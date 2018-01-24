package org.usfirst.frc.team694.robot;
import edu.wpi.first.wpilibj.AnalogInput;
import java.util.ArrayList;

public class LineSensor {
	private int[] diffLightFrames = new int[5];
	private int framesExsisted = 0;
	private int[] ambientLightFrames = new int[5];
	private int ambientLight;
    private double avgDist;
    private int linesCrossed = 0;
    private boolean isChangedBefore = false;
    private double threshold = 1.2;
    private int refreshRate;
    private int rawValue;
    private boolean setupDone = false;
    private AnalogInput mySensor;
	public LineSensor(AnalogInput mySensor,double threshold,int refreshRate){
		this.mySensor = mySensor;
		this.threshold = threshold;
		this.refreshRate = refreshRate;
		diffLightFrames = new int[refreshRate];
		ambientLightFrames = new int[refreshRate];
		rawValue = mySensor.getValue();
		ambientLightFrames[0] = rawValue;
		diffLightFrames[0] = rawValue;
	}
	public double averageList(int[] k, boolean isDifference){
		int sum = 0;
		int j = isDifference ? 1 : 0;
		int p = j;
		for (int i : k){
			sum += isDifference ? k[p] : (k[p] - k[p - 1]);
			p++;
		}
		return (sum / (p - j));
	}
	public void shiftList(){
		if (isNotExtraneous()){
		   System.arraycopy(diffLightFrames, 1, diffLightFrames, 0, diffLightFrames.length - 1);
	       diffLightFrames[diffLightFrames.length - 1] = ambientLight - rawValue;
		}
	}
	public boolean isNotExtraneous(){
	    return (Math.abs(rawValue - (diffLightFrames[refreshRate - 1])) < 5);
	}
	public void getRawData(){
		rawValue = mySensor.getValue();
	}
	public void postLoop(){
		shiftList();
	}
	public void initialLoop(){
		ambientLightFrames[framesExsisted] = rawValue;
		diffLightFrames[framesExsisted] = rawValue;
	    setupDone = (framesExsisted == refreshRate - 1);
	    if (setupDone){
	    	ambientLight = (int) averageList(ambientLightFrames,false);
	    }
	}
	public void mainLoop(){
	  framesExsisted++;
	  getRawData();
	  if (setupDone){
		  postLoop();
	  }else{
		  initialLoop();
	  }

	}
}

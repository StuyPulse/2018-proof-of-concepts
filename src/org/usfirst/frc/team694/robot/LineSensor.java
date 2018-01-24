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
    private double differenceThreshold = 1.2;
    private double outlierThreshold = 5;
    private int refreshRate;
    private int rawValue;
    private boolean setupDone = false;
    private AnalogInput mySensor;
	public LineSensor(AnalogInput mySensor,double differenceThreshold,double outlierThreshold,int refreshRate){
		this.mySensor = mySensor;
		this.differenceThreshold = differenceThreshold;
		this.outlierThreshold = outlierThreshold;
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
			sum += isDifference ? i : (i - k[p - 1]);
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
	    return (Math.abs(rawValue - (diffLightFrames[refreshRate - 1])) < outlierThreshold);
	}
	public void getRawData(){
		rawValue = mySensor.getValue();
	}
	public void postLoop(){
		shiftList();
		avgDist = averageList(diffLightFrames,true);
		if ((avgDist > differenceThreshold )&& (!(isChangedBefore)) ){
			System.out.println(avgDist);
			printArray(diffLightFrames);
			linesCrossed++;
			System.out.println(linesCrossed);
			
		}
		isChangedBefore = avgDist > differenceThreshold;
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
    public static void printArray(int[] myArray){                                                                                               
        for (int i : myArray){
            System.out.print(i + ",");
        }
        System.out.println("");   
      }
}

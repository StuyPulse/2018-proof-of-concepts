package org.usfirst.frc.team694.robot;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

import java.util.ArrayList;

public class LineSensorSystem {
    private boolean onLineBefore;
    private boolean darkRawValue;
    private boolean lightRawValue;
    private boolean newLine;
    private String colorFound;
    private DigitalInput darkColorSensor;
    private DigitalInput lightColorSensor;
    public LineSensorSystem(DigitalInput dark, DigitalInput light){
        darkColorSensor = dark;
        lightColorSensor = light;
        colorFound = "grey";

    }
    public void getRawData(){
        darkRawValue = darkColorSensor.get();
        lightRawValue = lightColorSensor.get();
    }
    public void mainLoop(){
      getRawData();
      if (!(darkRawValue || lightRawValue)){
          colorFound = "black";
      }else{
          if (!lightRawValue && darkRawValue){
              colorFound = "grey";
          }else{
              colorFound = "Red/White/Blue";
          }
      }
      newLine = (colorFound != "grey" )&& (!darkRawValue  && !lightRawValue|| lightRawValue && darkRawValue) != onLineBefore;
      onLineBefore = newLine ;

    }
    public boolean basicFind(){
        //System.out.println("port 2 DIO (DARK):" + darkRawValue);
        //System.out.println("port 3 DIO (LIGHT):" + lightRawValue);
        System.out.println("port 8 DIO (DARK):" + darkRawValue);
        System.out.println("port 9 DIO (LIGHT):" + lightRawValue);
        if (newLine){
            System.out.println("port 8 DIO (DARK):" + darkRawValue);
            System.out.println("port 9 DIO (LIGHT):" + lightRawValue);
            System.out.println("new Color:" + colorFound);
        }
        return newLine;
    }
}

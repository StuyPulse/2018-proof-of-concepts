/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;
import java.util.ArrayList;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	public static OI m_oi;
    //public int ambientLight;
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	/*public ArrayList<Integer> diffLightFrames = new ArrayList<Integer>();
    public double avgDist;
    public int linesCrossed;
    public boolean isChangedBefore;
    public double threshold;
    public int refreshRate;*/
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	AnalogInput analogTest;
	LineSensor backSensor;

	@Override
	public void robotInit() {
		m_oi = new OI();
        //linesCrossed = 0;
        //isChangedBefore = false;
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		analogTest = new AnalogInput(RobotMap.ANALOG_SONAR_PORT);
		AnalogInput.setGlobalSampleRate(40);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		backSensor = new LineSensor(analogTest,RobotMap.DIFFERENCE_THRESHOLD,RobotMap.OUTLIER_THRESHOLD,RobotMap.REFRESH_RATE);
        /*linesCrossed = 0;
        threshold = 0.8;
        isChangedBefore = false;
		ambientLight = analogTest.getValue();
		refreshRate = 3;
		System.out.println(ambientLight);*/
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Voltage: ", analogTest.getVoltage());
		SmartDashboard.putNumber("Raw Value", analogTest.getValue());
		SmartDashboard.putNumber("Average Voltage: ", analogTest.getAverageVoltage());
		SmartDashboard.putNumber("Average Raw Value: ", analogTest.getAverageValue());
        backSensor.mainLoop();
		    //int diffLight = ambientLight - analogTest.getValue();

		    /*if (diffLight > 24) { 
		        System.out.println("The Light Sensor is reporting white");
		    }
		    if (diffLight > 5 && diffLight < 23){
		        System.out.println("The Light Sensor is reporting alliance color");
		       }
		    if (diffLight > -10 && diffLight < 5) {
		        System.out.println("The Light Sensor is reporting grey");
		    }
		    if (diffLight < -15) {
		        System.out.println("The Light Sensor is reporting black");
		    }*/
		    /*if (diffLightFrames.size() >= refreshRate){
		        diffLightFrames.remove(0);
		    
		    }
		    if  ((diffLightFrames.size() == 0 )|| (Math.abs(diffLight - diffLightFrames.get(diffLightFrames.size() - 1)) < refreshRate)){
		        diffLightFrames.add(diffLight);
		    }
		    
		    //System.out.println("");
		    //avgDist = getDifferenceAvg(diffLightFrames);
		    //System.out.println(avgDist);
		    avgDist = getDifferenceAvg(diffLightFrames);
		    if ((avgDist > threshold) && (diffLightFrames.size() == refreshRate)){
		        //System.out.println("lineSet");
		        if (! isChangedBefore){
		            
		            System.out.println(avgDist);
		            printList(diffLightFrames);
		            
		           linesCrossed += 1;
		           System.out.println(linesCrossed);
		        }
		    }
		    isChangedBefore = (avgDist > threshold);*/
		    //System.out.println(linesCrossed);
		    //System.out.println(diffLight);
	    
	}
    /* public static void printList(ArrayList myArray){                                                                                               
       for (int i = 0; i < myArray.size() ; i++){
           System.out.print(myArray.get(i) + ",");
       }
       System.out.println("");   
     }
     public static double getDifferenceAvg(ArrayList<Integer> myData){
         double sum = 0;
         for (int i = 1; i < myData.size() ; i++){
             sum += Math.abs(myData.get(i) - myData.get(i - 1));
         }
         sum /= myData.size();
         return sum;

     }
     public static double getAvg(ArrayList<Integer> myData){
         double sum = 0;
         for (int i = 0; i < myData.size() ; i++){
             sum += Math.abs(myData.get(i));
         }
         sum /= myData.size();
         return sum;
     }*/
     

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;
import java.util.ArrayList;

import org.usfirst.frc.team694.robot.LineSensorSystem;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
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
	WPI_TalonSRX leftTop;
	WPI_TalonSRX rightTop;
	WPI_TalonSRX leftBottom;
	WPI_TalonSRX rightBottom;
	private boolean isDone = false;
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
	DigitalInput leftLineSensor;//senses colors darker than carpet
	DigitalInput rightLineSensor;//senses colors lighter the carpet
	LineSensorSystem lineSensorSystem;

	@Override
	public void robotInit() {
		m_oi = new OI();
        //linesCrossed = 0;
        //isChangedBefore = false;
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		leftTop = new WPI_TalonSRX(1);
		leftBottom = new WPI_TalonSRX(2);
		rightTop = new WPI_TalonSRX(3);
		rightBottom = new WPI_TalonSRX(4);
		leftTop.setNeutralMode(NeutralMode.Brake);
		rightBottom.setNeutralMode(NeutralMode.Brake);
		rightTop.setNeutralMode(NeutralMode.Brake);
		leftBottom.setNeutralMode(NeutralMode.Brake);
		leftLineSensor = new DigitalInput(RobotMap.DIGITAL_LEFT_LINE_SENSOR_PORT);
		rightLineSensor = new DigitalInput(RobotMap.DIGITAL_RIGHT_LINE_SENSOR_PORT);
	    lineSensorSystem = new LineSensorSystem(leftLineSensor,rightLineSensor);		
		AnalogInput.setGlobalSampleRate(24000);
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
		isDone = false;

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
		lineSensorSystem.mainLoop();
		
		if (lineSensorSystem.basicFind() && isDone == false){			
		    if (!isDone){
		    	System.out.println("move");
		    	//leftTop.set(-0.75);
				//leftBottom.set(-0.75);
				//rightTop.set(0.75);
				//rightBottom.set(0.75);
			}
		} else {
			System.out.println("stop");
			leftTop.set(0);
			leftBottom.set(0);
			rightTop.set(0);
			rightBottom.set(0);
			isDone = true;
		}
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
	}
     

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
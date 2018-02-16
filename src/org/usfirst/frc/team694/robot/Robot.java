/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.*;


import org.usfirst.frc.team694.robot.commands.RampingTurningTestingCommand;
import org.usfirst.frc.team694.robot.commands.RotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.ScoreScaleSameSideAutonCommand;
import org.usfirst.frc.team694.robot.subsystems.Drivetrain;
import org.usfirst.frc.team694.robot.subsystems.Gyro;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	public static Solenoid solenoid;
	public static Drivetrain drivetrain;
	public static Gyro gyro;
	public static double p = 0.002;
	public static double i = p / 100;
	public static double d = 10 * p;
	public static double f = 0.3188;
	public final static double RPM = 470; //something;
	public static int cruiseVel = (int) Math.round(RPM * 3/4);
	public static int accel = cruiseVel;
	public static int timeoutms = 0;
	public static double previousTime = 0;
	public static double deltaTime = 0;
	public static double accumulatedTime = 0;
	public static double startTime;
	public static int count = 0;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		Robot.drivetrain.resetEncoders();
		SmartDashboard.putNumber("Test Distance", 170);
		SmartDashboard.putNumber("RampSeconds", 2);
		SmartDashboard.putNumber("Offset", 122);
		
		SmartDashboard.putNumber("DriveDistanceEncodersPID P", 0); 
		SmartDashboard.putNumber("DriveDistanceEncodersPID I", 0);
		SmartDashboard.putNumber("DriveDistanceEncodersPID D", 0);

		SmartDashboard.putNumber("RotateDegreesPID P", 0);
		SmartDashboard.putNumber("RotateDegreesPID I", 0); 
		SmartDashboard.putNumber("RotateDegreesPID D", 0);
		
		/*
		Robot.drivetrain.leftFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeoutms);
		Robot.drivetrain.rightFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeoutms);
		
		Robot.drivetrain.rightFront.setSensorPhase(true);
		
		Robot.drivetrain.leftFront.configPeakOutputReverse(-1, timeoutms);
		Robot.drivetrain.rightFront.configPeakOutputReverse(-1, timeoutms);
		
		Robot.drivetrain.leftFront.configPeakOutputForward(1, timeoutms);
		Robot.drivetrain.rightFront.configPeakOutputForward(1, timeoutms);
		
		Robot.drivetrain.leftFront.configNominalOutputForward(0, timeoutms);
		Robot.drivetrain.rightFront.configNominalOutputForward(0, timeoutms);
		
		Robot.drivetrain.leftFront.configNominalOutputReverse(0, timeoutms);
		Robot.drivetrain.rightFront.configNominalOutputReverse(0, timeoutms);
		
		Robot.drivetrain.leftFront.selectProfileSlot(0, 0);
		Robot.drivetrain.rightFront.selectProfileSlot(0, 0);
		
		Robot.drivetrain.leftFront.config_kF(0, f, timeoutms);
		Robot.drivetrain.leftFront.config_kP(0, p, timeoutms);
		Robot.drivetrain.leftFront.config_kI(0, i, timeoutms);
		Robot.drivetrain.leftFront.config_kD(0, d, timeoutms);

		
		Robot.drivetrain.rightFront.config_kF(0, f, timeoutms);
		Robot.drivetrain.rightFront.config_kP(0, p, timeoutms);
		Robot.drivetrain.rightFront.config_kI(0, i, timeoutms);
		Robot.drivetrain.rightFront.config_kD(0, d, timeoutms);

		
		Robot.drivetrain.rightFront.configMotionCruiseVelocity(cruiseVel, timeoutms);
		Robot.drivetrain.rightFront.configMotionAcceleration(accel, timeoutms);
		
		Robot.drivetrain.leftFront.configMotionCruiseVelocity(cruiseVel, timeoutms);
		Robot.drivetrain.leftFront.configMotionAcceleration(accel, timeoutms);
		
		Robot.drivetrain.leftFront.setSelectedSensorPosition(0, 0, timeoutms);
		Robot.drivetrain.rightFront.setSelectedSensorPosition(0, 0, timeoutms);
	*/	
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * You can addw additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//new MotionMagicCommand(30).start();
		
		//new RotateDegreesPIDCommand(90).start();
		
		//new DriveStraightWithRampingWithLineSensorCommand(118).start();

		
		new ScoreScaleSameSideAutonCommand().start(); //Going 168 Inches DOES NOT work with full speed on DEStiny
		//new TestTimeCommand().start();
		//new DriveStraightWithRampingCommand().start();
		//new DriveDistanceEncodersPIDCommand(168).start();
		//new RampingTurningTestingCommand().start();
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		//System.out.print("hi");
		//Robot.drivetrain.tankDrive(0.5, 0.5);
		/*Robot.drivetrain.leftFront.set(ControlMode.MotionMagic, 30);
		Robot.drivetrain.leftRear.set(ControlMode.MotionMagic, 30);
		Robot.drivetrain.rightFront.set(ControlMode.MotionMagic, 30);
		Robot.drivetrain.rightRear.set(ControlMode.MotionMagic, 30);
        */Scheduler.getInstance().run();
		SmartDashboard.putNumber("LeftEncoder:", Robot.drivetrain.getLeftEncoderDistance());
		SmartDashboard.putNumber("RightEncoder:", Robot.drivetrain.getRightEncoderDistance());
		//Robot.drivetrain.tankDrive(0.7, 0.7);
		//deltaTime = Timer.getFPGATimestamp() - previousTime;
		//count ++;
		//accumulatedTime += deltaTime;
		//previousTime = Timer.getFPGATimestamp();
		//System.out.println("Average Time Elapsed: " + count/accumulatedTime);
	}

	@Override
	public void teleopInit() {
		
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

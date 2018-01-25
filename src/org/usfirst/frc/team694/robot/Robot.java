/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.subsystems.Drivetrain;
import org.usfirst.frc.team694.robot.subsystems.Gyro;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	public static Drivetrain drivetrain;
	public static Gyro gyro;
	public static double p;
	public static double i;
	public static double d;
	public static double f;
	public final double RPM = 470; //something;
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		SmartDashboard.putNumber("Test Distance", 0);

		SmartDashboard.putNumber("DriveDistanceEncodersPID P", 0); 
		SmartDashboard.putNumber("DriveDistanceEncodersPID I", 0);
		SmartDashboard.putNumber("DriveDistanceEncodersPID D", 0);

		SmartDashboard.putNumber("RotateDegreesPID P", 0);
		SmartDashboard.putNumber("RotateDegreesPID I", 0); 
		SmartDashboard.putNumber("RotateDegreesPID D", 0);
		
		Robot.drivetrain.leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		Robot.drivetrain.rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		Robot.drivetrain.rightFront.setSensorPhase(true);
		
		Robot.drivetrain.leftFront.configPeakOutputReverse(-1, 10);
		Robot.drivetrain.leftRear.configPeakOutputReverse(-1, 10);
		Robot.drivetrain.rightFront.configPeakOutputReverse(-1, 10);
		Robot.drivetrain.rightRear.configPeakOutputReverse(-1, 10);
		
		Robot.drivetrain.leftFront.configPeakOutputForward(1, 10);
		Robot.drivetrain.leftRear.configPeakOutputForward(1, 10);
		Robot.drivetrain.rightFront.configPeakOutputForward(1, 10);
		Robot.drivetrain.rightRear.configPeakOutputForward(1, 10);
		
		Robot.drivetrain.leftFront.configNominalOutputForward(0, 10);
		Robot.drivetrain.leftRear.configNominalOutputForward(0, 10);
		Robot.drivetrain.rightFront.configNominalOutputForward(0, 10);
		Robot.drivetrain.rightRear.configNominalOutputForward(0, 10);
		
		Robot.drivetrain.leftFront.configNominalOutputReverse(0, 10);
		Robot.drivetrain.leftRear.configNominalOutputReverse(0, 10);
		Robot.drivetrain.rightFront.configNominalOutputReverse(0, 10);
		Robot.drivetrain.rightRear.configNominalOutputReverse(0, 10);
		
		Robot.drivetrain.leftFront.selectProfileSlot(0, 0);
		Robot.drivetrain.leftRear.selectProfileSlot(0, 0);
		Robot.drivetrain.rightFront.selectProfileSlot(0, 0);
		Robot.drivetrain.rightRear.selectProfileSlot(0, 0);
		
		Robot.drivetrain.leftFront.config_kF(0, f, 10);
		Robot.drivetrain.leftFront.config_kP(0, p, 10);
		Robot.drivetrain.leftFront.config_kI(0, i, 10);
		Robot.drivetrain.leftFront.config_kD(0, d, 10);
		
		Robot.drivetrain.leftRear.config_kF(0, f, 10);
		Robot.drivetrain.leftRear.config_kP(0, p, 10);
		Robot.drivetrain.leftRear.config_kI(0, i, 10);
		Robot.drivetrain.leftRear.config_kD(0, d, 10);
		
		Robot.drivetrain.rightFront.config_kF(0, f, 10);
		Robot.drivetrain.rightFront.config_kP(0, p, 10);
		Robot.drivetrain.rightFront.config_kI(0, i, 10);
		Robot.drivetrain.rightFront.config_kD(0, d, 10);
		
		Robot.drivetrain.rightRear.config_kF(0, f, 10);
		Robot.drivetrain.rightRear.config_kP(0, p, 10);
		Robot.drivetrain.rightRear.config_kI(0, i, 10);
		Robot.drivetrain.rightRear.config_kD(0, d, 10);
		
		Robot.drivetrain.rightFront.configMotionCruiseVelocity(0, 10);
		Robot.drivetrain.rightFront.configMotionAcceleration(0, 10);
		
		Robot.drivetrain.leftFront.configMotionCruiseVelocity(0, 10);
		Robot.drivetrain.leftFront.configMotionAcceleration(0, 10);
		
		Robot.drivetrain.rightRear.configMotionCruiseVelocity(0, 10);
		Robot.drivetrain.rightRear.configMotionAcceleration(0, 10);
		
		Robot.drivetrain.leftRear.configMotionCruiseVelocity(0, 10);
		Robot.drivetrain.leftRear.configMotionAcceleration(0, 10);
		

		
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
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		double targetPosition = 30;
		Robot.drivetrain.leftFront.set(ControlMode.MotionMagic, targetPosition);
		Robot.drivetrain.leftRear.set(ControlMode.MotionMagic, targetPosition);
		Robot.drivetrain.rightFront.set(ControlMode.MotionMagic, targetPosition);
		Robot.drivetrain.rightRear.set(ControlMode.MotionMagic, targetPosition);
		//(new DriveDistanceEncodersPIDCommand(100)).start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
        Scheduler.getInstance().run();
		SmartDashboard.putNumber("[DriveDistanceEncodersCommand] distance:", Robot.drivetrain.getEncoderDistance());
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

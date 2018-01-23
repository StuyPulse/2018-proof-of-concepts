/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.DriveDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.DriveStraightPIDCommand;
import org.usfirst.frc.team694.robot.commands.RotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.subsystems.Drivetrain;
import org.usfirst.frc.team694.robot.subsystems.Gyro;

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
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//(new DriveDistanceEncodersPIDCommand(100)).start();
		new DriveStraightPIDCommand(20).start();
		
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

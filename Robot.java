/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
    private WPI_TalonSRX motorLeftTop = new WPI_TalonSRX(1);
    private WPI_TalonSRX motorLeftBottom = new WPI_TalonSRX(2);
    private WPI_TalonSRX motorRightTop = new WPI_TalonSRX(3);
    private WPI_TalonSRX motorRightBottom = new WPI_TalonSRX(4);
    private SpeedControllerGroup left = new SpeedControllerGroup(motorLeftTop, motorLeftBottom);
    private SpeedControllerGroup right = new SpeedControllerGroup(motorRightTop, motorRightBottom);
    private DifferentialDrive mdrive = new DifferentialDrive (left, right);
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	    motorRightTop.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 2000);
	}

	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	public void teleopInit() {
	    
	}
	@Override
	public void teleopPeriodic() {
	    mdrive.tankDrive(.5, .5);
	    double distance = motorRightTop.getSelectedSensorPosition(0);
	    double velocity = motorRightTop.getSelectedSensorVelocity(0);
	    System.out.println("Distance: " + distance + " Velocity: " + velocity);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	
	@Override
	public void testPeriodic() {
	}
}

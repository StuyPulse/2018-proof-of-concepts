/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends IterativeRobot {

	private WPI_TalonSRX leftFront;
	private WPI_TalonSRX rightFront;
	private WPI_TalonSRX leftRear;
	private WPI_TalonSRX rightRear;

	private DifferentialDrive differentialDrive;

	private SpeedControllerGroup leftSpeedController;
	private SpeedControllerGroup rightSpeedController;

	private boolean arcadeDrive;
	private boolean wasPressed;

	public static OI oi;

	private	double rightTrigger;
	private double leftAxis;
	private double rightAxis;
	private double leftTrigger;

	private double leftTriggerSquared;
	private double rightTriggerSquared;

	@Override
	public void robotInit() {
		leftFront = new WPI_TalonSRX(4);
		leftRear = new WPI_TalonSRX(3);
		leftSpeedController = new SpeedControllerGroup(leftFront, leftRear);

		rightFront = new WPI_TalonSRX(1);
		rightRear = new WPI_TalonSRX(2);
		rightSpeedController = new SpeedControllerGroup(rightFront, rightRear);

		leftFront.setInverted(true);
		leftRear.setInverted(true);
		rightFront.setInverted(true);
		rightRear.setInverted(true);

		differentialDrive = new DifferentialDrive(leftSpeedController, rightSpeedController);

		oi = new OI();

		CameraServer.getInstance().startAutomaticCapture();
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

	public void teleopInit() {
		arcadeDrive = true;
		wasPressed = false;
		leftTrigger = oi.driverPad.getLeftTriggerAxis();
		rightTrigger = oi.driverPad.getRightTriggerAxis();
		//leftAxis = oi.driverPad.;
		//rightAxis;
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		leftTrigger = oi.driverPad.getLeftTriggerAxis();
		rightTrigger = oi.driverPad.getRightTriggerAxis();

		rightTriggerSquared = ((rightTrigger + 1) / 2) * ((rightTrigger + 1) / 2);
		leftTriggerSquared = ((leftTrigger + 1) / 2) * ((leftTrigger + 1) / 2);

		if (oi.driverPad.getRawButton(1) && wasPressed == false) {
			arcadeDrive = !arcadeDrive;
		}
		wasPressed = oi.driverPad.getRawButton(1);
		System.out.println(leftTrigger);
		if(arcadeDrive) {
			differentialDrive.arcadeDrive(-1.0 * leftTriggerSquared + rightTriggerSquared, Math.signum(oi.driverPad.getLeftX()) * oi.driverPad.getLeftX() * oi.driverPad.getLeftX());
		} else {
			differentialDrive.tankDrive(-1.0 * Math.signum(oi.driverPad.getRightY()) * oi.driverPad.getRightY() * oi.driverPad.getRightY(), -1.0 * Math.signum(oi.driverPad.getLeftY())* oi.driverPad.getLeftY() * oi.driverPad.getLeftY());
		}
	}


	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

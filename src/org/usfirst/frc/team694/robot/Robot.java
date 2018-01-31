package org.usfirst.frc.team694.robot;

import java.util.ArrayDeque;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	// Motor Physical Port
	public static final int MOTOR_PORT = 2;
	// Motor
	private WPI_TalonSRX motor;
	private double maxOutputCurrent = 1.2;
	private ArrayDeque<Double> history = new ArrayDeque<Double>();
	private boolean motorStall = false;

	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		motor = new WPI_TalonSRX(MOTOR_PORT);
	}

	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		System.out.println("Auto selected: " + autoSelected);
	}

	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * Test the temperature and voltage values to find change when "stalling"
	 */
	@Override
	public void teleopPeriodic() {
		// Turn Motor On
		if (isMotorStalled(motor)) {
			motorStall = true;
		}
		if (motorStall) {
			try {
				motor.set(ControlMode.PercentOutput, -0.2);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} else {
			motor.set(ControlMode.PercentOutput, 0.2);
		} 
		motorStall = false;
		// Smart Dashboard Output
		SmartDashboard.putNumber("Output current", motor.getOutputCurrent());
	}

	/**
	 * Check if the motor is stalled and returns true if it is.
	 * 
	 * @param motor
	 *            The motor to check
	 * @return Whether the motor is stalled or not
	 */
	public boolean isMotorStalled(WPI_TalonSRX motor) {
		while (history.size() >= 10) {
			history.pop();
		}
		history.add(motor.getOutputCurrent());
		for (double d : history) {
			if (!(d > maxOutputCurrent)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if the motor is stalled, and turns it the other direction if it is.
	 * 
	 * @param motor
	 *            The motor to check and fix
	 */
	public void checkAndFixStalledMotor(WPI_TalonSRX motor) {
		if (isMotorStalled(motor)) {
			motor.set(ControlMode.PercentOutput, 0);
		}
	}

	@Override
	public void testPeriodic() {
	}
}

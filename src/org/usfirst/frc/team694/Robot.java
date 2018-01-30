package org.usfirst.frc.team694.robot;

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
	public static final int MOTOR_PORT = 1;
	// Motor
	private WPI_TalonSRX motor;
	// Motor Highest Values Storage
	private double highestBusVoltage = -1;
	private double highestMotorOutputVoltage = -1;
	private double highestTemperature = -1;
	// Motor Limits
	private double maxTemperature = 30;
	private double maxVoltage = 30;

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
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
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
		motor.set(ControlMode.PercentOutput, 1);
		// Print Motor Values
		System.out.println("[Robot] - Temperature: " + motor.getTemperature() + ", Voltage Bus: "
				+ motor.getBusVoltage() + ", Voltage Output: " + motor.getMotorOutputVoltage());
		// Update Highest Values
		highestBusVoltage = motor.getBusVoltage() > highestBusVoltage ? motor.getBusVoltage() : highestBusVoltage;
		highestMotorOutputVoltage = motor.getMotorOutputVoltage() > highestMotorOutputVoltage
				? motor.getMotorOutputVoltage() : highestMotorOutputVoltage;
		highestTemperature = motor.getTemperature() > highestTemperature ? motor.getTemperature() : highestTemperature;
		// Print Highest Values
		System.out.println("[Robot] - Highest Temperature: " + highestTemperature + ", Highest Bus Voltage: "
				+ highestBusVoltage + ", Highest Voltage Output: " + highestMotorOutputVoltage);
		//Smart Dashboard
		SmartDashboard.putNumber("Bus Voltage", motor.getBusVoltage());
		SmartDashboard.putNumber("Output Voltage", motor.getMotorOutputVoltage());
		SmartDashboard.putNumber("Temperature", motor.getTemperature());
	}
	/**
	 * Check if the motor is stalled and returns true if it is.
	 * @param motor The motor to check
	 * @return Whether the motor is stalled or not
	 */
	public boolean isMotorStalled(WPI_TalonSRX motor) {
		try {
			if (motor.getTemperature() > maxTemperature) {
				return true;
			} else if (motor.getBusVoltage() > maxVoltage) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException e) {
			return true;
		}
	}
	/**
	 * Check if the motor is stalled, and turns it the other direction if it is.
	 * @param motor The motor to check and fix
	 */
	public void checkAndFixStalledMotor(WPI_TalonSRX motor){
		if(isMotorStalled(motor)){
			motor.set(ControlMode.PercentOutput, -1);
		}
	}
	@Override
	public void testPeriodic() {
	}
}

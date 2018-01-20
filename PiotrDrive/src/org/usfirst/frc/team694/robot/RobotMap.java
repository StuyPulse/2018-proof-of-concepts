/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int LEFT_FRONT_MOTOR_CHANNEL = 1;
	public static final int LEFT_REAR_MOTOR_CHANNEL = 2;
	public static final int RIGHT_FRONT_MOTOR_CHANNEL = 3;
	public static final int RIGHT_REAR_MOTOR_CHANNEL = 4;
	
	public static final int DRIVER_GAMEPAD = 0;
	public static final int OPERATOR_GAMEPAD = 1;
	  
	public static final int LEFT_ENCODER_CHANNEL_A = 0;
	public static final int LEFT_ENCODER_CHANNEL_B = 1;
	public static final int RIGHT_ENCODER_CHANNEL_A = 2;
	public static final int RIGHT_ENCODER_CHANNEL_B = 3;
	
	public static final double CHEAT_FACTOR = (92.0/105.0);
	public static final int QUADITURE_FACTOR = 4;
	public static final int ENCODER_FACTOR = 360;
	public static final int PULSES_PER_REVOLUTION = QUADITURE_FACTOR * ENCODER_FACTOR;
	public static final int DRIVETRAIN_WHEEL_DIAMETER = 8;
	public static final double DRIVETRAIN_WHEEL_CIRCUMFERENCE = DRIVETRAIN_WHEEL_DIAMETER * Math.PI;
	public static final double DRIVETRAIN_ENCODER_INCHES_PER_PULSE = DRIVETRAIN_WHEEL_CIRCUMFERENCE * CHEAT_FACTOR/ PULSES_PER_REVOLUTION;
    
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

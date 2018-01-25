/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

public interface RobotMap {

	// Drivetrain CANTalon channels
    int FRONT_RIGHT_MOTOR_CHANNEL = 1;
    int REAR_RIGHT_MOTOR_CHANNEL = 2;
    int REAR_LEFT_MOTOR_CHANNEL = 3;
    int FRONT_LEFT_MOTOR_CHANNEL = 4;

    int GEAR_SHIFT_CHANNEL = 3;

    // Digital IO Ports
    int LEFT_ENCODER_CHANNEL_A = 0;
    int LEFT_ENCODER_CHANNEL_B = 1;
    int RIGHT_ENCODER_CHANNEL_A = 2;
    int RIGHT_ENCODER_CHANNEL_B = 3;

    int DIO_ENCODER_PULSES_PER_REVOLUTION = 360;

    double DRIVETRAIN_WHEEL_DIAMETER = 8;
    int DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION = 256;
    double DRIVETRAIN_ENCODERS_FACTOR = 4.0;
    double DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION = Math.PI * DRIVETRAIN_WHEEL_DIAMETER;

    double DRIVETRAIN_WHEEL_CIRCUMFERENCE = DRIVETRAIN_WHEEL_DIAMETER * Math.PI;
    double DRIVETRAIN_ENCODER_INCHES_PER_PULSE = DRIVETRAIN_WHEEL_CIRCUMFERENCE / DIO_ENCODER_PULSES_PER_REVOLUTION;

}

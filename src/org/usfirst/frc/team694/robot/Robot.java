/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {

    private WPI_TalonSRX motorLeftTop;
    private WPI_TalonSRX motorLeftBottom;
    private WPI_TalonSRX motorRightTop;
    private WPI_TalonSRX motorRightBottom;

    private SpeedControllerGroup left;
    private SpeedControllerGroup right;

    private DifferentialDrive mdrive;

    int DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION = 256;
    double DRIVETRAIN_WHEEL_DIAMETER = 4.0;
    double DRIVETRAIN_ENCODERS_FACTOR = 4.0; // output must be scaled *down* by 4 due to type of encoder
    double DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION = Math.PI * DRIVETRAIN_WHEEL_DIAMETER;



    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        motorLeftTop = new WPI_TalonSRX(1);
        motorLeftBottom = new WPI_TalonSRX(2);
        motorRightTop = new WPI_TalonSRX(3);
        motorRightBottom = new WPI_TalonSRX(4);

        left = new SpeedControllerGroup(motorLeftTop, motorLeftBottom);
        right = new SpeedControllerGroup(motorRightTop, motorRightBottom);

        mdrive = new DifferentialDrive (left, right);

        //int LEFT_ENCODER_CHANNEL_A = 0;
        //int LEFT_ENCODER_CHANNEL_B = 1;
        //int RIGHT_ENCODER_CHANNEL_A = 2;
        //int RIGHT_ENCODER_CHANNEL_B = 3;

        Encoder encoderRight = new Encoder(0, 1);
        Encoder encoderLeft = new Encoder(2, 3);


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
        motorRightTop.setSelectedSensorPosition(0, 0, 0);
    }
    @Override
    public void teleopPeriodic() {
        mdrive.tankDrive(-.5, -.5);
        double distance = motorRightTop.getSelectedSensorPosition(0);
        double velocity = motorRightTop.getSelectedSensorVelocity(0);
        System.out.println("Distance: " + Math.abs(((distance*DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION)/4.0/425.0)*.564*0.875) + " Velocity: " + Math.abs(velocity));
    }

    /**
     * This function is called periodically during test mode.
     */

    @Override
    public void testPeriodic() {
    }
}

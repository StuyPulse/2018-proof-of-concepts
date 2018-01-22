package org.usfirst.frc.team694.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public interface RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;

    // Hardcoded before each match for auton
    public static final DriverStation.Alliance ALLIANCE = DriverStation.Alliance.Blue;

    int PRESSURE_LED_PORT = 9;
    int GEAR_LED_PORT = 8; // for when gear is in robot
    // Note: port 10 means port 0 on NavX (see DigitalIO docs)
    int GEAR_SHIFT_LED_PORT = 10;

    // On-values (i.e. which bit to send to indicate to turn the LED on)
    boolean PRESSURE_LED_ON_VALUE = true;
    boolean GEAR_LED_ON_VALUE = true;

    int IR_SENSOR_PORT = 0;
    double IR_SENSOR_THRESHOLD = 1.5;
    double IR_TIME_IN_MECHANISM_THRESHOLD = 1.0;

    double EQUATION_FACTOR = 12.23368994;
    double EQUATION_EXPONENT = -0.9779601588;
    double CONVERSION_FACTOR_CM_TO_INCHES = 0.393701;

}

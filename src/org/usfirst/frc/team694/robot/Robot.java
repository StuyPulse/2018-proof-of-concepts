/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

/*USAGE INFORMATION ABOUT THE ACCELEROMETER:
 * 
 * - The acceleration for x, y, and z works, but need the actual robot to know the normal vibrations
 * in order to detect bumps
 * - The rotation for x, y, and z work but you have to choose which one you want to work.
 * If you place the navX parallel to the ground, the z rotation (around the z-axis) works,
 * but the x and y rotation values will go from 0-90-0 instead of 0-90-180 (if that makes sense at all)
 * 
 *  So, if we use it for rotation it will only be able to measure rotation around ONE axis, but that
 *  is probably all we need. The accuracy was good.
 * 
 * 
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	public static AHRS accelerometer;
	public static double xAccel;
	public static double yAccel;
	public static double zAccel;
  	public static double xVelocity;
  	public static double yVelocity;
  	public static double zVelocity;
  	public static double xrotation;
  	public static double yrotation;
  	public static double zrotation;
//	public static int secondsPassed = 0;
	
/*	Timer startTimer = new Timer();
	TimerTask task = new TimerTask() { 
		public void run() {
			secondsPassed++;
		}
	};
*/
	
//	public start() {
//		startTimer.scheduleAtFixedRate(task,1000,1000);
//	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		accelerometer = new AHRS(SPI.Port.kMXP);
		if (accelerometer.isConnected()) {
			System.out.println("Accelerometer is connected.");
		}else {
			System.out.println("Accelerometer isn't connected.");
		}
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		
		}
	}
	
	// AHRS Sensors and the Accelerometer does not have the method getVelocity()
	// Timer could be used to calculate velocity by multiplying time and acceleration 
	// if velocity is necessary. The implementation of timer is in comments.
	public static void getAccelerometerValues() {
//		Robot robot = new Robot();
//		robot.start();
		xAccel = accelerometer.getWorldLinearAccelX();
//		xVelocity = accelerometer.getVelocityX();
// 		Alternative --> xVelocity = xAccel * secondsPassed;
		yAccel = accelerometer.getWorldLinearAccelY();
//		yVelocity = accelerometer.getVelocityY();
// 		Alternative --> yVelocity = yAccel * secondsPassed;
		zAccel = accelerometer.getWorldLinearAccelZ();
//		zVelocity = accelerometer.getVelocityZ();
// 		Alternative --> zVelocity = zAccel * secondsPassed;
		SmartDashboard.putNumber("X acceleration", xAccel);
//		System.out.print(" X velocity: " + xVelocity + " m/s,");
//		System.out.print(" Y acceleration: " + yAccel + " G,");
		SmartDashboard.putNumber("Y acceleration", yAccel);
//		System.out.print(" Y velocity: " + yVelocity + " m/s,");
//		System.out.print(" Z acceleration: " + zAccel + " G,");
		SmartDashboard.putNumber("Z acceleration", zAccel);
//		System.out.println(" Z velocity: " + zVelocity + " m/s,");
	}
	
	public static void getGyroValues() {
		xrotation = accelerometer.getPitch();
		yrotation = accelerometer.getRoll();
		zrotation = accelerometer.getYaw();
		SmartDashboard.putNumber("X Rotation", xrotation);
		SmartDashboard.putNumber("Y Rotation", yrotation);
		SmartDashboard.putNumber("Z Rotation", zrotation);
	}
	
	/*public static void updateVelocityValues() {
	 * xVelocity += xAccel;
	 * yVelocity += yAccel;
	 * zVelocity += zAccel;
	 * SmartDashboard.putNumber("X velocity", xVelocity);
	 * SmartDashboard.putNumber("Y velocity", yVelocity);
	 * SmartDashboard.putNumber("Z velocity", zVelocity);
	}
	/**,
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopInit() {
		accelerometer.reset();
	}
	
	@Override
	public void teleopPeriodic() {
		if (accelerometer.isCalibrating()) {
			System.out.println("Stay STILL!");
		}else {
			getAccelerometerValues();
			getGyroValues();
			SmartDashboard.putBoolean("Hit a bump?", Math.abs(zAccel) > .25);
			SmartDashboard.putBoolean("Is Moving", accelerometer.isMoving());	
		}
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
}
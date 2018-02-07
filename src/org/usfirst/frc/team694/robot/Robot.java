/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilderImpl;
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
	
	int i;
	boolean julianBoolean;
	boolean test;
	//public static SendableBuilder.BooleanConsumer randomInterface;
	SendableChooser<Command> autonChooser;
	//BooleanSupplier booleanDeliver;
	Command autonomousCommand;
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		autonChooser = new SendableChooser<Command>();
		autonChooser.addObject("Finding Nemo", new CommandGroup());
		autonChooser.addObject("Stew PIDF Pulse", new randomCommand());
		autonChooser.addObject("Wubba Lubba Dub Dub", new randomCommandTwo());
		SmartDashboard.putData("Auton Setting", autonChooser);
		//SendableBuilderImpl.addBooleanProperty("Fake Booleans", booleanDeliver.getAsBoolean(), randomInterface.accept(julianBoolean));
		//SendableBuilderImpl.addBooleanProperty("Fake Booleans", BooleanSupplier,)
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString line to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the SendableChooser
	 * make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		i = 0;
		autonomousCommand = (Command) autonChooser.getSelected();
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		i = i + 1;
		SmartDashboard.putNumber("Counting Up Thingamajig", i);
		SmartDashboard.putBoolean("Am I passing", ((i % 2) == 0));
		test = SmartDashboard.getBoolean("Test", true);
		SmartDashboard.putBoolean("Test Result", test);
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

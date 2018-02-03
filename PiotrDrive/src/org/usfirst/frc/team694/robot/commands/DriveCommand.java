/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveCommand extends Command { 	
	
	public DriveCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double rightTrigger = (Robot.oi.driverGamepad.getRawAxis(4) + 1) / 2;
		double leftTrigger = (Robot.oi.driverGamepad.getRawAxis(3) + 1) / 2;
		
		double rightTriggerSquared = Math.pow(rightTrigger, 2);
		double leftTriggerSquared = Math.pow(leftTrigger, 2);
		
		if(Math.abs(-leftTriggerSquared + rightTriggerSquared) < .05) {
			Robot.drivetrain.curvatureDrive(rightTriggerSquared - leftTriggerSquared, -1.0 * Robot.oi.driverGamepad.getRawAxis(0), true);
		} else {
			Robot.drivetrain.curvatureDrive(rightTriggerSquared - leftTriggerSquared, -1.0 * Robot.oi.driverGamepad.getRawAxis(0), false);
		}
	}	

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}

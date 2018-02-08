package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ResetEncoders extends Command {
	public boolean resetEncoders = false;
    public ResetEncoders() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    /*	SmartDashboard.putBoolean("EncoderReset", resetEncoders);
    	if (resetEncoders == true) {
    		Robot.drivetrain.resetEncoders();
    		resetEncoders = false;
    	}
    	SmartDashboard.putNumber("Right Distance", Robot.drivetrain.getRightEncoderDistance());
    	SmartDashboard.putNumber("Left Distance", Robot.drivetrain.getLeftEncoderDistance());*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

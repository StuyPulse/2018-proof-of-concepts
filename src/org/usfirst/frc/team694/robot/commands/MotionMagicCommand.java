package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotionMagicCommand extends Command {
	
	public double targetPosition;

    public MotionMagicCommand(double targetPosition) {
    	requires(Robot.drivetrain);
    	this.targetPosition = targetPosition;
    	System.out.println("hi");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.motionMagic(targetPosition);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("hi");
        return Robot.drivetrain.getEncoderDistance() - targetPosition >= 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RampingCommand extends Command {
	private double time;
	private double distance;
	private int startingTime;
	private double timeElapsed;
	private double startSeconds;
    public RampingCommand(double distance, double time) {
    	this.startSeconds = 1.5;
    	requires(Robot.drivetrain);
    	this.distance = distance;
    	startingTime = (int) Timer.getFPGATimestamp();
    	this.time = time;
    	Robot.drivetrain.leftFront.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.rightFront.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.leftRear.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.rightRear.configOpenloopRamp(startSeconds, 0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    	System.out.println("Reset");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.timeElapsed = Timer.getFPGATimestamp() - startingTime;
    	Robot.drivetrain.tankDrive(1, 1);
    	System.out.println(Robot.drivetrain.getEncoderDistance());
    	System.out.println(Robot.drivetrain.getEncoderVelocity());
    	SmartDashboard.putNumber("Velocity", Robot.drivetrain.getEncoderVelocity());
    	SmartDashboard.putNumber("Distance", Robot.drivetrain.getEncoderDistance());
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeElapsed >= time || Robot.drivetrain.getEncoderDistance() > distance;
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

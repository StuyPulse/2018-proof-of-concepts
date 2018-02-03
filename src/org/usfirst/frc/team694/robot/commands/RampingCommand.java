package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RampingCommand extends Command {
	private double distance;
	private double startSeconds;
	private double speed; 
    public RampingCommand(double distance, double speed) {
    	this.startSeconds = 1.5;
    	requires(Robot.drivetrain);
    	this.distance = distance;
    	this.speed = speed;
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
    	Robot.drivetrain.resetGyro();
    	System.out.println("Reset");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tankDrive(speed, speed);
    	System.out.println("Distance: " + Robot.drivetrain.getEncoderDistance());
    	System.out.println("Velocity: " + Robot.drivetrain.getEncoderVelocity());
    	SmartDashboard.putNumber("Velocity", Robot.drivetrain.getEncoderVelocity());
    	SmartDashboard.putNumber("Distance", Robot.drivetrain.getEncoderDistance());
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getEncoderDistance() > distance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    	Timer.delay(2);
    	System.out.println(Robot.drivetrain.getEncoderDistance());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

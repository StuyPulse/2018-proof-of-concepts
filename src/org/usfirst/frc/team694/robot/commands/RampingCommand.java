package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RampingCommand extends PIDCommand {
	private double time;
	private double distance;
	private int startingTime;
	private double timeElapsed;
	private double startSeconds;
	private double speed;
    public RampingCommand(double distance, double time, double speed) {
    	super(0,0,0);
    	this.startSeconds = 1.5;
    	requires(Robot.drivetrain);
    	this.distance = distance;
    	startingTime = (int) Timer.getFPGATimestamp();
    	this.time = time;
    	this.speed = speed;
 /*   	Robot.drivetrain.leftFront.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.rightFront.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.leftRear.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.rightRear.configOpenloopRamp(startSeconds, 0);*/
    	setSetpoint(0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    	Robot.drivetrain.resetGyro();
    	System.out.println("Reset");
    	this.getPIDController().setPID(
				SmartDashboard.getNumber("RotateDegreesPID P", 0), 
				SmartDashboard.getNumber("RotateDegreesPID I", 0), 
				SmartDashboard.getNumber("RotateDegreesPID D", 0)
				);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.timeElapsed = Timer.getFPGATimestamp() - startingTime;
//    	System.out.println(Robot.drivetrain.getEncoderDistance());
//    	System.out.println(Robot.drivetrain.getEncoderVelocity());
    	SmartDashboard.putNumber("Velocity", Robot.drivetrain.getEncoderVelocity());
    	SmartDashboard.putNumber("Distance", Robot.drivetrain.getEncoderDistance());
    	System.out.println(Robot.drivetrain.getGyroAngle());
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

	@Override
	protected double returnPIDInput() {
		return Robot.drivetrain.getGyroAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
			System.out.println("Running");
			Robot.drivetrain.tankDrive(speed + output, speed - output);
	}
}

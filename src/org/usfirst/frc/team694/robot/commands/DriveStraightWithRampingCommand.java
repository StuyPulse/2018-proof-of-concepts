package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightWithRampingCommand extends PIDCommand {
	double distance;
	double speed;
	double output;
	private double time;
	private int startingTime;
	private double timeElapsed;
	private double startSeconds;
	public DriveStraightWithRampingCommand(double distance, double speed, double time) {
		super(0,0,0);
		requires(Robot.drivetrain);
		this.distance = distance;
		this.speed = speed;
		this.startSeconds = 1.5;
    	requires(Robot.drivetrain);
    	this.distance = distance;
    	startingTime = (int) Timer.getFPGATimestamp();
    	this.time = time;
    	this.speed = speed;
    	Robot.drivetrain.leftFront.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.rightFront.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.leftRear.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.rightRear.configOpenloopRamp(startSeconds, 0);
    	setSetpoint(0);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.resetGyro();
		Robot.drivetrain.resetEncoders();
		this.getPIDController().setPID(
				SmartDashboard.getNumber("RotateDegreesPID P", 0),
				SmartDashboard.getNumber("RotateDegreesPID I", 0), 
				SmartDashboard.getNumber("RotateDegreesPID D", 0)
				);
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		this.timeElapsed = Timer.getFPGATimestamp() - startingTime;
		System.out.println("[RotateDegreesPIDCommand] angle:" + returnPIDInput());
		System.out.println("[RotateDegreesPIDCommand] velocity:" + Robot.drivetrain.getEncoderVelocity());
		System.out.println("[RotateDegreesPIDCommand] distance:" + Robot.drivetrain.getEncoderDistance());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		System.out.println("hi");
		return Robot.drivetrain.getEncoderDistance() >= distance || timeElapsed >= time ;
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
		this.output = output;
		System.out.println("hi");
		Robot.drivetrain.tankDrive(speed + output , speed - output);
	}
}

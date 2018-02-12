package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightWithRampingWithLineSensorCommand extends DriveStraightWithRampingCommand {
	double output;
	double offset = 122;
	double valueAtLine = 0;
	public DriveStraightWithRampingWithLineSensorCommand(double distance, double speed,double offset) {
		super(distance,speed);
		this.offset = offset;
	}

	// Called just before this Command runs the first time
	
	protected void initialize() {
		super.initialize();
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		super.execute();
		if  (Robot.drivetrain.isOnLine()) {
			valueAtLine = Robot.drivetrain.getEncoderDistance() - offset;
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (Robot.drivetrain.getEncoderDistance() >= super.targetDistance + startEncoderValue + valueAtLine);
	}

	// Called once after isFinished returns true
	protected void end() {
		super.end();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	@Override
	protected void usePIDOutput(double output) {
		super.usePIDOutput(output);
	}
}

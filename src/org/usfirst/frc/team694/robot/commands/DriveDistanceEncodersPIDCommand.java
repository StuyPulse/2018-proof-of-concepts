package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** PID for Drive Distance.
 *
 *   We cannot use this, because it slips in the very beginning
 *
 */

public class DriveDistanceEncodersPIDCommand extends PIDCommand {
	private double output;
	private double targetDistance;

	public DriveDistanceEncodersPIDCommand(double targetDistance) {
		super(0, 0, 0);

		this.targetDistance = targetDistance;
		
		setSetpoint(targetDistance);
		requires(Robot.drivetrain);	
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.resetEncoders();
		this.getPIDController().setPID(
				SmartDashboard.getNumber("DriveDistanceEncodersPID P", 0), 
				SmartDashboard.getNumber("DriveDistanceEncodersPID I", 0), 
				SmartDashboard.getNumber("DriveDistanceEncodersPID D", 0)
				);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("[DriveDistanceEncodersCommand] distance:" + Robot.drivetrain.getEncoderDistance());
		SmartDashboard.putNumber("Right Distance", Robot.drivetrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Left Distance", Robot.drivetrain.getLeftEncoderDistance());
		SmartDashboard.putNumber("Velocity", Robot.drivetrain.getEncoderVelocity());
		SmartDashboard.putNumber("Output", output);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//return Robot.drivetrain.getEncoderDistance() > targetDistance;
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Timer.delay(2);
		SmartDashboard.putNumber("Right Distance", Robot.drivetrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Left Distance", Robot.drivetrain.getLeftEncoderDistance());
	}
	
	
	
	@Override
	protected double returnPIDInput() {
		return Robot.drivetrain.getEncoderDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drivetrain.tankDrive(output, output);
		this.output = output;
	}
}

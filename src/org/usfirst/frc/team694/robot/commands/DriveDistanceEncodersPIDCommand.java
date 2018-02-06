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
	private double startSeconds = 1.5;
	private double startEncoderValue;
	public DriveDistanceEncodersPIDCommand(double targetDistance) {
		super(0, 0, 0);
		this.targetDistance = targetDistance;
		Robot.drivetrain.leftFront.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.rightFront.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.leftRear.configOpenloopRamp(startSeconds, 0);
    	Robot.drivetrain.rightRear.configOpenloopRamp(startSeconds, 0);

		
		setSetpoint(targetDistance);
		requires(Robot.drivetrain);	
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		/*while (Robot.drivetrain.getEncoderDistance() != 0) {
			Robot.drivetrain.resetEncoders();
		}*/
		startEncoderValue = Robot.drivetrain.getEncoderDistance();
		System.out.println("hi");
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
		SmartDashboard.putNumber("startEncoder", startEncoderValue);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.drivetrain.getEncoderDistance() > targetDistance + startEncoderValue;
		//return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Timer.delay(2);
		SmartDashboard.putNumber("Right Distance", Robot.drivetrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Left Distance", Robot.drivetrain.getLeftEncoderDistance());
	}
	
	
	
	@Override
	protected double returnPIDInput() {
		return Robot.drivetrain.getEncoderDistance() - startEncoderValue;
	}

	@Override
	protected void usePIDOutput(double output) {
		if (!isFinished()) {
			Robot.drivetrain.tankDrive(output, output);
			this.output = output;
		}
	}
}

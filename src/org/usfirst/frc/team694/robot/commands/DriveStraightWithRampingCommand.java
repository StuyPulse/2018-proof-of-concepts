package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveStraightWithRampingCommand extends PIDCommand {
	
	private static final double PID_CLOSE_ENOUGH_THRESHOLD = 0.05;

	private double output;
	private double targetDistance;
	private double startEncoderValue;
	private static double angleOutput;
	private static int usePIDCounter = 0;
	PIDController gyroControl = new PIDController(
			SmartDashboard.getNumber("RotateDegreesPID P", 0), 
			SmartDashboard.getNumber("RotateDegreesPID I", 0), 
			SmartDashboard.getNumber("RotateDegreesPID D", 0), 
			new Source(),
			new Output()
	);
	public DriveStraightWithRampingCommand(double targetDistance, double rampSeconds) {
		super(0, 0, 0);
		this.targetDistance = targetDistance;
		Robot.drivetrain.leftFront.configOpenloopRamp(rampSeconds, 0);
    	Robot.drivetrain.rightFront.configOpenloopRamp(rampSeconds, 0);
    	Robot.drivetrain.leftRear.configOpenloopRamp(rampSeconds, 0);
    	Robot.drivetrain.rightRear.configOpenloopRamp(rampSeconds, 0);
		gyroControl.setSetpoint(0);
		requires(Robot.drivetrain);	
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.resetGyro();
		System.out.println("Init");
		startEncoderValue = Robot.drivetrain.getRightEncoderDistance();
		setSetpoint(targetDistance);
		this.getPIDController().setPID(
				SmartDashboard.getNumber("DriveDistanceEncodersPID P", 0), 
				SmartDashboard.getNumber("DriveDistanceEncodersPID I", 0), 
				SmartDashboard.getNumber("DriveDistanceEncodersPID D", 0)
				);
		gyroControl.enable();
		gyroControl.setPID(
				SmartDashboard.getNumber("RotateDegreesPID P", 0), 
				SmartDashboard.getNumber("RotateDegreesPID I", 0), 
				SmartDashboard.getNumber("RotateDegreesPID D", 0)
				);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//System.out.println("[DriveDistanceEncodersCommand] distance left:" + (targetDistance - Robot.drivetrain.getEncoderDistance()));
		SmartDashboard.putNumber("Right Distance", Robot.drivetrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Left Distance", Robot.drivetrain.getLeftEncoderDistance());
		SmartDashboard.putNumber("Velocity", Robot.drivetrain.getEncoderVelocity());
		SmartDashboard.putNumber("Output", output);
		SmartDashboard.putNumber("Angle", Robot.drivetrain.getGyroAngle());
		SmartDashboard.putNumber("Angle Output", angleOutput);
		SmartDashboard.putNumber("StartEncoderValue", startEncoderValue);
		//System.out.println(angleOutput);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (Robot.drivetrain.getRightEncoderDistance() > targetDistance && Math.abs(output) < PID_CLOSE_ENOUGH_THRESHOLD);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.tankDrive(0, 0);
		gyroControl.disable();
		SmartDashboard.putNumber("Right Distance", Robot.drivetrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Left Distance", Robot.drivetrain.getLeftEncoderDistance());
		System.out.println("END");
	}

	@Override
	protected double returnPIDInput() {
		return Robot.drivetrain.getRightEncoderDistance() + startEncoderValue;
	}
	protected double returnPIDInputGyro() {
		return Robot.drivetrain.getGyroAngle();
	}
	@Override
	protected void usePIDOutput(double output) {
		//if (!isFinished()) {
			Robot.drivetrain.tankDrive(output + DriveStraightWithRampingCommand.angleOutput, output - DriveStraightWithRampingCommand.angleOutput);
			this.output = output;
		//}
	}
	private class Source implements PIDSource {
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			return returnPIDInputGyro();
		}
		
	}
	private class Output implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			DriveStraightWithRampingCommand.angleOutput = output;
		}
		
	}
}

//Values: For a distance of 170, a P of 0.0075, and a D of 0.005
//For a distance of 290, a P of 0.007 and a D of 0.02
//For turning, make it 0.05

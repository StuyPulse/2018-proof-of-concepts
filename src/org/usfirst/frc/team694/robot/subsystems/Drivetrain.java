package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.LineSensorSystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem {

	public WPI_TalonSRX leftFront;
	public WPI_TalonSRX rightFront;
	public WPI_TalonSRX leftRear;
	public WPI_TalonSRX rightRear;
	
	private Encoder rightEncoder;
	private Encoder leftEncoder;

	private ADXRS450_Gyro gyro;

	private SpeedControllerGroup leftSpeedController;
	private SpeedControllerGroup rightSpeedController;
	
    private DigitalInput leftLineSensor;
    private DigitalInput rightLineSensor;
    private LineSensorSystem lineSensorSystem;

	public Drivetrain() {
		leftFront = new WPI_TalonSRX(4);
		leftRear = new WPI_TalonSRX(3);
		leftSpeedController = new SpeedControllerGroup(leftFront, leftRear);

		rightFront = new WPI_TalonSRX(1);
		rightRear = new WPI_TalonSRX(2);
		rightSpeedController = new SpeedControllerGroup(rightFront, rightRear);

		
		leftFront.setInverted(true);
		leftRear.setInverted(true);
		//rightFront.setInverted(true);
		//rightRear.setInverted(true);
		gyro = new ADXRS450_Gyro();
        gyro.reset();
        gyro.calibrate();
        
		leftFront.setNeutralMode(NeutralMode.Brake);
		leftRear.setNeutralMode(NeutralMode.Brake);
		rightFront.setNeutralMode(NeutralMode.Brake);
		rightRear.setNeutralMode(NeutralMode.Brake);
//		new DifferentialDrive(leftSpeedController, rightSpeedController);

        leftLineSensor = new DigitalInput(RobotMap.DRIVETRAIN_LINE_SENSOR_LEFT_PORT);
        rightLineSensor = new DigitalInput(RobotMap.DRIVETRAIN_LINE_SENSOR_RIGHT_PORT);
        lineSensorSystem = new LineSensorSystem(leftLineSensor,rightLineSensor);
		
		rightEncoder = new Encoder(2, 3);
		leftEncoder = new Encoder(0, 1);
		leftEncoder.setReverseDirection(true);
        leftEncoder.setDistancePerPulse(RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);
        rightEncoder.setDistancePerPulse(RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);

        leftFront.configOpenloopRamp(0, 0);
        leftRear.configOpenloopRamp(0, 0);
        rightFront.configOpenloopRamp(0, 0); 
        rightRear.configOpenloopRamp(0, 0);
	}
	@Override
    public void periodic() {
        lineSensorSystem.mainLoop();
    }
    public boolean isOnLine() {
        return lineSensorSystem.basicFind();
    }

	public void tankDrive(double left, double right) {
		leftFront.set(ControlMode.PercentOutput, left);
		leftRear.set(ControlMode.PercentOutput,left);
		rightFront.set(ControlMode.PercentOutput,right);
		rightRear.set(ControlMode.PercentOutput,right);
		//FIXME: This doesn't work: differentialDrive.tankDrive(left, right);
	}
	public void motionMagic(double targetPosition) {
		Robot.drivetrain.leftFront.set(ControlMode.MotionMagic, targetPosition);
		Robot.drivetrain.leftRear.set(ControlMode.MotionMagic, targetPosition);
		Robot.drivetrain.rightFront.set(ControlMode.MotionMagic, targetPosition);
		Robot.drivetrain.rightRear.set(ControlMode.MotionMagic, targetPosition);
	}
	public void distanceTuning(double targetPosition) {
		Robot.drivetrain.leftFront.set(ControlMode.Position, targetPosition);
		Robot.drivetrain.leftRear.set(ControlMode.Follower, targetPosition);
		Robot.drivetrain.rightFront.set(ControlMode.Position, targetPosition);
		Robot.drivetrain.rightRear.set(ControlMode.Follower, targetPosition);
	}
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	public void resetGyro() {
		gyro.reset();
	}
	public double getGyroAngle() {
		return gyro.getAngle();
	}

	public double getLeftEncoderDistance() {
		return leftEncoder.getDistance();
		//return (leftEncoder.getDistance() * RobotMap.DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION) / RobotMap.DRIVETRAIN_ENCODERS_FACTOR;
		//return (leftEncoder.getDistance() * RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);
	}

	public double getRightEncoderDistance() {
		return rightEncoder.getDistance();
		//return (rightEncoder.getDistance() * RobotMap.DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION) / RobotMap.DRIVETRAIN_ENCODERS_FACTOR;
		//return (rightEncoder.getDistance() * RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);
	}

	public double getEncoderDistance() {
			return Math.max(getLeftEncoderDistance() , getRightEncoderDistance()  );
	}
	public double getEncoderVelocity() {
		return Math.max(rightEncoder.getRate(), leftEncoder.getRate());
	}


	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
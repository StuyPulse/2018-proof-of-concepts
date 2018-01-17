package org.usfirst.frc.team694.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem {

	private WPI_TalonSRX leftFront;
	private WPI_TalonSRX rightFront;
	private WPI_TalonSRX leftRear;
	private WPI_TalonSRX rightRear;
	
	private Encoder rightEncoder;
	private Encoder leftEncoder;

	private ADXRS450_Gyro gyro;

	private DifferentialDrive differentialDrive;

	private SpeedControllerGroup leftSpeedController;
	private SpeedControllerGroup rightSpeedController;

	public Drivetrain() {
		leftFront = new WPI_TalonSRX(4);
		leftRear = new WPI_TalonSRX(3);
		leftSpeedController = new SpeedControllerGroup(leftFront, leftRear);

		rightFront = new WPI_TalonSRX(1);
		rightRear = new WPI_TalonSRX(2);
		rightSpeedController = new SpeedControllerGroup(rightFront, rightRear);

		leftFront.setInverted(true);
		leftRear.setInverted(true);
		rightFront.setInverted(true);
		rightRear.setInverted(true);

		leftFront.setNeutralMode(NeutralMode.Brake);
		leftRear.setNeutralMode(NeutralMode.Brake);
		rightFront.setNeutralMode(NeutralMode.Brake);
		rightRear.setNeutralMode(NeutralMode.Brake);

		differentialDrive = new DifferentialDrive(leftSpeedController, rightSpeedController);

		rightEncoder = new Encoder(2, 3);
		leftEncoder = new Encoder(0, 1);
		leftEncoder.setDistancePerPulse(8 * Math.PI / 360);
		rightEncoder.setDistancePerPulse(8 * Math.PI / 360);

		gyro = new ADXRS450_Gyro();
        gyro.reset();
        gyro.calibrate();
	}

	public void tankDrive(double left, double right) {
		differentialDrive.tankDrive(left, right);
	}

	public double getLeftEncoderDistance() {
		return 0;//FIXME: leftEncoder.getDistance()
	}

	public double getRightEncoderDistance() {
		return 0;//FIXME: leftEncoder.getDistance()
	}

	public double getEncoderDistance() {
		return Math.max(getLeftEncoderDistance(), getRightEncoderDistance());
	}

	public double getGyroAngle() {
		return gyro.getAngle();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
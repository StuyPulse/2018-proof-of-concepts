package org.usfirst.frc.team694.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class fakeDrive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	WPI_TalonSRX motor;
	
	public fakeDrive() {
		motor = new WPI_TalonSRX(2);
	}
	
	public String coolQuote() {
		return "I find your infatuation with a mouse disturbing";
	}
	
	public void moveSlowly() {
		motor.set(ControlMode.PercentOutput, 0.1);
	}
	
	public void moveMiddly() {
		motor.set(ControlMode.PercentOutput, 0.5);
	}
	
	public void moveQuickly() {
		motor.set(ControlMode.PercentOutput, 1);
	}
	
	public void moveStop() {
		motor.stopMotor();
	}
	
	public double getMotorSpeed() {
		return motor.getMotorOutputPercent();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


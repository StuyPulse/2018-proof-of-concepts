package org.usfirst.frc.team694.robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class randomCommandTwo extends InstantCommand {

    public randomCommandTwo() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	SmartDashboard.putNumber("What is the answer to the ultimate question?",42);
    }

}

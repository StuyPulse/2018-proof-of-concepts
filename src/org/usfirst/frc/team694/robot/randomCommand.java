package org.usfirst.frc.team694.robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class randomCommand extends InstantCommand {

    public randomCommand() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	System.out.println("Fake News");
    }

}

package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistanceSonarCommand extends Command {
	private double distance;
	private double newDistance;
    public DriveDistanceSonarCommand() {
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ultra.setAutomaticMode(true);
    	distance = Robot.ultra.getRangeInches();
		System.out.println(distance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.moveRobot(-1);
    	newDistance = Robot.ultra.getRangeInches();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {      
    	if (newDistance - distance == Robot.driveDistanceInches) {
   	 		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

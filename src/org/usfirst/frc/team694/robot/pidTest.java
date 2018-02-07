package org.usfirst.frc.team694.robot;


import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class pidTest extends PIDCommand {
	
    public pidTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(0,0,0);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	/*this.getPIDController().setPID(
    			SmartDashboard.getNumber("P", 0),
    			SmartDashboard.getNumber("I", 0),
    			SmartDashboard.getNumber("D", 0)
    			);*/
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
}

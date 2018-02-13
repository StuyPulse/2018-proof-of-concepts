package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RampingTurningTestingCommand extends CommandGroup {

    public RampingTurningTestingCommand() {
    	//addSequential(new ResetEncoders());
    	//addSequential(new WaitCommand(2));
    	addSequential(new DriveStraightWithRampingCommand(), 10);
    	//new MotionMagicCommand(30).start();
    	//new RotateDegreesPIDCommand(-210).start();
    	//new DriveStraightPIDCommand(200, 0.5).start();
    	//new RampingCommand(168, 0.75).start(); //Going 168 Inches DOES NOT work with full speed on DEStiny
    	//new TestTimeCommand().start();
    	//new DriveStraightWithRampingCommand(250, 0.7).start();
    	//new DriveDistanceEncodersPIDCommand(168).start();
    }
}

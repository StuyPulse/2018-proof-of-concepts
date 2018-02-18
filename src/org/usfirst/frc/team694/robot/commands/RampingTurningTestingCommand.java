package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RampingTurningTestingCommand extends CommandGroup {

    public RampingTurningTestingCommand() {
    	//addSequential(new ResetEncoders());
//    	addSequential(new DriveStraightWithRampingCommand(288), 10);
//    	addSequential(new WaitCommand(5));
//    	addSequential(new DriveStraightWithRampingCommand(-200), 10);
    	//new MotionMagicCommand(30).start();
    	addSequential(new DriveStraightWithRampingCommand(84));
    	addSequential(new RotateDegreesPIDCommand(180));
    	addSequential(new DriveStraightWithRampingCommand(84));
    	addSequential(new RotateDegreesPIDCommand(180));
    	//new DriveStraightPIDCommand(200, 0.5).start();
    	//new RampingCommand(168, 0.75).start(); //Going 168 Inches DOES NOT work with full speed on DEStiny
    	//new TestTimeCommand().start();
    	//new DriveStraightWithRampingCommand(250, 0.7).start();
    	//new DriveDistanceEncodersPIDCommand(168).start();
    }
}

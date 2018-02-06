package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RampingTurningTestingCommand extends CommandGroup {

    public RampingTurningTestingCommand() {
    	addSequential(new DriveStraightPIDCommand(200, 0.5));
    	Timer.delay(2);
    	addSequential(new DriveDistanceEncodersPIDCommand(-200));
    	//new MotionMagicCommand(30).start();
    	//new RotateDegreesPIDCommand(-210).start();
    	//new DriveStraightPIDCommand(200, 0.5).start();
    	//new RampingCommand(168, 0.75).start(); //Going 168 Inches DOES NOT work with full speed on DEStiny
    	//new TestTimeCommand().start();
    	//new DriveStraightWithRampingCommand(250, 0.7).start();
    	//new DriveDistanceEncodersPIDCommand(168).start();
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

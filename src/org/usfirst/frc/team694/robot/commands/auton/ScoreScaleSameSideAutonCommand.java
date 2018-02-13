package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScoreScaleSameSideAutonCommand extends CommandGroup {

	public ScoreScaleSameSideAutonCommand() {
		addSequential(new DriveStraightWithRampingWithLineSensorCommand(290,0.75,122));
		addSequential(new RotateDegreesPIDCommand(45));
	}
}

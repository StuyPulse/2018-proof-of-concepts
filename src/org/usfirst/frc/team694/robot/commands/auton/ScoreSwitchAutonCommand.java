package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.commands.DriveDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.RotateDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScoreSwitchAutonCommand extends CommandGroup {

	public ScoreSwitchAutonCommand() {
		addSequential(new DriveDistanceEncodersCommand(160));
		addSequential(new RotateDegreesPIDCommand(-90));
	}
}

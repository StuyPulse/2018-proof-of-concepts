package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ScoreScaleSameSideAutonCommand extends CommandGroup {

	public ScoreScaleSameSideAutonCommand() {
		addSequential(new DriveStraightWithRampingWithLineSensorCommand(SmartDashboard.getNumber("Offset", 122)));
		//addSequential(new RotateDegreesPIDCommand(45));
	}
}

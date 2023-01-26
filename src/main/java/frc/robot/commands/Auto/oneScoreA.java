package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Auto.AutoCommands.encDriveA;
import frc.robot.commands.Auto.AutoCommands.resetEncoders;
import frc.robot.subsystems.driveS;

public class oneScoreA extends SequentialCommandGroup {
    public oneScoreA(driveS drive) {
        super(
            Commands.sequence(
                new resetEncoders(drive),

                new encDriveA(drive, -50)
            )
        );
                
                    

                    

    }
}

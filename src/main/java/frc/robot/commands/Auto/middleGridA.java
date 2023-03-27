package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Auto.AutoCommands.autoIntake;
import frc.robot.commands.Auto.AutoCommands.encDriveA;
import frc.robot.commands.Auto.AutoCommands.liftA;
import frc.robot.commands.Macros.autoBalance;
import frc.robot.subsystems.driveS;
import frc.robot.subsystems.intakeS;
import frc.robot.subsystems.liftS;

public class middleGridA extends SequentialCommandGroup{


    public middleGridA(driveS drive, liftS lift, intakeS intake) {
        
        super(
            Commands.sequence(
                new liftA(lift, 2.5, false),
                new autoIntake(intake, 0.35, true),
                new liftA(lift, 1.5, true),
                new encDriveA(drive, -165.03, true),
                new encDriveA(drive, 65, true), 
                new autoBalance(drive, 10)

            )
        );

    }
}
package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.Auto.AutoCommands.autoIntake;
import frc.robot.commands.Auto.AutoCommands.encDriveA;
import frc.robot.commands.Auto.AutoCommands.liftA;
import frc.robot.commands.Auto.AutoCommands.resetEncoders;
import frc.robot.commands.Auto.AutoCommands.rotDriveA;
import frc.robot.commands.Macros.rotateIntake;
import frc.robot.subsystems.driveS;
import frc.robot.subsystems.intakeS;
import frc.robot.subsystems.liftS;

public class rightGridA extends SequentialCommandGroup{


    public rightGridA(driveS drive, intakeS intake, liftS lift) {
        super(
            Commands.sequence(
                //new resetEncoders(drive),

            //     new liftA(lift, 3, false),
            //     new autoIntake(intake, 1.5, true),
            //    new liftA(lift, 3, true)

                // new autoIntake(intake, 0.5, false),
                // new liftA(lift, 3, false),
                // new autoIntake(intake, 1.5, true),
                // new liftA(lift, 3, true), 
                // new rotateIntake(lift, 3),
                // new ParallelCommandGroup(new encDriveA(drive, -150, false), new autoIntake(intake, 3, false)),
                // new ParallelCommandGroup(new rotateIntake(lift, 3), new encDriveA(drive, 150, false)),
                // new liftA(lift, 1.5, false),
                // new autoIntake(intake, 1.5, true),
                // new liftA(lift, 1.5, true)

                new rotDriveA(drive, -90, false)
            )
        );
            

    }
}
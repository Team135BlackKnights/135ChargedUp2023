package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Auto.AutoCommands.autoIntake;
import frc.robot.commands.Auto.AutoCommands.encDriveA;
import frc.robot.commands.Auto.AutoCommands.waitAuto;
import frc.robot.commands.Macros.extendLift;
import frc.robot.commands.Macros.rotateIntake;
import frc.robot.subsystems.driveS;
import frc.robot.subsystems.intakeS;
import frc.robot.subsystems.liftS;

public class rightGridA extends SequentialCommandGroup{


    public rightGridA(driveS drive, intakeS intake, liftS lift) {
        super(
            Commands.sequence(

                new encDriveA(drive, 0, true),
                new ParallelCommandGroup(new extendLift(lift, 2), new autoIntake(intake, 1, false)),
                new waitAuto(0.2),
                new autoIntake(intake, 0.3, true),
                new extendLift(lift, 0),
                new ParallelCommandGroup(new rotateIntake(lift, 1.5), new encDriveA(drive, -190, true), new autoIntake(intake, 3, false)),
                new ParallelCommandGroup(new rotateIntake(lift, 1.5), new encDriveA(drive, 200, true), new autoIntake(intake, 3, false))
                //new extendLift(lift, 1),
                //new autoIntake(intake, 0.3, true)
                //new extendLift(lift, 0)
            )
        );
            

    }
}
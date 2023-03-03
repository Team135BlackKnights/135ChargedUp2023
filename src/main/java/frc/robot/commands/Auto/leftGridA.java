package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.Auto.AutoCommands.encDriveA;
import frc.robot.commands.Auto.AutoCommands.liftA;
import frc.robot.commands.Auto.AutoCommands.resetEncoders;
//import frc.robot.commands.Macros.extendLift;
import frc.robot.commands.Macros.rotateIntake;
import frc.robot.subsystems.driveS;
import frc.robot.subsystems.intakeS;
import frc.robot.subsystems.liftS;

public class leftGridA extends SequentialCommandGroup{

    public leftGridA(driveS drive, liftS lift, intakeS intake) {
        super(
            Commands.sequence(
                //new resetEncoders(drive),

                new encDriveA(drive, -150, false),
                new liftA(lift, 3, false)
            )
        );

    }
}
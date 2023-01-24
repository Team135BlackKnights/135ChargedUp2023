package frc.robot.commands.Auto.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.driveS;

public class resetEncoders extends CommandBase{
    driveS drive;

    public resetEncoders(driveS subsystem) {
        drive = subsystem;
    }

    @Override
    public void initialize() {
        drive.resetEncoders();
        addRequirements(drive);
    }
}

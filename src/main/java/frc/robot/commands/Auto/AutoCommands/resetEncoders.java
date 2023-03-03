package frc.robot.commands.Auto.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.driveS;

public class resetEncoders extends CommandBase{
    driveS drive;
    boolean isFinished = false;
    public resetEncoders(driveS subsystem) {
        drive = subsystem;
    }

    @Override
    public void initialize() {
        addRequirements(drive);
        drive.resetEncoders();
    }

    @Override
    public void execute() {
        isFinished = true;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

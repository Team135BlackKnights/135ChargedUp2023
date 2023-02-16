package frc.robot.commands.Macros;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.driveS;

public class autoBalance extends CommandBase{
    private final driveS drive;
    boolean isFinished;
    public autoBalance(driveS subsystem){
        drive = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        isFinished = false;
    }

    @Override
    public void execute() {
        if (drive.navx.getPitch() > 5) {
            drive.tankDrive(0.25, 0.25);
        } else if (drive.navx.getPitch() < -5) {
            drive.tankDrive(-0.25, -0.25);
        } else {
            drive.tankDrive(0, 0);
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        drive.tankDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

package frc.robot.commands.Macros;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.liftS;

public class extendLift extends CommandBase{
    public final liftS lift;
    boolean isFinished = false;
    public extendLift(liftS subsystem) {
        lift = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        isFinished = false;
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

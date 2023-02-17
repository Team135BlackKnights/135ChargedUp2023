package frc.robot.commands.Macros;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.liftS;

public class rotateIntake extends CommandBase{
    public final liftS lift;
    boolean isFinished = false;
    public rotateIntake(liftS subsystem) {
        lift = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        isFinished = false;
    }

    @Override
    public void execute() {
        /* * if (liftS.eTilt.getDistance() < 0.1) {
            while (liftS.eTilt.getDistance() < 28.2) {
                liftS.tilt.set(0.5);
            }
            isFinished = true;
        } else if (liftS.eTilt.getDistance() > 28.2) {
            while (liftS.eTilt.getDistance() > 0.1) {
                liftS.tilt.set(-0.5);
            }
            isFinished = true;
        } else {
            isFinished = true;
        }*/
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return isFinished;
    }   
}

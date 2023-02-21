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
        liftS.eTilt.setPosition(0);
        isFinished = false;
    }

    @Override
    public void execute() {
         /*if (lift.intakeRotated == false) {
            while (liftS.eTilt.getPosition() < 28.2) {
                liftS.tilt.set(0.5);
            }
            lift.intakeRotated = true;
            isFinished = true;
        } else if (lift.intakeRotated == true) {
            while (liftS.eTilt.getPosition() > -28.2) {
                liftS.tilt.set(-0.5);
            }
            lift.intakeRotated = false;
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

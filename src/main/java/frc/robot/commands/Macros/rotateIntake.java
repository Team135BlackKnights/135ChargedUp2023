package frc.robot.commands.Macros;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.liftS;

public class rotateIntake extends CommandBase{
    public final liftS lift;
    boolean isFinished = false;
    double time;
    Timer timer = new Timer();
    public rotateIntake(liftS subsystem, double m_time) {
        lift = subsystem;
        time = m_time;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        timer.start();
        isFinished = false;
    }

    @Override
    public void execute() { //-14
        if (timer.get() < time && lift.getIntakePosition() >= -14) { //upper
            lift.setTiltPower(-0.75);
        } else if (timer.get() < time && lift.getIntakePosition() < -14) { //lower
            lift.setTiltPower(0.75);
        }

        if (timer.get() > time) {
            isFinished = true;
        }

      /*    if (lift.intakeRotated == false) {
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
    public void end(boolean interrupted) {
        lift.setTiltPower(0);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }   
}

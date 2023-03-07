package frc.robot.commands.Macros;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.liftS;

public class rotateIntake extends CommandBase{
    public final liftS lift;
    boolean isFinished = false, goingDown;
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
        if (lift.getIntakePosition() >= -14) { //upper
            goingDown = true;
        } else { //lower
            goingDown = false;
        }
    }

    @Override
    public void execute() { //-14
        if (Math.abs(RobotContainer.controller2.getRightY()) > 0.3) {
            isFinished = true;
        }
        
        if (timer.get() < time && goingDown == true) { 
            lift.setTiltPower(-0.65);
        } else if (timer.get() < time && goingDown == false) { 
            lift.setTiltPower(0.9);
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
        timer.stop();
        timer.reset();
        lift.setTiltPower(0);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }   
}

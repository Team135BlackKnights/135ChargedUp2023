package frc.robot.commands.Macros;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.liftS;

public class cubeHeight extends CommandBase {
    public final liftS lift; //-16.3
    boolean isFinished = false;
    double time, tiltPower;
    Timer timer = new Timer();
    PIDController pidController = new PIDController(0.03, 0, 0);
    public cubeHeight(liftS subsystem, double m_time/*, double target */) {
        lift = subsystem;
      //target= m_target;
        time = m_time;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        timer.start();
        isFinished = false;
    }

    @Override
    public void execute() {
        if (timer.get() >= time) {
            isFinished = true;
        }

        if (Math.abs(RobotContainer.controller2.getRightY()) >= 0.15 || RobotContainer.controller2.getXButton()==true) {
            isFinished = true;
        }
        
        tiltPower = pidController.calculate(lift.getIntakePosition(),-16.3 /*m_target*/);

        lift.setTiltPower((tiltPower));

        SmartDashboard.putNumber("attempted cube power", tiltPower);
        SmartDashboard.putNumber("Cube Height Error", pidController.getPositionError());
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

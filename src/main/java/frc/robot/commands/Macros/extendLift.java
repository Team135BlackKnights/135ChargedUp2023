//Everything in targetCone is commented that is why this doesnt work

package frc.robot.commands.Macros;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.liftS;

public class extendLift extends CommandBase{
     public final liftS lift;
    boolean isFinished = false;
    double Desired, target, liftSpeed;
    double encoderValue = 0;
    PIDController pidController = new PIDController(0.008, 0.01, 0.005);
    public extendLift(liftS subsystem, double m_target) {
        lift = subsystem;
        target = m_target;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        // if (targetCone.targetType == 1 || targetCone.targetType == 0) {
        //     Desired = 43.8; //diagonal distance needed to extend
        // } else if (targetCone.targetType == 2) {
        //     Desired = 63.6; //diagonal distance needed to extend
        // } 
        if (target == 1) {
            Desired = 31.58;
        } else if (target == 2) {
            Desired = 53.33;
        } else if (target == 3) {
            Desired = 43.34;
        } else if (target == 4) {
            Desired = 18;
        } else {
            Desired = 0;
        }
        isFinished = false;
    }

    @Override
    public void execute() {
        if (Math.abs(RobotContainer.controller2.getLeftY()) > 0.15) {
            isFinished = true;
        }

        encoderValue = lift.getLiftPosition();

        liftSpeed = pidController.calculate(encoderValue, Desired);

        lift.setLiftFeedForward(0.65*liftSpeed);

        SmartDashboard.putNumber("lift error", pidController.getPositionError());

        if (Math.abs(pidController.getPositionError()) < 1) {
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        lift.setLiftFeedForward(0);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

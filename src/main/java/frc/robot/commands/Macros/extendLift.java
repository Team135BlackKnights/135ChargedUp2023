//Everything in targetCone is commented that is why this doesnt work

/*package frc.robot.commands.Macros;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.liftS;

public class extendLift extends CommandBase{
     public final liftS lift;
    boolean isFinished = false;
    double Desired;
    double encoderValue = 0;
    PIDController pidController = new PIDController(0.1, 0.1, 0);
    public extendLift(liftS subsystem) {
        lift = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        if (encoderValue < 2) {
            if (targetCone.targetType == 1 || targetCone.targetType == 0) {
                Desired = 43.8; //diagonal distance needed to extend
            } else if (targetCone.targetType == 2) {
                Desired = 63.6; //diagonal distance needed to extend
            } 
        } else {
            Desired = 0;
        }
        isFinished = false;
    }

    @Override
    public void execute() {

      //  encoderValue = ((liftS.eLeftLift.getDistance() + liftS.eRightLift.getDistance())/2);
        encoderValue = 1 * Math.PI /42; //1 is diameter in inches

        //liftS.lift.set(pidController.calculate(encoderValue, Desired));

        if (Math.abs(pidController.getPositionError()) < 1) {
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}*/

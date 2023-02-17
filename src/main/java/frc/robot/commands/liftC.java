package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.liftS;

public class liftC extends CommandBase{
    private final liftS lift;
    double Desired, pos;
    public liftC(liftS subsystem) {
        lift=subsystem;
        addRequirements(subsystem);    
         Desired = 200;
    }
    double tiltVal = liftS.eTilt.getDistance();

    PIDController pidController = new PIDController(.1, .1, 0);//placeholder vals

    public void execute(){
        if (RobotContainer.controller2.getPOV() == 0){
            liftS.extendedLift(.5, -.5);
        }
        else if (RobotContainer.controller2.getPOV() == 180){
            liftS.extendedLift(.3, -.3);
        }

        /*if (RobotContainer.controller2.getAButton()==true){
            liftS.tilt.set(0.2);
        }
        if (RobotContainer.controller2.getBButton()==true) {
            liftS.tilt.set(-0.2);
        }*/
}
}

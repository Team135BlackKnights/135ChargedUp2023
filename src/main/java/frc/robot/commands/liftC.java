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
        if (RobotContainer.controller2.getLeftBumper()){
            liftS.leftLift.set(.5);
            liftS.rightLift.set(-.5);
        }
        else if (RobotContainer.controller2.getRightBumper()){
            liftS.leftLift.set(.5);
            liftS.rightLift.set(-.5);
        }

        if (RobotContainer.controller2.getAButton()==true){
        liftS.tilt.set(pidController.calculate(tiltVal, Desired));
    }
}
}
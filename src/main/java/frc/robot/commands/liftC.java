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
    double tiltVal = liftS.eTilt.getPosition();

    PIDController pidController = new PIDController(.1, .1, 0);//placeholder vals

    public void execute(){
        

        if (RobotContainer.controller1.getRightBumperPressed()==true){
        liftS.tilt.set(pidController.calculate(tiltVal, Desired));
    }
}
}
// cope seethe and mald nish you bald ass motherfucker
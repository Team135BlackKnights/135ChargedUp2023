package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.liftS;

public class liftC extends CommandBase{
    private final liftS lift;
    double Desired, liftSpeed, liftPos;
    public liftC(liftS subsystem) {
        lift=subsystem;
        addRequirements(subsystem);    
         Desired = 200;
    }

    public void execute(){

        lift.setLiftFeedForward(-1 * RobotContainer.controller2.getLeftY());
        lift.setTiltPower(-1 * RobotContainer.controller2.getRightY());

    }
}
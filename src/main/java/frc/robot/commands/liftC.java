package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.liftS;

public class liftC extends CommandBase{
    private final liftS lift;
    double Desired, pos, liftSpeed;
    public liftC(liftS subsystem) {
        lift=subsystem;
        addRequirements(subsystem);    
         Desired = 200;
    }
   // double tiltVal = liftS.eTilt.getDistance();

    PIDController pidController = new PIDController(.1, .1, 0);//placeholder vals

    public void execute(){
        if (RobotContainer.controller2.getLeftY() < 0) {
            liftSpeed = (RobotContainer.controller2.getLeftY()/3);
        } else {
            liftSpeed = (RobotContainer.controller2.getLeftY()*.75);
        }
        lift.extendedLift(liftSpeed, -liftSpeed);

<<<<<<< Updated upstream
     /*    if (lift.rotateStop.get()) {
=======
        /*if (lift.rotateStop.get()) {
>>>>>>> Stashed changes
            liftS.eTilt.setPosition(0);
        }
        
        if (lift.rotateStop.get() == true && RobotContainer.controller2.getRightY() > 0) {
            liftS.tilt.set(0);
        }*/ if (liftS.eTilt.getPosition() <= 28.2 && RobotContainer.controller2.getRightY() < 0) {
            liftS.tilt.set(0);
        } else {*/
            liftS.tilt.set(-RobotContainer.controller2.getRightY()/2);
        SmartDashboard.putNumber("Lift", lift.eLeftLift.getPosition());
        SmartDashboard.putNumber("Intake Tilt", lift.eTilt.getPosition());
    }  
}
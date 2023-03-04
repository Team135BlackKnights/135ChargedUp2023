package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
   // double tiltVal = liftS.eTilt.getDistance();

   // PIDController pidController = new PIDController(.05, 0, 0);//placeholder vals

    public void execute(){

        lift.setLiftFeedForward(-1 * RobotContainer.controller2.getLeftY());
        lift.setTiltPower(-1 * RobotContainer.controller2.getRightY());


        //liftS.eLiftAverageDist = -liftS.eLeftLift.getPosition() * 1.5 * Math.PI /4;

        // if (RobotContainer.controller2.getLeftY() > 0 && liftS.eLiftAverageDist > 1) {
        //     liftSpeed = (RobotContainer.controller2.getLeftY() / 3);
        // } else if (RobotContainer.controller2.getLeftY() < 0 && liftS.eLiftAverageDist < 67) {
        //     liftSpeed = (RobotContainer.controller2.getLeftY() * .75);
        // } else if (liftS.eLiftAverageDist > 10 && liftS.eLiftAverageDist < 52) {
        //     // liftSpeed = ((0.00015*liftS.eLiftAverageDist*liftS.eLiftAverageDist) -
        //     // (0.00957*liftS.eLiftAverageDist) - 0.01546); //+ 0.02046);
        //     liftSpeed = ((-0.00035 * liftS.eLiftAverageDist) - 0.086);
        // } else {
        //     liftSpeed = 0; // pidController.calculate(liftS.eLeftLift.getVelocity(), 0);
        // }
        // liftS.lift.set(liftSpeed);

        
        /*if (lift.rotateStop.get()) {
            liftS.eTilt.setPosition(0);
        }
        
        if (lift.rotateStop.get() == true && RobotContainer.controller2.getRightY() > 0) {
            liftS.tilt.set(0);
        }*/ 
        //if (liftS.eTilt.getPosition() <= 28.2 && RobotContainer.controller2.getRightY() < 0) {
          //  liftS.tilt.set(0);
        //}  else {
            //liftS.tilt.set(-RobotContainer.controller2.getRightY()/2);
        //} 
}
}
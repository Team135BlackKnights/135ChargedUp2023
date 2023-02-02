package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.clawS;

public class clawC extends CommandBase{
    private final clawS claw;
    public clawC(clawS subsystem) {
        claw=subsystem;
        addRequirements(subsystem);
    }
        public void execute(){
          /*  if (RobotContainer.controller1.getAButton()==true){
                subsys1.motor1.set(1);
                subsys1.motor2.set(-1);

            }
            else if (RobotContainer.controller1.getBButton()==true){
                subsys1.motor1.set(-1);
                subsys1.motor.set(1);

            }*/
            if (RobotContainer.controller1.getRawAxis(2)>0){
            clawS.claw1.set(3*(RobotContainer.controller1.getLeftTriggerAxis())/4);
            //clawS.claw2.set((RobotContainer.controller2.getLeftTriggerAxis())/2);
            }
         else if (RobotContainer.controller1.getRawAxis(3)>0){

            clawS.claw1.set(-3*(RobotContainer.controller1.getRightTriggerAxis())/4);
            //clawS.claw2.set(-(RobotContainer.controller2.getRightTriggerAxis())/2);
            }
            else  {
                clawS.claw1.set(0);
                //clawS.claw2.set(0);
            }
        }
}

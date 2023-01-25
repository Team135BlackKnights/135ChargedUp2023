package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.subsys1;

public class comm1 extends CommandBase{
    private final subsys1 subsys;
    public comm1(subsys1 subsystem) {
        subsys=subsystem;
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
            subsys1.motor1.set((RobotContainer.controller1.getRawAxis(2))/2);
            subsys1.motor2.set(-(RobotContainer.controller1.getRawAxis(2))/2);
            }
         else if (RobotContainer.controller1.getRawAxis(3)>0){

            subsys1.motor1.set(-(RobotContainer.controller1.getRawAxis(3))/2);
            subsys1.motor2.set((RobotContainer.controller1.getRawAxis(3))/2);
            }
            else  {
               subsys1.motor1.set(0);
               subsys1.motor2.set(0);
            }
        }
}

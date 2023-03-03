package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.intakeS;

public class intakeC extends CommandBase{
    private final intakeS intake;
    public intakeC(intakeS subsystem) {
        intake = subsystem;
        addRequirements(subsystem);
    }
        public void execute(){
            if (RobotContainer.controller1.getRawAxis(2)>0){
                intakeS.intake.set((RobotContainer.controller1.getLeftTriggerAxis()));
            } else if (RobotContainer.controller1.getRawAxis(3)>0){
                intakeS.intake.set((-RobotContainer.controller1.getRightTriggerAxis()));
            } else {
                intakeS.intake.set(-.25);
            }
        }
}

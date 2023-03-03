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
                intakeS.intake.set((1.25*(RobotContainer.controller1.getLeftTriggerAxis())) - 0.25);
            } else if (RobotContainer.controller1.getRawAxis(3)>0){
                intakeS.intake.set((-.75*(RobotContainer.controller1.getRightTriggerAxis())) - 0.25);
            } else {
                intakeS.intake.set(-0.25);
            }
        }
}

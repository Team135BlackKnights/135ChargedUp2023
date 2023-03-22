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
          if (RobotContainer.jStick_Chooser.getSelected()==false){
            if (RobotContainer.controller1.getRawAxis(2)>0){
                intake.intake.set((RobotContainer.controller1.getLeftTriggerAxis()));
            } else if (RobotContainer.controller1.getRawAxis(3)>0){
                intake.intake.set((-RobotContainer.controller1.getRightTriggerAxis()));
            } else { //later get motor current and when it is at stall current then start passive intake
                intake.intake.set(-.1);
            }
        }
        if (RobotContainer.jStick_Chooser.getSelected()==true){
            if (RobotContainer.Ltl.getAsBoolean()){
                intake.intake.set((RobotContainer.leftJoystick.getRawAxis(3)+1/2));
            } else if (RobotContainer.Lbl.getAsBoolean()){
                intake.intake.set(-1*Math.abs(RobotContainer.leftJoystick.getRawAxis(3)+1/2));
            } else { //later get motor current and when it is at stall current then start passive intake
                intake.intake.set(-.1);
            }
        }
    }
}

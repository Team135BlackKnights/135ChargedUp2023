package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intakeS;

public class deployBarC extends CommandBase {
    public final intakeS intake;
    boolean isFinished = false;
    public deployBarC(intakeS subsystem) {
        intake = subsystem;
        addRequirements(subsystem);
    }
    public void initiate(){}
    public void execute(){
         if (intakeS.entrapBar.get()==Value.kOff){
            intakeS.entrapBar.set(Value.kForward);
         }
         else if (intakeS.entrapBar.get()==Value.kForward){
            intakeS.entrapBar.set(Value.kOff);
         }
         isFinished=true;
    }
}
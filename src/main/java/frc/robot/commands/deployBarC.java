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
    public void initiate(){
        isFinished = false;
    }
    public void execute(){
         if (intakeS.entrapBar.get()==Value.kReverse){
            intakeS.entrapBar.set(Value.kForward);
         } else if (intakeS.entrapBar.get()==Value.kForward){
            intakeS.entrapBar.set(Value.kReverse);
         } else {
            intakeS.entrapBar.set(Value.kReverse);
         }
         isFinished=true;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

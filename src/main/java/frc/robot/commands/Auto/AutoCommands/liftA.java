package frc.robot.commands.Auto.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.liftS;

public class liftA extends CommandBase{
    double time, testVar;
    Timer timer = new Timer();
    private final liftS lift;
    boolean isFinished = false;
    boolean reverse;
  
      public liftA(liftS subsystem, double m_time, boolean m_reverse) {
        time = m_time;
        reverse = m_reverse;
        lift = subsystem;
        addRequirements(subsystem);
    }
  @Override
  public void initialize(){
       timer.start();

  }
  @Override
  public void execute(){

      if (timer.get() < time && reverse==false) {
        if (lift.getLiftPosition() >= 45){
        lift.setLiftFeedForward(.285);
      } else {
        lift.setLiftFeedForward(.6);
      }
       //testVar = testVar + 1;
  }
    else if (timer.get()<time && reverse==true){
      lift.setLiftFeedForward(-.9);
     // testVar = testVar - 1;

    }
        else if (timer.get()>time){
          lift.setLiftFeedForward(0);
     // lift.setLiftFeedForward(0);
      isFinished=true;
    }

  }
  @Override
  public void end(boolean interrupted) {
      timer.stop();
      timer.reset();

  }

  @Override
  public boolean isFinished() {
      return isFinished;
  }
}
  
  
  
  

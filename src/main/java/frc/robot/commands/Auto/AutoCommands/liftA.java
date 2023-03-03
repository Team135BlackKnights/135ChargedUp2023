package frc.robot.commands.Auto.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.liftS;

public class liftA extends CommandBase{
    double time;
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
        lift.setLiftFeedForward(5);
  }
    else if (timer.get()<time && reverse==true){
      lift.setLiftFeedForward(-5);
    }
        else if (timer.get()>time){
      lift.setLiftFeedForward(0);
    }
  }
}
  
  
  
  

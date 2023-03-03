package frc.robot.commands.Auto.AutoCommands;

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
        liftS.setLiftFeedForward(5);
  }
    else if (timer.get()<time && reverse==true){
      liftS.setLiftFeedForward(-5);
    }
        else if (timer.get()>time){
      liftS.setLiftFeedForward(0);
    }
  }
}
  
  
  
  

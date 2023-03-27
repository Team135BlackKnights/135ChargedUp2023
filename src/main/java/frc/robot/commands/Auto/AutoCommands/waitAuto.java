package frc.robot.commands.Auto.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class waitAuto extends CommandBase{
    double time;
    Timer timer = new Timer();
    boolean isFinished = false;
    boolean reverse;

    public waitAuto(double m_time) {
        time = m_time;
    }

    @Override
    public void initialize() {
        timer.start();
    }

    @Override
    public void execute() {
        if (timer.get() > time) {
            isFinished = true;
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

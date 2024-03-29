package frc.robot.commands.Auto.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intakeS;

public class autoIntake extends CommandBase{
    double time;
    Timer timer = new Timer();
    private final intakeS intake;
    boolean isFinished = false;
    boolean reverse;

    public autoIntake(intakeS subsystem, double m_time, boolean m_reverse) {
        time = m_time;
        reverse = m_reverse;
        intake = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        timer.start();
        if (reverse != true) {
            intake.intake.set(-.4);
        } else {
            intake.intake.set(1);
        }
    }

    @Override
    public void execute() {
        if (timer.get() > time) {
            intake.intake.set(0);
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        timer.reset();
        intake.intake.set(0);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

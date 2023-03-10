package frc.robot.commands.Macros;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.driveC;
import frc.robot.subsystems.driveS;

public class autoBalance extends CommandBase{
    private final driveS drive;
    boolean isFinished;
    Timer timer = new Timer();
    double time;
    public autoBalance(driveS subsystem, double m_time){
        drive = subsystem;
        time = m_time;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        isFinished = false;
    }

    @Override
    public void execute() {

        if (Math.abs(RobotContainer.controller1.getRightY()) > 0.2 || Math.abs(RobotContainer.controller1.getLeftY()) > 0.2) {
            isFinished = true;
        }

        if (timer.get() >= time) {
            isFinished = true;
        }

        if (drive.navx.getRoll() > 10) {
            drive.tankDrive(0.35, 0.35);
            timer.stop();
            timer.reset();
        } else if (drive.navx.getRoll() < -10) {
            drive.tankDrive(-0.35, -0.35);
            timer.stop();
            timer.reset();
        } else {
            drive.tankDrive(0, 0);
            timer.start();
        }
    }

    @Override
    public void end(boolean interrupted) {
        drive.tankDrive(0, 0);
        drive.motorBrake();
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

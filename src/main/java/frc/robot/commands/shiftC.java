package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.driveS;

public class shiftC extends CommandBase{
    private final driveS drive;
    double threshold;
    boolean position;
    Value output;
    public shiftC(driveS subsystem) {
        // Use addRequirements() here to declare subsystem dependencies.
        drive = subsystem;
        addRequirements(subsystem);
      }       

    @Override
    public void initialize() {
        position = true;
        driveS.shifting(position);
        }

    @Override
    public void execute() {

        if (RobotContainer.controller1.getLeftBumper()) {
            position = false;
        } 

        if (RobotContainer.controller1.getRightBumper()) {
            position = true;
        }

        driveS.shifting(position);
        SmartDashboard.putBoolean("position", position);
        SmartDashboard.putNumber("Pressure", driveS.pressure);
    }

    @Override
    public void end(boolean interrupted) {}
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}

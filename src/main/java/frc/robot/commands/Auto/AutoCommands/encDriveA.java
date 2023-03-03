package frc.robot.commands.Auto.AutoCommands;

import javax.swing.text.Position;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.driveC;
import frc.robot.subsystems.driveS;

public class encDriveA extends CommandBase {
  private final driveS drive;
  PIDController pidController = new PIDController(.008, .004, 0);
  public boolean isFinished= false;


  boolean Gear;
  double Desired;

  public encDriveA(driveS subsystem, double desDis, boolean gear) { //desired Distance
    Desired=desDis;
    Gear = gear;
    drive = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    driveS.shifting(Gear);
    drive.resetEncoders();
  }
        
  @Override 
  public void execute() {
    
    double autoDriveSpeed = 1 * pidController.calculate(drive.getDrivePos(), Desired);

    drive.tankDrive(/*(100)*/.5*autoDriveSpeed, .5 * autoDriveSpeed);

    SmartDashboard.putNumber("position error", pidController.getPositionError());
    SmartDashboard.putNumber("auto drive speed", autoDriveSpeed);
    
    if ((pidController.getPositionError()) > -1) {
      isFinished = true;
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

package frc.robot.commands.Auto.AutoCommands;

import javax.swing.text.Position;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.driveC;
import frc.robot.subsystems.driveS;

public class rotDriveA extends CommandBase {
  private final driveS drive;
  PIDController pidController = new PIDController(.012, .004, 0);
  public boolean isFinished= false;


  boolean Gear;
  double Desired;

  public rotDriveA(driveS subsystem, double desAng, boolean gear) { //desired Angle
    Desired=desAng;
    Desired = Desired * Math.PI * 27.5 / 360;
    Gear = gear;
    drive = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    drive.motorCoast();
    driveS.shifting(Gear);
    drive.resetEncoders();
  }
        
  @Override 
  public void execute() {
    
    double autoDriveSpeed = 1 * pidController.calculate(drive.getRightDrivePos(), Desired);

    drive.tankDrive(/*(100)*/-.5*autoDriveSpeed, .5 * autoDriveSpeed);

    SmartDashboard.putNumber("position error", pidController.getPositionError());
    SmartDashboard.putNumber("auto drive speed", autoDriveSpeed);
    
    if ((pidController.getPositionError()) > -0.2) {
      isFinished = true;
    } 
  }
  @Override
  public void end(boolean interrupted) {
    drive.motorBrake();
    drive.tankDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}

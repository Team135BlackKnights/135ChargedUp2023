package frc.robot.commands.Auto.AutoCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
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
    drive.motorCoast();
    driveS.shifting(Gear);
    drive.resetEncoders();
    drive.navx.zeroYaw();
  }
        
  @Override 
  public void execute() {
    if (driveS.shifter.get() == Gear) {
    double autoDriveSpeed = 1 * pidController.calculate(drive.getDrivePos(), Desired);
    double angleOffset = drive.navx.getYaw();
    double angleCorrection = angleOffset/90;

    drive.tankDrive(((1.0085*.79*autoDriveSpeed) + angleCorrection), .79*autoDriveSpeed);

    SmartDashboard.putNumber("left drive position", drive.getLeftDrivePos());
    SmartDashboard.putNumber("right drive position", drive.getRightDrivePos());
    SmartDashboard.putNumber("position error", pidController.getPositionError());
    SmartDashboard.putNumber("auto drive speed", autoDriveSpeed);
    SmartDashboard.putNumber("angle offset", angleOffset);

    if (Math.abs(pidController.getPositionError()) < 1) {
      isFinished = true;
    } 
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

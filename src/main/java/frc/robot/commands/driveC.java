package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.driveS;

public class driveC extends CommandBase{
  private final driveS drive;
  boolean position;
  private boolean coasting = false;
  public driveC(driveS subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive = subsystem;
    addRequirements(subsystem);
  }     

  @Override
  public void initialize() {
  }

  @Override
  public void execute(){
    driveS.pCompress.enableDigital();
    if (RobotContainer.controller1.getLeftBumper()) {
      position = false;
      drive.shifting(position);
      drive.motorCoast();
    }

    if (RobotContainer.controller1.getRightBumper()) {
      position = true;
      drive.shifting(position);
      drive.motorBrake();
    }

    if (Math.abs(RobotContainer.controller1.getLeftY()) > 0.15 || Math.abs(RobotContainer.controller1.getRightY()) > 0.15) {
      if (coasting != true) {
        drive.motorCoast();
        coasting = true;
      }
    }
    
    SmartDashboard.putNumber("NavX Tilt", drive.navx.getPitch());
    
    double left = RobotContainer.controller1.getLeftY();
    double right = RobotContainer.controller1.getRightY();

    drive.tankDrive(left, right);

    SmartDashboard.putNumber("left drive teleop position", drive.getLeftDrivePos());
    SmartDashboard.putNumber("right drive teleop position", drive.getRightDrivePos());
    SmartDashboard.putNumber("drive teleop position", drive.getDrivePos());
    SmartDashboard.putNumber("tx", driveS.limelight.getEntry("tx").getDouble(0.0));
    SmartDashboard.putNumber("tv", driveS.limelight.getEntry("tv").getDouble(0.0));
    SmartDashboard.putNumber("pipeline", driveS.limelight.getEntry("pipeline").getDouble(0.0));
    SmartDashboard.putNumber("NavX Yaw", drive.navx.getYaw());
  }
  
}
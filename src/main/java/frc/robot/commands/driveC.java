package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.driveS;

public class driveC extends CommandBase{
  private final driveS drive;
  public static boolean position;
  public driveC(driveS subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive = subsystem;
    addRequirements(subsystem);
  }     

  @Override
  public void initialize() {
    drive.motorCoast();
  }

  @Override
  public void execute(){
    driveS.pCompress.enableDigital();
    if (RobotContainer.controller1.getLeftBumper()) {
      position = false;
    }

    if (RobotContainer.controller1.getRightBumper()) {
      position = true;
    }

    driveS.shifting(position);
    SmartDashboard.putBoolean("Gear", position);
    SmartDashboard.putNumber("navx", drive.navx.getPitch());
    
   // driveS.pCompress.enableAnalog(50, 120);
    double left = RobotContainer.controller1.getLeftY();
    double right = RobotContainer.controller1.getRightY();
    drive.tankDrive(left, right);
    //SmartDashboard.putNumber("volt reader", DriverStation.getBatteryVoltage());
    


   // SmartDashboard.putNumber("Gyro", driveS.gybro.getAngle());
  }
  
}
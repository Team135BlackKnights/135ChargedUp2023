package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.Macros.autoBalance;
import frc.robot.subsystems.driveS;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class driveC extends CommandBase{
  private final driveS drive;
  public static boolean position;
  public static boolean balanceIsFinished = true;
  public driveC(driveS subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive = subsystem;
    addRequirements(subsystem);
  }     

  @Override
  public void initialize() {
    //drive.motorCoast();
  }

  @Override
  public void execute(){
    driveS.pCompress.enableDigital();
    if (RobotContainer.controller1.getLeftBumper()) {
      position = false;
      driveS.shifting(position);
    }

    if (RobotContainer.controller1.getRightBumper()) {
      position = true;
      driveS.shifting(position);
    }

    
    SmartDashboard.putBoolean("Gear", position);
    SmartDashboard.putNumber("navx", drive.navx.getRoll());
    
   // driveS.pCompress.enableAnalog(50, 120);
    double left = RobotContainer.controller1.getLeftY();
    double right = RobotContainer.controller1.getRightY();
   // if (balanceIsFinished != false) {
    drive.tankDrive(left, right);
  //  }
    SmartDashboard.putNumber("tx", driveS.limelight.getEntry("tx").getDouble(0.0));
    SmartDashboard.putNumber("tv", driveS.limelight.getEntry("tv").getDouble(0.0));
    SmartDashboard.putNumber("pipeline", driveS.limelight.getEntry("pipeline").getDouble(0.0));
        
    //SmartDashboard.putNumber("volt reader", DriverStation.getBatteryVoltage());
    


   // SmartDashboard.putNumber("Gyro", driveS.gybro.getAngle());
  }
  
}
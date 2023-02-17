package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
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

  public void execute(){
    driveS.pCompress.enableDigital();
   // driveS.pCompress.enableAnalog(50, 120);
    double left = RobotContainer.controller1.getLeftY();
    double right = RobotContainer.controller1.getRightY();
    drive.tankDrive(left,right);
    if (RobotContainer.controller1.getAButton()){
      
    }

    if (RobotContainer.controller1.getLeftTriggerAxis() > 0.1) {
      position = false;
    }

    if (RobotContainer.controller1.getRightTriggerAxis() > 0.1) {
      position = true;
    }

    driveS.shifting(position);
    SmartDashboard.putBoolean("position", position);

    SmartDashboard.putNumber("gyro", driveS.gybro.getAngle());
  }
  
}
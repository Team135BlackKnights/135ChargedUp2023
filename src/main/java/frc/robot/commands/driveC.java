package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.driveS;

public class driveC extends CommandBase{
  private final driveS drive;
  public driveC(driveS subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive = subsystem;
    addRequirements(subsystem);
  }       

  public void execute(){

    double left = RobotContainer.controller1.getLeftY();
    double right = RobotContainer.controller1.getRightY();
    drive.tankDrive(left,right);
    if (RobotContainer.controller1.getAButton()){
      
    }

    SmartDashboard.putNumber("gyro", driveS.gybro.getAngle());
  }
  
}
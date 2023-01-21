package frc.robot.commands;

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
    double left = RobotContainer.controller1.getRawAxis(1);
    double right = RobotContainer.controller1.getRawAxis(5);
    drive.tankDrive(left,right);

    NetworkTable LauncherLimeLightTable = NetworkTableInstance.getDefault().getTable("limelight-launch");
    if (RobotContainer.controller1.getAButtonPressed()){
    LauncherLimeLightTable.getEntry("pipeline").setNumber(0);
    }
    if (RobotContainer.controller1.getXButton()) {
      LauncherLimeLightTable.getEntry("pipeline").setNumber(1);
    }
  }

}
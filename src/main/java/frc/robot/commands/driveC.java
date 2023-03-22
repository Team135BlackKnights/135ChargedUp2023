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
  double left, right;
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
    if (RobotContainer.controller1.getLeftBumper()|| RobotContainer.Rtr.getAsBoolean()) {
      position = false;
      driveS.shifting(position);
    }

    if (RobotContainer.controller1.getRightBumper()|| RobotContainer.Rbr.getAsBoolean()) {
      position = true;
      driveS.shifting(position);
    }
    if (Math.abs(RobotContainer.controller1.getLeftY()) > 0.15 || Math.abs(RobotContainer.controller1.getRightY()) > 0.15 || Math.abs(RobotContainer.leftJoystick.getRawAxis(4))>.15 || Math.abs(RobotContainer.rightJoystick.getRawAxis(4))>.15) {
      if (coasting != true) {
        drive.motorCoast();
        coasting = true;
      }
    }
    
    SmartDashboard.putNumber("NavX Tilt", drive.navx.getRoll());

  if (RobotContainer.jStick_Chooser.getSelected()==true){
     left = RobotContainer.leftJoystick.getRawAxis(4);
     right = RobotContainer.controller1.getRawAxis(4);
  }
  else{ 
    left = RobotContainer.controller1.getLeftY();
    right = RobotContainer.controller1.getRightY();
  }
    drive.tankDrive(left, right);

    SmartDashboard.putNumber("tx", driveS.limelight.getEntry("tx").getDouble(0.0));
    SmartDashboard.putNumber("tv", driveS.limelight.getEntry("tv").getDouble(0.0));
    SmartDashboard.putNumber("pipeline", driveS.limelight.getEntry("pipeline").getDouble(0.0));
        
  }
  
}
package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.driveS;

public class targetCone extends CommandBase{
    private final driveS drive;
  
    public targetCone(driveS subsystem) {
      // Use addRequirements() here to declare subsystem dependencies.
      drive = subsystem;
      addRequirements(subsystem);
    }       
    NetworkTable LauncherLimeLightTable = NetworkTableInstance.getDefault().getTable("limelight-launch");
    NetworkTableEntry Tx = LauncherLimeLightTable.getEntry("tx");
    NetworkTableEntry Tv = LauncherLimeLightTable.getEntry("tv");
    NetworkTableEntry Ty = LauncherLimeLightTable.getEntry("ty");
    boolean isFinished;


    double angY, angX, dist;
    @Override 
    public void initialize(){
        boolean isFinished = false;
    }
    @Override
    public void execute(){
        angY= Ty.getDouble(0.0);

        dist = -2.5/Math.tan(Math.toRadians(angY));
        angX= Tx.getDouble(0.0);

        if (RobotContainer.controller1.getLeftBumper()){
        if (angX<-1){
            drive.tankDrive(-.1, -.1);
        }
        else if (angX>1){
            drive.tankDrive(.1, .1);
        }
        else
        isFinished=true;
    }
        SmartDashboard.putNumber("distance", dist);

    }
}

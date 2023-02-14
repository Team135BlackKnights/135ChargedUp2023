package frc.robot.commands.Macros;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.driveS;

public class selectionC extends CommandBase{
    private final driveS drive;
    public selectionC(driveS subsystem) {
      // Use addRequirements() here to declare subsystem dependencies.
      drive = subsystem;
      addRequirements(subsystem);
    }       
    public int gridx=1, gridy=1;
    public boolean selecting=false;
    public void initialize(){}

    public void execute() {
        if (RobotContainer.controller2.getXButton()){
            selecting=true;
        }else if (selecting==true && RobotContainer.controller2.getXButton()){
            selecting=false;
        }

        if (selecting==true && RobotContainer.controller2.getPOV()==0){
            gridy= gridy+1;
        }else if (selecting==true&& RobotContainer.controller2.getPOV()==90){
            gridx=gridx+1;
        }

        if (gridy==4){
            gridy=1;
        }
        if (gridx==4){
            gridx=1;
        }


        if (gridx==2){ //if center
            targetCone.targetType=0;
        }
        else if (gridx==1||gridx==3){ //if to cones
            targetCone.targetType=1;
            if (gridy==3){
                targetCone.targetType=2;
            }
        }
        
        SmartDashboard.putNumber("x", gridx);
        SmartDashboard.putNumber("y", gridy);
        
    }
}

package frc.robot.commands.Macros;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer; 
import frc.robot.subsystems.driveS;

public class targetCone extends CommandBase{
    private final driveS drive;
    double lProportional, lIntegral, rProportional, rIntegral, leftSpeed, rightSpeed, tx, ta;
    public static int targetType;
    boolean inRange, isFinished = false;
    NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-launch");
    NetworkTableEntry Tx = limelight.getEntry("tx");
    NetworkTableEntry Ta = limelight.getEntry("ta");
    double kP = 0.01, kI = 0;
    public targetCone(driveS subsystem, int target) {
      // Use addRequirements() here to declare subsystem dependencies.
      drive = subsystem;
      targetType = target;
      addRequirements(subsystem);
    }
    @Override
    public void execute(){
        NetworkTableInstance.getDefault().getTable("limelight-launch").getEntry("pipeline").setNumber(targetType);
    
        tx = limelight.getEntry("tx").getDouble(0.0);
        ta = limelight.getEntry("ta").getDouble(0.0);

        lProportional = tx * kP;
        rProportional = -tx * kP;
        lIntegral = -tx * kI;
        rIntegral = tx * kI;
        SmartDashboard.putBoolean("inRange", inRange);

        if (RobotContainer.controller1.getAButton()) {search();} 
        else if (RobotContainer.controller1.getXButton()) {destroy();} 

        drive.tankDrive(leftSpeed, rightSpeed);
        SmartDashboard.putNumber("tx", tx);
        SmartDashboard.putNumber("tv", limelight.getEntry("tv").getDouble(0.0));
        SmartDashboard.putNumber("pipeline", limelight.getEntry("pipeline").getDouble(0.0));
        SmartDashboard.putNumber("Target Type", targetType);
        
    }

    public double limit(double x, double upperLimit, double lowerLimit) {
    
        if (x > upperLimit) {
          return upperLimit;
        }
        else if (x < -upperLimit) {
          return -upperLimit;
        }
        else if (0 < x && x < lowerLimit) {
          return lowerLimit;
        }
        else if (-lowerLimit < x && x < 0) {
          return -lowerLimit;
        }
        else {
          return x;
        }
    }

    public void search() {
        if (targetType == 0) {
            if (tx > 2.5 || tx < 2.5) {
                leftSpeed = limit((lProportional + lIntegral), 0.75, 0.275);
                rightSpeed = -limit((rProportional + rIntegral), 0.75, 0.275);
            }

            if (tx < 2.45 && tx > -2.45) {isFinished = true;}
        } 
        else {
            if (tx > 1 || tx < -1) {
                leftSpeed = limit((lProportional + lIntegral), 0.75, 0.275);
                rightSpeed = -limit((rProportional + rIntegral), 0.75, 0.275);
            }

            if (tx < 0.95 && tx > -0.95) {isFinished = true;}
        }
    }

    public void destroy() {
        if (targetType == 0) {
            if (ta > 5) {
            inRange = true;
            } else {
            inRange = false;
            }
        }
        if (targetType == 1) {
            if (ta > 0.3) {
            inRange = true;
            } else {
            inRange = false;
            }
        }
        if (targetType == 2) {
            if (ta > 0.18) {
            inRange = true;
            } else {
            inRange = false;
            }
        }  
        if (inRange == false) {
            leftSpeed = -limit(0.3 + (-tx / 15), 0.4, 0.3);
            rightSpeed = limit(0.3 + (tx / 15), 0.4, 0.3);
        } 
        else {
            isFinished = true;
        } 
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
    
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return isFinished();
    }
}

package frc.robot.commands.Macros;

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
    boolean inRange = false, isRange = false, isFinished = false;
    NetworkTableEntry Tx = driveS.limelight.getEntry("tx");
    NetworkTableEntry Ta = driveS.limelight.getEntry("ta");
    double kP = 0.0325, kI = 0.0135, minRange = 5.5;
    int runs = 0, targetRuns = 0, searchRuns = 0, destroyRuns = 0;
    public targetCone(driveS subsystem, int m_targetType) {
      // Use addRequirements() here to declare subsystem dependencies.
      drive = subsystem;
      targetType = m_targetType;
      addRequirements(subsystem);
    }
    @Override
    public void execute(){
        isRange = false;
        isFinished = false;
        NetworkTableInstance.getDefault().getTable("limelight-target").getEntry("pipeline").setNumber(targetType);
    
        double Ttx = driveS.limelight.getEntry("tx").getDouble(0.0);
        ta = driveS.limelight.getEntry("ta").getDouble(0.0);
        
        tx = Ttx - rangeScale();
        SmartDashboard.putNumber("tx", tx);
        lProportional = tx * kP;
        rProportional = -tx * kP;
        lIntegral = -tx * kI;
        rIntegral = tx * kI;

        if (targetType == 0) {
            if (tx < 2.5 && tx > -2.5) {
                isRange = true;
            }
            else {
                isRange = false;
            }
        }
        else {
            if (tx < 1 && tx > -1) {
                isRange = true;
            }
            else {
                isRange = false;
            }
        }

        if (isRange == false) {
            if (targetType == 0) {
                if (tx > 2.5 || tx < -2.5) {
                    leftSpeed = limit((lProportional + lIntegral), 0.65, 0.3);
                    rightSpeed = -limit((rProportional + rIntegral), 0.65, 0.3);
                    drive.tankDrive(-leftSpeed, rightSpeed);
                }

                if (tx < 2.5 && tx > -2.5) {isRange = true;}
            } 
            else {
                if (tx < 0.1 && tx > -0.1) {isRange = true;}
                else if (tx < 8 && tx > -8) {
                    if (tx > 0) {leftSpeed = -0.3; rightSpeed = 0.3;}
                    if (tx < 0) {leftSpeed = 0.3; rightSpeed = -0.3;}
                }
                else if (tx > 8 || tx < -8) {
                    leftSpeed = limit((lProportional + lIntegral), 0.75, 0.3);
                    rightSpeed = -limit((rProportional + rIntegral), 0.75, 0.3);
                    drive.tankDrive(-leftSpeed, rightSpeed);
                }
            }
            if (RobotContainer.controller1.getAButton() == false) {isRange = true;}
        }
        runs += 1;
        SmartDashboard.putNumber("runs", runs);

        SmartDashboard.putBoolean("isRange", isRange);

        if (RobotContainer.controller1.getAButton() == false) {
            isFinished = true;
        }
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

    public double rangeScale() {
        double xDiff;
        if (targetType == 0) {xDiff = ta * -5;}//-2.4768
        else if (targetType == 1 || targetType == 2) {xDiff = ta * -23;}//-20.938
        else {xDiff = ta;}
        return xDiff;
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
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

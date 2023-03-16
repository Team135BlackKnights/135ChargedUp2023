package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class liftS extends SubsystemBase {
    private static CANSparkMax leftLift = new CANSparkMax(RobotMap.Lift.liftID_1, MotorType.kBrushless);
    private static CANSparkMax rightLift = new CANSparkMax(RobotMap.Lift.liftID_2, MotorType.kBrushless);
    private static CANSparkMax tilt = new CANSparkMax(RobotMap.Lift.tiltID, MotorType.kBrushless);
    private static MotorControllerGroup lift = new MotorControllerGroup(leftLift, rightLift);
    private static RelativeEncoder eTilt, eLeftLift, eRightLift;
    public static double eLiftLimit = 70, eLiftAverage, eLiftAverageDist, eLiftAveragePercent;

    final private double spoolDiameter = 1.51, gearRatio = 4.43;
    final private double conversionFactor = spoolDiameter*Math.PI/gearRatio;
    
    public liftS() {
        leftLift.enableVoltageCompensation(12);
        rightLift.enableVoltageCompensation(12);
        tilt.enableVoltageCompensation(12);

        leftLift.setInverted(true);
        rightLift.setInverted(true);
        leftLift.setIdleMode(IdleMode.kBrake);
        rightLift.setIdleMode(IdleMode.kBrake);
        tilt.setIdleMode(IdleMode.kBrake);
        tilt.setSmartCurrentLimit(40, 40);

        eTilt = tilt.getEncoder();
        eLeftLift = leftLift.getEncoder();
        eRightLift = rightLift.getEncoder();

        eLeftLift.setPositionConversionFactor(conversionFactor);
        eRightLift.setPositionConversionFactor(conversionFactor);

        eLeftLift.setVelocityConversionFactor(conversionFactor/ 60);
        eRightLift.setVelocityConversionFactor(conversionFactor/ 60);

        eTilt.setPosition(0);
        eLeftLift.setPosition(0);
        eRightLift.setPosition(0);

        tilt.burnFlash();
        leftLift.burnFlash();
        rightLift.burnFlash();
    }

    public void setLiftFeedForward(double desVel) { //desVel= desired Velocity
        eLiftAverageDist = eLeftLift.getPosition();
        double posFeedForward = (0.003 * eLeftLift.getPosition()) + 0.1; // position feed forward


        if (eTilt.getPosition() > -1) { //wont run unless intake is in
            if (desVel < 0) {
                desVel = desVel * 0.333;
            } else {
                desVel = desVel * 0.75;
            }


            if (desVel < 0)
            {   // soft stop on bottom of travel
                if (eLeftLift.getPosition() < 1)
                {   // 
                    desVel = 0;
                }
                else if (eLeftLift.getPosition() < 4)
                {
                    desVel = desVel * 0.333;
                }
            }
            else if (desVel > 0.1 && eLeftLift.getPosition() > 65)
            {   // soft stop on top of travel
                desVel = 0.1;
            }
        }
        lift.set(desVel);

        SmartDashboard.putNumber("Intake Tilt", eTilt.getPosition());
        SmartDashboard.putNumber("lift Position", eLeftLift.getPosition());
        SmartDashboard.putNumber("Lift Velocity", eLeftLift.getVelocity());
        SmartDashboard.putNumber("Lift Power", lift.get());
        SmartDashboard.putNumber("desVel", desVel);
    }

    public void setTiltPower(double desSpeed)
    {
        desSpeed = desSpeed * 0.35; // slow down power control for tilt

        if (desSpeed > 0)
        {   // up soft stop
            if (eTilt.getPosition() > -0.5)
            {   // hard stop
                desSpeed = 0;  
            }
            else if (eTilt.getPosition() > -6.5)
            {   // slow down
                desSpeed = desSpeed * 0.5;
            }
        }
        if (desSpeed < 0)
        {   // down soft stop
            if (eTilt.getPosition() < -25/*-23.5*/)
            {   // hard stop
                desSpeed = 0;  
            }
            else if (eTilt.getPosition() < -18/*-19.5*/)
            {   // slow down
                desSpeed = desSpeed * 0.5;
            }
        }
    
        // set the lift motor speed (power)
        tilt.set(desSpeed);

        SmartDashboard.putNumber("tilt Position", eTilt.getPosition());
        SmartDashboard.putNumber("tilt Speed", desSpeed);
    }

    public double getIntakePosition() {
        return eTilt.getPosition();
    }

    public double getLiftPosition() {
        return eLeftLift.getPosition();
    }

    public static double liftPercent() {
        eLiftAverageDist = -eLeftLift.getPosition() * 1.5 * Math.PI /4; // encoderLiftAverage * spool diameter * pi * gear ratio
        eLiftAveragePercent = 1/(eLeftLift.getPosition() / eLiftLimit);
        return eLiftAveragePercent;
    }

}
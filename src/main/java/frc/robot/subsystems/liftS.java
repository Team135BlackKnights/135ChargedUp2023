package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class liftS extends SubsystemBase {
    public static CANSparkMax leftLift = new CANSparkMax(RobotMap.Lift.liftID_1, MotorType.kBrushless);
    public static CANSparkMax rightLift = new CANSparkMax(RobotMap.Lift.liftID_2, MotorType.kBrushless);
    public static CANSparkMax tilt = new CANSparkMax(RobotMap.Lift.tiltID, MotorType.kBrushless);
    //public static Encoder eTilt, eLeftLift, eRightLift;
    public static RelativeEncoder eTilt, eLeftLift, eRightLift;
    public boolean intakeRotated = false;
    public static double eLiftLimit, eLiftAverage, eLiftAveragePercent;
    
    public liftS() {
        eTilt = tilt.getEncoder();
        eLeftLift = leftLift.getEncoder();
        eRightLift = rightLift.getEncoder();
        eTilt.setPosition(0);
        eLeftLift.setPosition(0);
        eRightLift.setPosition(0);

        tilt.setSmartCurrentLimit(40, 40);
        tilt.burnFlash();
    }

    public void extendedLift(double left, double right) {
        leftLift.set(left);
        rightLift.set(right);
    }

    public static double liftPercent() {
        eLiftAverage = (eLeftLift.getPosition() + eRightLift.getPosition()) / 2;
        eLiftAveragePercent = eLiftAverage / eLiftLimit;
        return eLiftAveragePercent;
    }
}
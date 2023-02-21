package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class liftS extends SubsystemBase {
    public static CANSparkMax leftLift = new CANSparkMax(RobotMap.Lift.liftID_1, MotorType.kBrushless);
    public static CANSparkMax rightLift = new CANSparkMax(RobotMap.Lift.liftID_2, MotorType.kBrushless);
    public static CANSparkMax tilt = new CANSparkMax(RobotMap.Lift.tiltID, MotorType.kBrushless);
   // public static Encoder eTilt, eLeftLift, eRightLift;
    public static RelativeEncoder eTilt;
    public boolean intakeRotated = false;
    
    public liftS() {
        eTilt = tilt.getEncoder();
        eTilt.setPosition(0);
       // eLeftLift.setReverseDirection(true);
        //eTilt= new Encoder(1,0, false, Encoder.EncodingType.k4X);
        //eTilt.setDistancePerPulse((24*Math.PI/42)); //24 is diamter in inches
        //eTilt.reset();
        //eLeftLift.setDistancePerPulse(1*Math.PI/42); //1 is diameter in inches
        //eRightLift.setDistancePerPulse(1*Math.PI/42);

        tilt.setSmartCurrentLimit(1, 1);
        tilt.burnFlash();
    }

    public void extendedLift(double left, double right) {
        leftLift.set(left);
        rightLift.set(right);
    }
}
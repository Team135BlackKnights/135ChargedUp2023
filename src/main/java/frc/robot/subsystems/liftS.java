package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class liftS extends SubsystemBase {
    CANSparkMax lift1 = new CANSparkMax(RobotMap.Lift.liftID_1, MotorType.kBrushless);
    CANSparkMax lift2 = new CANSparkMax(RobotMap.Lift.liftID_2, MotorType.kBrushless);
    public static CANSparkMax tilt = new CANSparkMax(RobotMap.Lift.tiltID, MotorType.kBrushless);
    public static  Encoder eTilt;
    
    public liftS() {
        eTilt= new Encoder(1,0, false, Encoder.EncodingType.k4X);
    }


}

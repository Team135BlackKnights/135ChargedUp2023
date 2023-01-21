package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class subsys1 extends SubsystemBase {
    public static CANSparkMax motor1 = new CANSparkMax(RobotMap.M1, MotorType.kBrushless);
    public static CANSparkMax motor2 = new CANSparkMax(RobotMap.M2, MotorType.kBrushless);

}

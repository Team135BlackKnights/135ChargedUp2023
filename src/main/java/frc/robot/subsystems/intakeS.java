package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class intakeS extends SubsystemBase {
    public static CANSparkMax intake = new CANSparkMax(RobotMap.Intake.intakeID_1, MotorType.kBrushless);
    //public static CANSparkMax claw2 = new CANSparkMax(RobotMap.Intake.intakeID_2, MotorType.kBrushless);

    public intakeS() {
     //   claw2.setInverted(true);
    }
}

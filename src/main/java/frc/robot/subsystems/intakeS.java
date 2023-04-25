package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class intakeS extends SubsystemBase {
    public CANSparkMax intake = new CANSparkMax(RobotMap.Intake.intakeID_1, MotorType.kBrushless);
    //public static DoubleSolenoid entrapBar = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.Intake.BAR_ID, RobotMap.Intake.BAR2_ID);

    public intakeS() {
        intake.setInverted(false);
        intake.setSmartCurrentLimit(40, 60);
        intake.setIdleMode(IdleMode.kBrake);
        intake.burnFlash();
    }
}

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class driveS extends SubsystemBase{
   public static DoubleSolenoid shifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.Drive.shiftSolenoid, RobotMap.Drive.shiftSolenoid2);

    public static PneumaticHub PneumHub= new PneumaticHub();
    public static double pressure = PneumHub.getPressure(0);

    public CANSparkMax FrontL = new CANSparkMax(RobotMap.Drive.FL_ID, MotorType.kBrushless);
    public CANSparkMax FrontR = new CANSparkMax(RobotMap.Drive.FR_ID, MotorType.kBrushless);
    public CANSparkMax BackL = new CANSparkMax(RobotMap.Drive.BL_ID, MotorType.kBrushless);
    public CANSparkMax BackR = new CANSparkMax(RobotMap.Drive.BR_ID, MotorType.kBrushless);
    public static RelativeEncoder elFront, elBack, erFront, erBack;

     MotorControllerGroup MCGleft = new MotorControllerGroup(FrontL, BackL);
     MotorControllerGroup MCGright = new MotorControllerGroup(FrontR, BackR);
    
    public DifferentialDrive tank = new DifferentialDrive(MCGleft, MCGright);

    public  void tankDrive(double left, double right) {
        tank.tankDrive(left,right);
    }
    public static void shifting(boolean position) {
        if (position == true) {
            shifter.set(Value.kForward);
          }
          else if (position == false) {
            shifter.set(Value.kReverse);
          }
    }

}
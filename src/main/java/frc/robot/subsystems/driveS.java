package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
//import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class driveS extends SubsystemBase{
  public static DoubleSolenoid shifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.Drive.shiftSolenoid, RobotMap.Drive.shiftSolenoid2);

  //public static PneumaticHub PneumHub= new PneumaticHub();
  //public static double pressure = PneumHub.getPressure(0);

  public CANSparkMax FrontL = new CANSparkMax(RobotMap.Drive.FL_ID, MotorType.kBrushless);
  public CANSparkMax FrontR = new CANSparkMax(RobotMap.Drive.FR_ID, MotorType.kBrushless);
  public CANSparkMax BackL = new CANSparkMax(RobotMap.Drive.BL_ID, MotorType.kBrushless);
  public CANSparkMax BackR = new CANSparkMax(RobotMap.Drive.BR_ID, MotorType.kBrushless);
  public static RelativeEncoder elFront;

  public static RelativeEncoder elBack;

  public static RelativeEncoder erFront;

  public static RelativeEncoder erBack;
  public static Gyro gybro = new ADXRS450_Gyro();

  public AHRS navx = new AHRS();
  public static Compressor pCompress = new Compressor(PneumaticsModuleType.REVPH);  //Digtial I/O,Relay

  MotorControllerGroup MCGleft = new MotorControllerGroup(FrontL, BackL);
  MotorControllerGroup MCGright = new MotorControllerGroup(FrontR, BackR);
    
  public DifferentialDrive tank = new DifferentialDrive(MCGleft , MCGright);

  public driveS() {
    MCGleft.setInverted(true);

  }

  public void tankDrive(double left, double right) {
    tank.tankDrive(left,right);
  }

  public void resetEncoders() {
    elFront.setPosition(0);
    elBack.setPosition(0);
    erFront.setPosition(0);
    erBack.setPosition(0);
  }

  public static void shifting(boolean position) {
    if (position == true) {
      shifter.set(Value.kForward);
 //     pressure = PneumHub.getPressure(0);
    } else if (position == false) {
      shifter.set(Value.kReverse);
 //     pressure = PneumHub.getPressure(0);
    }
  }
}
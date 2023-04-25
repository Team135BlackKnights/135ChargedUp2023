package frc.robot.subsystems;

//import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class driveS extends SubsystemBase{
  public static Solenoid shifter = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.Drive.shiftSolenoid);

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
  //public static Gyro gybro = new ADXRS450_Gyro();

  public AHRS navx = new AHRS(Port.kUSB1);
  public static Compressor pCompress = new Compressor(PneumaticsModuleType.REVPH);  //Digtial I/O,Relay
  public static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-target");
  MotorControllerGroup MCGleft = new MotorControllerGroup(FrontL, BackL);
  MotorControllerGroup MCGright = new MotorControllerGroup(FrontR, BackR);
    
  public DifferentialDrive tank = new DifferentialDrive(MCGleft , MCGright);

  private double gearRatios;
  private double circumfrance = Math.PI*6;
  public driveS() {
    motorCoast();
    pCompress.enableDigital();
    FrontL.setInverted(true);
    BackL.setInverted(true);
    MCGright.setInverted(false);
    elFront = FrontL.getEncoder();
    elBack = BackL.getEncoder();
    erFront = FrontR.getEncoder();
    erBack = BackR.getEncoder();
    FrontL.setOpenLoopRampRate(.05);
    FrontR.setOpenLoopRampRate(.05);
    BackL.setOpenLoopRampRate(.05);
    BackR.setOpenLoopRampRate(.05);
    FrontL.setSmartCurrentLimit(75, 75);
    BackL.setSmartCurrentLimit(75, 75);
    FrontR.setSmartCurrentLimit(75, 75);
    BackR.setSmartCurrentLimit(75, 75);

    FrontL.burnFlash();
    BackL.burnFlash();
    FrontR.burnFlash();
    BackR.burnFlash();
  }

  public void tankDrive(double left, double right) {
    
    /*if (liftS.liftPercent() >= 0.85) {
      left = left * 0.15;
      right = right * 0.15;
    }
    else {
      left = left / 10 * liftS.liftPercent();
      right = right / 10 * liftS.liftPercent();
    }*/
    
    tank.tankDrive(left*0.97,right);
    SmartDashboard.putNumber("liftPercent", liftS.liftPercent());
    SmartDashboard.putNumber("leftMCG", MCGleft.get());
    SmartDashboard.putNumber("rightMCG", MCGright.get());
  }
  public double getDrivePos(){
    if (shifter.get() == true) {
      gearRatios=1/22.67; //7.56:1, 22.67:1
    } else {
      gearRatios=1/7.56;
    }
    double avgEnc = (elFront.getPosition() + erFront.getPosition()) / 2;
    double rEncValue = avgEnc*gearRatios;
    double encodervalue = rEncValue*circumfrance;
    return encodervalue;
  }

  public double getRightDrivePos(){
    if (shifter.get() == true) {
      gearRatios=1/22.67; //7.56:1, 22.67:1
    } else {
      gearRatios=1/7.56;
    }
    double ravgEnc = (erFront.getPosition());
    double rrEncValue = ravgEnc*gearRatios;
    double rencodervalue = rrEncValue*circumfrance;
    return rencodervalue;
  }

  public double getLeftDrivePos(){
    if (shifter.get() == true) {
      gearRatios=1/22.67; //7.56:1, 22.67:1
    } else {
      gearRatios=1/7.56;
    }
    double lavgEnc = (elFront.getPosition());
    double lrEncValue = lavgEnc*gearRatios;
    double lencodervalue = lrEncValue*circumfrance;
    return lencodervalue;
  }

  public void resetEncoders() {
    elFront.setPosition(0);
    elBack.setPosition(0);
    erFront.setPosition(0);
    erBack.setPosition(0);
  }

  public void motorBrake() {
    FrontL.setIdleMode(IdleMode.kBrake);
    BackL.setIdleMode(IdleMode.kBrake);
    FrontR.setIdleMode(IdleMode.kBrake);
    BackR.setIdleMode(IdleMode.kBrake);
    FrontL.burnFlash();
    FrontR.burnFlash();
    BackL.burnFlash();
    BackR.burnFlash();
    MCGleft.stopMotor();
    MCGright.stopMotor();
  }

  public void motorCoast() {
    FrontL.setIdleMode(IdleMode.kCoast);
    BackL.setIdleMode(IdleMode.kCoast);
    FrontR.setIdleMode(IdleMode.kCoast);
    BackR.setIdleMode(IdleMode.kCoast);
    FrontL.burnFlash();
    FrontR.burnFlash();
    BackL.burnFlash();
    BackR.burnFlash();
  }

  public void shifting(boolean position) {
    shifter.set(position);
    SmartDashboard.putBoolean("Gear", shifter.get());
  }
}
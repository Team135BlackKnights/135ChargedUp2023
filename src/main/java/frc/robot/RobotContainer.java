// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.deployBarC;
import frc.robot.commands.driveC;
import frc.robot.commands.intakeC;
import frc.robot.commands.liftC;
import frc.robot.commands.Auto.leftGridA;
import frc.robot.commands.Auto.middleGridA;
import frc.robot.commands.Auto.rightGridA;
import frc.robot.commands.Macros.autoBalance;
import frc.robot.commands.Macros.extendLift;
//import frc.robot.commands.Macros.extendLift;
import frc.robot.commands.Macros.rotateIntake;
import frc.robot.commands.Macros.targetCone;
import frc.robot.subsystems.intakeS;
import frc.robot.subsystems.driveS;
import frc.robot.subsystems.liftS;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static XboxController controller1 = new XboxController(0);
  public static XboxController controller2 = new XboxController(1);
  public static Joystick leftJoystick = new Joystick(2);
  public static Joystick rightJoystick = new Joystick(3);

  //public static int grid = 0;
  public static JoystickButton LTrig = new JoystickButton(leftJoystick, 1); //left trigger
  public static JoystickButton RTrig = new JoystickButton(rightJoystick, 1);
  public static JoystickButton LThumb= new JoystickButton(leftJoystick, 2);
  public static JoystickButton RThumb= new JoystickButton(rightJoystick, 2);
  public static JoystickButton Lbl = new JoystickButton(leftJoystick, 3); //left joystick bottom left
  public static JoystickButton Rbl = new JoystickButton(rightJoystick, 3);
  public static JoystickButton Lbr = new JoystickButton(leftJoystick, 4);
  public static JoystickButton Rbr = new JoystickButton(rightJoystick, 4);
  public static JoystickButton Ltl = new JoystickButton(leftJoystick, 5);
  public static JoystickButton Rtl = new JoystickButton(rightJoystick, 5);
  public static JoystickButton Ltr = new JoystickButton(leftJoystick, 6);
  public static JoystickButton Rtr = new JoystickButton(rightJoystick, 6);


  final JoystickButton a1 = new JoystickButton(controller1, RobotMap.ButtonMap.A);
  final JoystickButton b1 = new JoystickButton(controller1, RobotMap.ButtonMap.B);
  final JoystickButton x1 = new JoystickButton(controller1, RobotMap.ButtonMap.X);
  final JoystickButton y1 = new JoystickButton(controller1, RobotMap.ButtonMap.Y);
  final JoystickButton lb1 = new JoystickButton(controller1, RobotMap.ButtonMap.LB);
  final JoystickButton rb1 = new JoystickButton(controller1, RobotMap.ButtonMap.RB);

  final JoystickButton a2 = new JoystickButton(controller2, RobotMap.ButtonMap.A);
  final JoystickButton b2 = new JoystickButton(controller2, RobotMap.ButtonMap.B);
  final JoystickButton x2 = new JoystickButton(controller2, RobotMap.ButtonMap.X);
  final JoystickButton y2 = new JoystickButton(controller2, RobotMap.ButtonMap.Y);
  final JoystickButton lb2 = new JoystickButton(controller2, RobotMap.ButtonMap.LB);
  final JoystickButton rb2 = new JoystickButton(controller2, RobotMap.ButtonMap.RB);
  

  // The robot's subsystems and commands are defined here...
  public static driveS _driveS = new driveS();
  public static liftS _liftS = new liftS();
  public static intakeS _intakeS = new intakeS();
  
  //public static driveC _driveC = new driveC(_driveS);
  //public static liftC _liftC = new liftC(_liftS);
  //public static targetCone _targetCone0 = new targetCone(_driveS, 0);

  private final Command LeftGrid = new leftGridA(_driveS, _liftS, _intakeS);
  private final Command MiddleGrid = new middleGridA(_driveS, _liftS, _intakeS);
  private final Command RightGrid = new rightGridA(_driveS, _intakeS, _liftS);

  //private final Command oneScore = new oneScoreA(_driveS);

  SendableChooser<Command> m_Chooser = new SendableChooser<>();
  public static SendableChooser<Boolean> jStick_Chooser = new SendableChooser<>();

 // SendableChooser<Command> m_Chooser2= new SendableChooser<>();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_Chooser.setDefaultOption("Score High Auto", LeftGrid);
    m_Chooser.addOption("Score High and Charging", MiddleGrid);
    m_Chooser.addOption("Cycling Auto DO NOT USE", RightGrid);
    SmartDashboard.putData(m_Chooser);
    jStick_Chooser.addOption("joystick", true);
    jStick_Chooser.addOption("controller", false);
    _intakeS.setDefaultCommand(new intakeC(_intakeS));
    _driveS.setDefaultCommand(new driveC(_driveS));
    _liftS.setDefaultCommand(new liftC(_liftS));
    
    
    // Configure the button bindings
    configureButtonBindings();
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be creted by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {      
    if (jStick_Chooser.getSelected()==false){
    y1.onTrue(new deployBarC(_intakeS));
    a1.whileTrue(new targetCone(_driveS, 1));
    b1.onTrue(new autoBalance(_driveS, 1.5));
    }
    else {
      LThumb.onTrue(new deployBarC(_intakeS));
      Rtl.onTrue(new targetCone(_driveS, 1));
      Rbl.onTrue(new autoBalance(_driveS, 1.5));
    }
    a2.onTrue(new extendLift(_liftS, 69));
    b2.onTrue(new extendLift(_liftS, 1));
    y2.onTrue(new extendLift(_liftS, 2));
    x2.onTrue(new rotateIntake(_liftS, 3));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_Chooser.getSelected();
  }
  /*
   * Driver Controls:
   * Drive: Joysticks
   * Shift: Left/Right Bumpers (Only works if compressor works)
   * Intake: Triggers
   * Entrapment Bar: Y       (Only works if compressor works)
   * 
   * Manipulator Controls:
   * Lift: Left Joystick
   * Rotate Intake: Right Joystick
   * Auto Rotate Intake: X   (Isn't consistent and probably doesn't work) DONT USE
   */
    
 
}

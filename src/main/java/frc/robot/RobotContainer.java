// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.intakeC;
import frc.robot.commands.driveC;
import frc.robot.commands.liftC;
import frc.robot.commands.Auto.leftGridA;
import frc.robot.commands.Auto.middleGridA;
import frc.robot.commands.Auto.oneScoreA;
import frc.robot.commands.Auto.rightGridA;
import frc.robot.subsystems.intakeS;
import frc.robot.subsystems.driveS;
import frc.robot.subsystems.liftS;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static XboxController controller1 = new XboxController(0);
  public static XboxController controller2= new XboxController(1);
  public static int grid = 0;

  // The robot's subsystems and commands are defined here...
  public static driveS _driveS = new driveS();
  public static intakeS _clawS = new intakeS();
  public static liftS _liftS = new liftS();

  public static driveC _driveC = new driveC(_driveS);
  public static Command _Comm1 = new intakeC(_clawS);
  public static liftC _liftC = new liftC(_liftS);

  private final Command LeftGrid = new leftGridA(_driveS);
  private final Command MiddleGrid = new middleGridA(_driveS);
  private final Command RightGrid = new rightGridA(_driveS);
  private final Command oneScore = new oneScoreA(_driveS);

  SendableChooser<Command> m_Chooser = new SendableChooser<>();
 // SendableChooser<Command> m_Chooser2= new SendableChooser<>();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_Chooser.addOption("LeftGridAuto", LeftGrid);
    m_Chooser.addOption("MiddleGridAuto", MiddleGrid);
    m_Chooser.addOption("RightGridAuto", RightGrid);

    m_Chooser.setDefaultOption("LeftGridAuto", LeftGrid);

    //m_Chooser2.addOption("1 score", oneScore);
  //m_Chooser2.addOption("2 score", twoScore)

    SmartDashboard.putData(m_Chooser);

    _clawS.setDefaultCommand(new intakeC(_clawS));
    _driveS.setDefaultCommand(new driveC(_driveS));
    _liftS.setDefaultCommand(new liftC(_liftS));

    if (m_Chooser.getSelected()==LeftGrid){
      grid=1;
    }
    else if (m_Chooser.getSelected()==MiddleGrid){
      grid=2;
    }
    else if (m_Chooser.getSelected()==RightGrid){
      grid=3;
    }

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be creted by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_Chooser.getSelected();
  }
    
 
}

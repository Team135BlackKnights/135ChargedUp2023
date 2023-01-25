// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.comm1;
import frc.robot.commands.driveC;
import frc.robot.commands.Auto.leftGridA;
import frc.robot.commands.Auto.middleGridA;
import frc.robot.commands.Auto.rightGridA;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.driveS;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static XboxController controller1 = new XboxController(0);
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public static frc.robot.subsystems.driveS _driveS = new frc.robot.subsystems.driveS();
  public static frc.robot.subsystems.subsys1 _subsys1 = new frc.robot.subsystems.subsys1();
  public static driveC _driveC = new driveC(_driveS);

  public final Command _Comm1 = new comm1(_subsys1);
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final Command LeftGrid = new leftGridA(_driveS);
  private final Command MiddleGrid = new middleGridA(_driveS);
  private final Command RightGrid = new rightGridA(_driveS);

  SendableChooser<Command> m_Chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_Chooser.addOption("LeftGridAuto", LeftGrid);
    m_Chooser.addOption("MiddleGridAuto", MiddleGrid);
    m_Chooser.addOption("RightGridAuto", RightGrid);

    m_Chooser.setDefaultOption("LeftGridAuto", LeftGrid);

    SmartDashboard.putData(m_Chooser);
    _subsys1.setDefaultCommand(new comm1(_subsys1));
    _driveS.setDefaultCommand(new driveC(_driveS));

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

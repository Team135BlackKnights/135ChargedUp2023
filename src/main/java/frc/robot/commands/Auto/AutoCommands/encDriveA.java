package frc.robot.commands.Auto.AutoCommands;

import javax.swing.text.Position;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.shiftC;
import frc.robot.subsystems.driveS;

public class encDriveA extends CommandBase {
  private final driveS drive;
  PIDController pidController = new PIDController(.008, .004, 0);
  public boolean isFinished= false;

  double Desired, encodervalue;
  double gearRatios;
  double diameter = Math.PI*6;

  public encDriveA(driveS subsystem, double desDis) { //desired Distance
    Desired=desDis;
    drive = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {

  }
        
  @Override
  public void execute() {
    if (shiftC.position == true) {
      gearRatios=1/36;
    } else if (shiftC.position == false) {
      gearRatios=1/42;
    }
    double avgEnc = (driveS.elBack.getPosition() + driveS.elFront.getPosition()-driveS.erBack.getPosition()
    - driveS.erFront.getPosition()) / 4;
    double rEncValue = avgEnc*gearRatios;
    encodervalue = rEncValue*diameter;

    drive.tankDrive(pidController.calculate(encodervalue, Desired), pidController.calculate(encodervalue, Desired));
    if (Math.abs(pidController.getPositionError()) < 1) {
      isFinished = true;
    } 
  }
  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return isFinished();
  }
}

package frc.robot.commands.Auto.AutoCommands;

import javax.swing.text.Position;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.driveC;
import frc.robot.subsystems.driveS;

public class rotDriveA extends CommandBase {
  private final driveS drive;
  PIDController pidController = new PIDController(.008, .004, 0);
  public boolean isFinished= false;

  double Desired, encodervalue;
  double gearRatios;
  double diameter = Math.PI*6;
  double cpr = 1/42; //counts per revolution

  public rotDriveA(driveS subsystem, double desAng) { //desired angle
    Desired=desAng;
    drive = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {

  }
        
  @Override 
  public void execute() {
    if (driveC.position == true) {
      gearRatios=1/7.56; //7.56:1, 22.67:1
    } else if (driveC.position == false) {
      gearRatios=1/22.67;
    }
    double avgEnc = (driveS.elBack.getPosition() + driveS.elFront.getPosition()-driveS.erBack.getPosition()
    - driveS.erFront.getPosition()) / 4;
    double rEncValue = avgEnc*gearRatios;
    encodervalue = rEncValue*diameter*cpr;

    drive.tankDrive(pidController.calculate(encodervalue, -Desired), pidController.calculate(encodervalue, Desired));
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

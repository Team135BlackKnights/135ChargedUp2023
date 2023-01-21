package frc.robot.commands.auto;

import javax.swing.text.Position;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.driveS;

public class encDriveA extends CommandBase {
       private final driveS drive;
       PIDController pidController = new PIDController(.008, .004, 0);
       public boolean isFinished= false;

       double Desired, encodervalue;
       double gearRatios=1/42;
       double diamter = Math.PI*6;

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
            double avgEnc = (driveS.elBack.getPosition() + driveS.elFront.getPosition()-drive.erBack.getPosition()
            - drive.erFront.getPosition()) / 4;
            double rEncValue = avgEnc/gearRatios;
            encodervalue = rEncValue*diamter;

            drive.tankDrive(pidController.calculate(encodervalue, Desired), pidController.calculate(encodervalue, Desired));
            if (Math.abs(pidController.getPositionError()) < 1) {
              isFinished = true;
            } 
        }
}

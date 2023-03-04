package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.Auto.AutoCommands.encDriveA;
import frc.robot.commands.Auto.AutoCommands.resetEncoders;
import frc.robot.commands.Auto.AutoCommands.rotDriveA;
import frc.robot.subsystems.driveS;

public class middleGridA extends SequentialCommandGroup{


    public middleGridA(driveS drive) {
        
        super(
            Commands.sequence(
                //new resetEncoders(drive),

                new encDriveA(drive, -93, false),
                new encDriveA(drive, 0.5, false)
              //  new rotDriveA(drive, 90, false)
            )
        );
                
                    

                    
                /* 
                scoreA(),
                encoderDriveA(50);
                if (RobotContainer.score>1) {
                }*/
            

    }
}
package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.Auto.AutoCommands.encDriveA;
import frc.robot.commands.Auto.AutoCommands.resetEncoders;
import frc.robot.subsystems.driveS;

public class middleGridA extends SequentialCommandGroup{
    int grid = 2;

    public middleGridA(driveS drive) {
        
        super(
            Commands.sequence(
                new resetEncoders(drive),

                new encDriveA(drive, -50, false)
            )
        );
                
                    

                    
                /* 
                scoreA(),
                encoderDriveA(50);
                if (RobotContainer.score>1) {
                }*/
            

    }
}
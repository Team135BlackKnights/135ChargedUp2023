package frc.robot;
public interface RobotMap {

    public final static int M1=31, M2=32;

    public interface Drive {
        
        public final static int 
            FL_ID = 12, //
            FR_ID = 14, //
            BL_ID = 11, //
            BR_ID = 13,        
            shiftSolenoid = 0,
            shiftSolenoid2 = 1;
    }

    public interface Intake {
        // Simple intake. Motors spin vertically opposite of each other.
        public static final int
            intakeMotorID1 = 0,
            intakeMotorID2 = 0;
    }

    public interface Manipulator {
        // Manipulator tilts and is on a lift. Limit switches to set to certain positions.
        public static final int
            liftMotorID1 = 0,
            liftMotorID2 = 0,
            tiltMotorID = 0;
    }
}
package frc.robot;
public interface RobotMap {

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
            intakeID_1 = 0,
            intakeID_2 = 0;
    }

    public interface Lift {
        // Manipulator tilts and is on a lift. Limit switches to set to certain positions.
        public static final int
            liftID_1 = 0,
            liftID_2 = 0,
            tiltID = 0;
    }
}
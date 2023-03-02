package frc.robot;
public interface RobotMap {

    public interface Drive {
        
        public final static int 
            FL_ID = 11, //
            FR_ID = 13, //
            BL_ID = 12, //
            BR_ID = 14,        
            shiftSolenoid = 0;
    }

    public interface Intake {
        // Simple intake. Motors spin vertically opposite of each other.
        public static final int
            intakeID_1 = 31,
            BAR_ID=1,
            BAR2_ID=2;
    }

    public interface Lift {
        // Manipulator tilts and is on a lift. Limit switches to set to certain positions.
        public static final int
            liftID_1 = 40,
            liftID_2 = 41,
            tiltID = 42;
    }

    public interface ButtonMap {
        //XboxOne Joysticks
	    public static final int LEFT_STICK_X = 0;
	    public static final int LEFT_STICK_Y = 1;
	    public static final int LEFT_TRIGGER = 2;
	    public static final int RIGHT_TRIGGER = 3;
	    public static final int RIGHT_STICK_X = 4;
	    public static final int RIGHT_STICK_Y = 5;
	
	    //XboxOne Buttons
	    public static final int A = 1;
	    public static final int B = 2;
	    public static final int X = 3;
	    public static final int Y = 4;
	    public static final int LB = 5;
	    public static final int RB = 6;
	    public static final int LOGO_LEFT = 7;
	    public static final int LOGO_RIGHT = 8;
	    public static final int LEFT_STICK_BUTTON = 9;
	    public static final int RIGHT_STICK_BUTTON = 10;

    }
}
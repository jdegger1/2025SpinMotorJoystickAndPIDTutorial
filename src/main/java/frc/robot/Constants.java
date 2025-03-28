package frc.robot;

public final class Constants {
    
    public static class  MotorConstants{
        //remember to change this number to whatever the motor id actually is
        public static final int kMotorID= 0;

        public static final double kMotorSpeed = 0.1;

        /// Here is where you can mess with the different pid values
        /// Then see how using I and D values impact the motor movement
        public static final double kMotorPVal = 0.1;
        public static final double kMotorIVal = 0.0;
        public static final double kMotorDVal = 0.0;

        // If you want the PID loop to go further or in the other direction, change this value
        public static final double kMotorSetPoint = 20.0;
    }

    public static class  OIConstants{
        public static final int kControllerPort = 0;

        public static final int kMotorAxis = 0;

        public static final int kSpinForwardButton = 1; //A
        public static final int kSpinReverseButton = 2; //B

        public static final int kSpinPIDButton = 3; //C
        
        public static final double kDeadBand = 0.05;
    }
}

This tutorial goes over [basic joystick controls](#joystick) and [basic pid loops](#pid).  
Make sure you completed the spin motor tutorial before going here.  
First go back to the [Motor Subsystem](https://github.com/jdegger1/2025SpinMotorJoystickAndPIDTutorial/blob/main/src/main/java/frc/robot/Subsystems/MotorSubsystem.java), we go over
- Encoders
- Smart dashboard output

### Joystick
For the joystick controls, first create the command [SpinMotorJoystickCmd](https://github.com/jdegger1/2025SpinMotorJoystickAndPIDTutorial/blob/main/src/main/java/frc/robot/Commands/SpinMotorJoystickCmd.java), it will cover
- Using suppliers
- Wrapper classes
- Using a deadband  

Then go to [robotContainer](https://github.com/jdegger1/2025SpinMotorJoystickAndPIDTutorial/blob/main/src/main/java/frc/robot/RobotContainer.java), it goes over
- creating a default command
- syntax used for creating a supplier

### PID
For the PID loop control, create the command [SpinMotorPIDCmd](https://github.com/jdegger1/2025SpinMotorJoystickAndPIDTutorial/blob/main/src/main/java/frc/robot/Commands/SpinMotorPIDCmd.java), it goes over
- The basic concepts behind PID, this includes
  - error
  - setpoint
  - p term
  - i term
  - d term
- Using a PIDController

Then we go into [RobotContainer](https://github.com/jdegger1/2025SpinMotorJoystickAndPIDTutorial/blob/main/src/main/java/frc/robot/RobotContainer.java) to create a toggle button  

If I missed anything, or if you have any questions, just let me know.  
Thank you!

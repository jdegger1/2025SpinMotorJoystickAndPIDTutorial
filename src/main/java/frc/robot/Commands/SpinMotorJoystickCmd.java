// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.Subsystems.MotorSubsystem;

/// We are making a command that can take in a the input from the sticks on the joystick.
/// That is, we are making a command with an analog input instead of digital input
/// Digital means on or off, while analog is a range of values
public class SpinMotorJoystickCmd extends Command {
  /** Creates a new SpinMotorJoystickCmd. */

  private final MotorSubsystem motorSubsystem;

  /// Because the joystick value is constantly changing, we can't just feed in a single double value for the command
  /// Instead, we create a supplier object of type Double, which will continuously supply us with double values
  /// Notice that this uses Double instead of double
  /// double is a primitive data type, whereas Double is a wrapper class
  /// The Double wrapper class creates an object that stores the double value and allows for additional manipulation of the value
  /// The Supplier Class needs the Double and not a double
  private final Supplier<Double> speedFunction;

  public SpinMotorJoystickCmd(MotorSubsystem motorSub, Supplier<Double> speedfxn) {
    // Note how using different names for the instance variable and local variable means we don't need "this".
    motorSubsystem = motorSub;
    speedFunction = speedfxn;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(motorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Here we grab the value from the supplier and assign it to a double
    double speed = speedFunction.get();

    /// We could just set the speed now, but there are a few things we can do to improve the command
    /// First we can apply a deadband
    /// When working with joysticks, the value rarely ever returns to 0
    /// This means that our motor will almost never stop moving
    /// To fix this we apply a deadband, where we set values close to 0, to 0.
    
    /// This is an if statement, if statements will only run if the conditional statement inside the () are true
    /// We check to see if the absolute value of speed is less than 0.05 (our current deadband value)
    /// We use absolute values because we have to consider negative speeds as well and want the deadband to be distance from 0.
    if( Math.abs(speed) < OIConstants.kDeadBand){
      speed = 0;
    }
    
    motorSubsystem.setSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

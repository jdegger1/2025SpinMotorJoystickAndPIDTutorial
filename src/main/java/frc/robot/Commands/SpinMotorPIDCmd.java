// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.MotorConstants;
import frc.robot.Subsystems.MotorSubsystem;

/// The following command uses PID to spin a motor to a setpoint
/// PID contains a setpoint, an error, and the speed
/// the setpoint is where we want to go
/// the error is the distance from the setpoint
/// the speed is calculated by summing three terms, the Proportional, Integral, and Derivative
/// The proportional term is calculated by taking our error and multiplying it by a constant value
///   We go proportionally slower the closer we get to our setpoint because our error gets smaller
/// 
/// As we approach the setpoint, we will eventually be too slow to make it to the setpoint
///   The output of the motor can't overcome friction and outside forces
/// That is where the Integral comes in
/// The Integral term takes a sum of the errors over time and multiplies it by a constant value
/// This is added to the Proportional speed and is typically used to give that last little boost to our setpoint
/// 
/// Oftentimes our P and I values lead to the motor going to fast and overshooting, or going past, the setpoint
/// To prevent overshoot, you can tune the P and I values by using lower values to slow down earlier and give less boost
/// Or you can use the Derivative term
/// The Derivative term takes the derivative of the speed (or something, I haven't taken calculus)
/// and calculates how much the motor will overshoot
/// It then compensates by lowering the speed, the D value determines how much it compensates
///   If this value is too high you will tend to see the motor act shaky.
public class SpinMotorPIDCmd extends Command {
  /** Creates a new SpinMotorPIDCmd. */

  private final MotorSubsystem motorSubsystem;

  /// Luckily, we don't need to do the math.
  /// We can create a PIDController object, and all we have to do is give it the setpoint, pid values, and location
  /// And it can calculate the rest
  private final PIDController motorPidController;

  public SpinMotorPIDCmd(MotorSubsystem motorSubsystem) {
    this.motorSubsystem = motorSubsystem;

    // we create the PIDController object using P I and D terms created in Constants
    motorPidController = new PIDController(MotorConstants.kMotorPVal, MotorConstants.kMotorIVal, MotorConstants.kMotorDVal);

    // we assign the setPoint to be 20 rotations (our setpoint constant) ahead of our location
    motorPidController.setSetpoint(motorSubsystem.getEncoder() + MotorConstants.kMotorSetPoint);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(motorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // we give the pidController our current location and it calculates the error which we use to calculate our speed
    double speed = motorPidController.calculate(motorSubsystem.getEncoder());

    motorSubsystem.setSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    motorSubsystem.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

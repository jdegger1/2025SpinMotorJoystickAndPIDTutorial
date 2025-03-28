// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Use your SpinMotorTutorial as the base for this code.
public class MotorSubsystem extends SubsystemBase {
  /** Creates a new MotorSubsystem. */

  private final SparkMax motor;

  /// Encoders track the rotation of a motor, axle, or shaft
  /// There are two types of encoders, relative encoder and absolute encoder
  /// Relative encoders track the number of rotations and can be used to track distance
  /// Absolute encoders track the location of the rotaion, after one rotation it goes back to zero, think like a compass
  /// Brushless motors come with an internal encoder used here
  /// You can also use through-bore encoders
  /// These are encoders put on shafts to track the rotation of the shaft
  private final RelativeEncoder motorEncoder;
  public MotorSubsystem(int motorID) {
    motor = new SparkMax(motorID, MotorType.kBrushless);
    motorEncoder = motor.getEncoder();
  }

  public void setSpeed(double speed){
    motor.set(speed);
  }

  public double getEncoder(){
    // We return the number of rotations of the encoder
    return motorEncoder.getPosition();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    /// We now use the subsystem's periodic method to track the encoder on SmartDashboard
    /// Smart dashboard is a GUI (graphic user interface) that can be used to track whatever we tell it
    SmartDashboard.putNumber("Encoder Distance", getEncoder());
    // Notice how we didn't have to create a MotorSubsystem object to use getEncoder() because we're already in the class.
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Commands.SpinMotorCmd;
import frc.robot.Commands.SpinMotorJoystickCmd;
import frc.robot.Commands.SpinMotorPIDCmd;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.Subsystems.MotorSubsystem;

public class RobotContainer {

  private final MotorSubsystem motorSub;

  private final Joystick joystick;

  public RobotContainer() {
    motorSub = new MotorSubsystem(MotorConstants.kMotorID);
    joystick = new Joystick(OIConstants.kControllerPort);
    

    /// When using joystick commands, we typically want them to be the default command of a subsystem
    /// The default command is just the command that runs when no other command is running
    motorSub.setDefaultCommand(new SpinMotorJoystickCmd(motorSub, () -> joystick.getRawAxis(OIConstants.kMotorAxis)));
    /// The syntax you see here, ()->, is used to say that we want a supplier using the return value from a method
    /// joystick.getRawAxis returns a double, but because we want it continuously, we use ()-> to create a supplier
    /// this supplier uses the value from getRawAxis to continuously get the value from that axis.
    
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(joystick, OIConstants.kSpinForwardButton).whileTrue(new SpinMotorCmd(motorSub, MotorConstants.kMotorSpeed));

    /// Here is how I would make the button to spin the motor the other way, instead of making a second speed, we can make our premade speed negative
    /// This makes changing both speeds in the constants file easier
    new JoystickButton(joystick, OIConstants.kSpinReverseButton).whileTrue(new SpinMotorCmd(motorSub, -MotorConstants.kMotorSpeed));

    /// If you want a command to go even when your not pressing a button, you can make the corresponding button be a toggle
    /// A toggleOnTrue will run the command until another button is pressed or you press that button again to toggle it off.
    new JoystickButton(joystick, OIConstants.kSpinPIDButton).toggleOnTrue(new SpinMotorPIDCmd(motorSub));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}

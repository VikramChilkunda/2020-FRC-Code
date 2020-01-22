/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new IntakeSubsystem.
   */

  public static DoubleSolenoid intake;

  public IntakeSubsystem() {
    intake = new DoubleSolenoid(frc.robot.Constants.OIConstants.intakeForwardChannel, frc.robot.Constants.OIConstants.intakeReverseChannel);
    intake.set(DoubleSolenoid.Value.kForward);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void deployIntake(){
    intake.set(DoubleSolenoid.Value.kReverse);

  }
  public void retractIntake(){
    intake.set(DoubleSolenoid.Value.kForward);
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  //private final WPI_TalonSRX m_leftMotor = new WPI_TalonSRX(DriveConstants.kLeftMotor1Port);
  //private final WPI_TalonSRX m_rightMotor = new WPI_TalonSRX(DriveConstants.kRightMotor1Port);  

  //UNCOMMENT FOR SPARK MOTORS
  public static CANSparkMax m_leftMotor = new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless);;
  public static CANSparkMax m_rightMotor = new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless);;

  // The motors on the left side of the drive.
  // private final SpeedControllerGroup m_leftMotors =
  //     new SpeedControllerGroup(new PWMVictorSPX(DriveConstants.kLeftMotor1Port),
  //                              new PWMVictorSPX(DriveConstants.kLeftMotor2Port));

  // // The motors on the right side of the drive.
  // private final SpeedControllerGroup m_rightMotors =
  //     new SpeedControllerGroup(new PWMVictorSPX(DriveConstants.kRightMotor1Port),
  //                              new PWMVictorSPX(DriveConstants.kRightMotor2Port));

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  // The left-side drive encoder
  private final Encoder m_leftEncoder =
      new Encoder(DriveConstants.kLeftEncoderPorts[0], DriveConstants.kLeftEncoderPorts[1],
                  DriveConstants.kLeftEncoderReversed);

  // The right-side drive encoder
  private final Encoder m_rightEncoder =
      new Encoder(DriveConstants.kRightEncoderPorts[0], DriveConstants.kRightEncoderPorts[1],
                  DriveConstants.kRightEncoderReversed);

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {

    // Sets the distance per pulse for the encoders
    m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);

    // Configure the Talons
    // m_leftMotor.configFactoryDefault();
    // m_rightMotor.configFactoryDefault();

    // m_leftMotor.setInverted(false);
    // m_leftMotor.setSensorPhase(false);

    // m_rightMotor.setInverted(false);
    // m_rightMotor.setSensorPhase(false);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  public void setDriveMotors(double left, double right, double multiplier){
    m_leftMotor.set(left*multiplier);
    m_rightMotor.set(right*multiplier);
  }

  // public void setDriveTalons(double left, double right, double multiplier){
  //   m_leftMotor.set(ControlMode.PercentOutput, left*multiplier);
  //   m_rightMotor.set(ControlMode.PercentOutput, right*multiplier);
  // }

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  /**
   * Gets the average distance of the TWO encoders.
   *
   * @return the average of the TWO encoder readings
   */
  public double getAverageEncoderDistance() {
    return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public Encoder getLeftEncoder() {
    return m_leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public Encoder getRightEncoder() {
    return m_rightEncoder;
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }
}

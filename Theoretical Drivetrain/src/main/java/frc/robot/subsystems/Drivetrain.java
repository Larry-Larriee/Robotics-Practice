// Default packages installed with template =================================================================================================
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

// IMPORTS ==================================================================================================================================

// Drivetrain class needs the motors that it's going to use https://docs.revrobotics.com/sparkmax/software-resources/spark-max-api-information
// Make sure to grab the JSON dependencies and manage vendor libraries

// Import motor libraries for CANSparkMax
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// Import encoders to read motor data for CANSparkMAx
import com.revrobotics.SparkMaxAlternateEncoder;

// Import differentable drive (think of how a regular car works). We pass the motors as arguments for the differentable drive
// This allows the vehicle to change speeds and direction
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// MAINSETUP ===============================================================================================================================

public class Drivetrain extends SubsystemBase {

  /*
   * Drivetrain does not take in any arguments and has its motor attributes in it
   * when we create it as an object
   */

  // Motors have 2 params (ID, MotorType)
  CANSparkMax m_leftMotor = new CANSparkMax(0, MotorType.kBrushless);
  CANSparkMax m_rightMotor = new CANSparkMax(1, MotorType.kBrushless);

  // Note that SparkMaxAlternateEncoder returns type RelativeEncoder
  RelativeEncoder m_leftEncoder = m_leftMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 30);
  RelativeEncoder m_rightEncoder = m_leftMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 30);

  // Attach motors to the vehicle Differential Drive
  DifferentialDrive m_DifferentialDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  final double wheelDiameter = 2.5;
  final double pi = Math.PI;

  public Drivetrain() {
  }

  /*
   * Common methods for driving include reseting encoders and getting positions of
   * the drivetrain from the start
   */

  public void resetEncoders() {

    m_leftEncoder.setPosition(0);
    m_rightEncoder.setPosition(0);
  }

  /* Get the total amount of rotations from start distance */
  public double getLeftEncoderPosition() {
    return m_leftEncoder.getPosition() * wheelDiameter * pi;
  }

  public double getRightEncoderPosition() {
    return m_rightEncoder.getPosition() * wheelDiameter * pi;
  }

  /*
   * arcadeDrive is a fancy way of saying move the vehicle given speed and
   * rotation
   */
  public void arcadeDrive(double xSpeed, double zRotation) {
    m_DifferentialDrive.arcadeDrive(xSpeed, zRotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

}

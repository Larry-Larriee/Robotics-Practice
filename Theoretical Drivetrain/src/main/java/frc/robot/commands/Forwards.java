// IMPORTS ==================================================================================================================================

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

// MAINSETUP ================================================================================================================================

/** An example command that uses an example subsystem. */
public class Forwards extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Drivetrain m_Drivetrain;

  /* The goal was for the drivetrain to move back three inches */
  final int distance = 3;

  public Forwards(Drivetrain subsystem) {
    m_Drivetrain = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Always start the forward command by reseting position
  @Override
  public void initialize() {
    m_Drivetrain.resetEncoders();
  }

  // Move forwards at a constant rate of 0.5
  @Override
  public void execute() {
    m_Drivetrain.arcadeDrive(0.5, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end and runs the end methods
  @Override
  public boolean isFinished() {

    if (m_Drivetrain.getLeftEncoderPosition() >= distance) {
      return true;
    }

    return false;
  }
}

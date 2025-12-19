package frc.robot.autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.RollerSubsystem;

public class DriveForwardScoreAuto extends Command {
    private DriveSubsystem m_drive;
    private RollerSubsystem m_roller;

    private Timer timer;
    private double drive_seconds = 3.25;
    private double reel_seconds = drive_seconds + 1;
    private double score_seconds = drive_seconds + 3.25;


      /**
     * This auto will have the robot drive forwards
     * 
     * There are many ways to write autos, this form will work well for most simple
     * auto routines. For more advanced routines you may want a different structure and 
     * to use more sensors.
     * 
     * Here we use a single timer gate, after the robot has finished driving for the first 3.25 
     * seconds it will stop moving. You may wish for the robot to move more or less depending on
     * your use case.
     * 
     * 
     * @param drive
     */
    public DriveForwardScoreAuto(DriveSubsystem drive, RollerSubsystem roller)
    {
        m_drive = drive;
        m_roller = roller;
        
        timer = new Timer();

        addRequirements(m_drive, roller);
        // addRequirements(roller);
    }

    @Override
  public void initialize() {
    // start timer, uses restart to clear the timer as well in case this command has
    // already been run before
    timer.restart();
  }

  // Runs every cycle while the command is scheduled (~50 times per second), here we will just drive forwards
  @Override
  public void execute() {
    // drive at 30% speed
    if(timer.get() < drive_seconds)
    {
        // m_drive.driveArcade(0.3, 0.0,false);
        // m_drive.driveTank(-0.3, 0.3, false);
        m_drive.driveTank(0.15, -0.15, false);

    }

    if(timer.get() > drive_seconds && timer.get() < reel_seconds) {
      m_roller.runRoller(0.6);
    }

    if(timer.get() > drive_seconds && timer.get() < score_seconds) {
      // m_drive.driveTank(-0.3, 0.3, false);
      m_drive.driveTank(0.15, -0.15, false);
      m_roller.runRoller(-0.95);
    }
  }

  // Runs each time the command ends via isFinished or being interrupted.
  @Override
  public void end(boolean isInterrupted) {
    // stop drive motors
    m_drive.driveArcade(0, 0, false);
    m_roller.runRoller(0);
    timer.stop();
  }

  // Runs every cycle while the command is scheduled to check if the command is
  // finished
  @Override
  public boolean isFinished() {
    // check if timer exceeds seconds, when it has this will return true indicating
    // this command is finished
    return timer.get() >= score_seconds;
  }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.RollerConstants;
import frc.robot.autos.DriveForwardAuto;
import frc.robot.autos.DriveForwardScoreAuto;
import frc.robot.commands.ArmDownCommand;
import frc.robot.commands.ArmUpCommand;
import frc.robot.commands.Autos;
import frc.robot.commands.ClimberDownCommand;
import frc.robot.commands.ClimberUpCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.RollerCommand;
import frc.robot.subsystems.AlgaeArm;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.RollerSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final RollerSubsystem rollerSubsystem = new RollerSubsystem();
  private final ClimberSubsystem climber = new ClimberSubsystem();
  private final AlgaeArm arm = new AlgaeArm();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kDriverOperatorPort);

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  public final DriveSubsystem m_drive = new DriveSubsystem();
  public final DriveForwardAuto m_driveForwardAuto = new DriveForwardAuto(m_drive);
  public final DriveForwardScoreAuto m_driveForwardScoreAuto = new DriveForwardScoreAuto(m_drive, rollerSubsystem);



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    m_chooser.addOption("Drive Forward Auto", m_driveForwardAuto);
    m_chooser.addOption("Drive Forward Score Auto", m_driveForwardScoreAuto);

    SmartDashboard.putData(m_chooser);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

        m_drive.setDefaultCommand(new DriveCommand(m_drive,
        () -> m_driverController.getRightX(),
        () -> m_driverController.getLeftY(),
        () -> true));


    
        m_driverController.leftBumper().whileTrue(new DriveCommand(m_drive, 
        () -> m_driverController.getRightX() * DriveConstants.SLOW_MODE_MOVE,  
        () -> m_driverController.getLeftY() * DriveConstants.SLOW_MODE_TURN,
        () -> true));

        // Set the default command for the roller subsystem to an instance of
        // RollerCommand with the values provided by the triggers on the operator
        // controller
        //rollerSubsystem.setDefaultCommand(new RollerCommand(() -> m_operatorController.getLeftY(), rollerSubsystem));
        // rollerSubsystem.setDefaultCommand(new RollerCommand(() -> -m_operatorController.getRightY(), rollerSubsystem));

        //m_operatorController.x().whileTrue(new RollerCommand(() -> RollerConstants.ROLLER_EJECT_VALUE, rollerSubsystem));
        m_operatorController.povDown().whileTrue(new RollerCommand(rollerSubsystem, 1));
        m_operatorController.povUp().whileTrue(new RollerCommand(rollerSubsystem, -1));

        m_operatorController.y().whileTrue(new ClimberUpCommand(climber));
        m_operatorController.a().whileTrue(new ClimberDownCommand(climber));
        
        m_operatorController.b().whileTrue(new ArmDownCommand(arm));
        m_operatorController.x().whileTrue(new ArmUpCommand(arm));

        // triggers -> rolllers (oppo directions)
        // climb -> y, a
        // b, x 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    return m_chooser.getSelected();
  }
}

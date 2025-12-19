// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kDriverOperatorPort = 1;
  }

  public static final class DriveConstants {
    // old ones
    // public static final int LEFT_LEADER_ID = 4;
    // public static final int LEFT_FOLLOWER_ID = 2;
    // public static final int RIGHT_LEADER_ID = 3;
    // public static final int RIGHT_FOLLOWER_ID = 5;

    public static final int LEFT_LEADER_ID = 5; // used to be 3
    public static final int LEFT_FOLLOWER_ID = 3; // used to be 5
    public static final int RIGHT_LEADER_ID = 4;
    public static final int RIGHT_FOLLOWER_ID = 2;

    public static final int DRIVE_MOTOR_CURRENT_LIMIT = 60;
    public static final double DRIVE_MOTOR_VOLTAGE_COMP = 12;
    public static final double SLOW_MODE_MOVE = 0.5;
    public static final double SLOW_MODE_TURN = 0.6;
  }

  public static class RollerConstants {
    public static final int ROLLER_MOTOR_ID = 6; 
    public static final double ROLLER_EJECT_VALUE = -0.5; 
    
  }

  public static class ClimberConstants {
    public static final int CLIMBER_MOTOR_ID = 62; // TODO Assign real value
    public static final double CLIMBER_SPEED_UP = .6; // TODO Assign real value
    public static final double CLIMBER_SPEED_DOWN = -.6; // TODO Assign real value

  }

  public static class ArmConstants {
    public static final int ARM_MOTOR_ID = 52;
    public static final double ARM_SPEED_UP = -0.3;
    public static final double ARM_HOLD_UP = -0.1;
    public static final double ARM_SPEED_DOWN = 0.3;
    public static final double ARM_HOLD_DOWN = 0.1;
  }
}

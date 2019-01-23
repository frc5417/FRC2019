/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;


/**
 * Add your docs here.
 */
public class driveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
TalonSRX driveLeftMaster = new TalonSRX(0);
TalonSRX driveLeftSlave1 = new TalonSRX(1);
TalonSRX driveLeftSlave2 = new TalonSRX(2);

TalonSRX driveRightMaster = new TalonSRX(3);
TalonSRX driveRightSlave1 = new TalonSRX(4);
TalonSRX driveRightSlave2 = new TalonSRX(5);

//test

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    driveLeftSlave1.set(ControlMode.Follower, 0);
    driveLeftSlave2.set(ControlMode.Follower, 0);

    driveRightSlave1.set(ControlMode.Follower, 3);
    driveRightSlave2.set(ControlMode.Follower, 3);
    

  }


  public void SetPower(double leftPower, double rightPower)
  {
    // m_drive.tankDrive(rightPower, leftPower);
    driveRightMaster.set(ControlMode.PercentOutput, -rightPower);
    driveRightMaster.set(ControlMode.PercentOutput, leftPower);
  }


  
}

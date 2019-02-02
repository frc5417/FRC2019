/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
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



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    driveLeftSlave1.set(ControlMode.Follower, driveLeftMaster.getDeviceID());
    driveLeftSlave2.set(ControlMode.Follower, driveLeftMaster.getDeviceID());

    driveRightSlave1.set(ControlMode.Follower, driveRightMaster.getDeviceID());
    driveRightSlave2.set(ControlMode.Follower, driveRightMaster.getDeviceID());

    driveRightMaster.set(ControlMode.PercentOutput, 0);
		driveLeftMaster.set(ControlMode.PercentOutput, 0);
		
		/* Set Neutral Mode */
		driveLeftMaster.setNeutralMode(NeutralMode.Brake);
		driveRightMaster.setNeutralMode(NeutralMode.Brake);
		
		/** Feedback Sensor Configuration */
		
		/* Configure the left Talon's selected sensor to a Quad Encoder*/
		driveLeftMaster.configSelectedFeedbackSensor(	FeedbackDevice.QuadEncoder, 			// Local Feedback Source
													0,					// PID Slot for Source (slot 0)
													30);					// Configuration Timeout (30ms)

		/* Configure the Remote Talon's selected sensor as a remote sensor for the right Talon */
		driveRightMaster.configRemoteFeedbackFilter(driveLeftMaster.getDeviceID(),					// Device ID of Source
												RemoteSensorSource.TalonSRX_SelectedSensor,	// Remote Feedback Source
												1,						// Source number (1) (i dont know what this is or how it works???)
                        30);	// Configuration Timeout (30ms)
                        
                        driveRightMaster.config_kP(0, 1, 30); //second number is P I D or F
                        driveRightMaster.config_kI(0, 0, 30);//(the first is PID Slot, and third is Timeout in ms, but i dont know how to use those)
                        driveRightMaster.config_kD(0, 20, 30);
                        driveRightMaster.config_kF(0, 1023.0/6800.0, 30);
                        driveRightMaster.config_IntegralZone(0, 300, 30);
                        driveRightMaster.configClosedLoopPeakOutput(0, .50, 30);
                        driveRightMaster.configAllowableClosedloopError(0, 0, 30);
                    

  }

  public void driveStraight(Boolean button, double leftPower, double rightPower){
    double forward = -1 * rightPower;
    double target_RPM = forward * 500;	// +- 500 RPM
    double target_unitsPer100ms = target_RPM * 4096 / 600.0;
    driveRightMaster.set(ControlMode.Velocity, target_unitsPer100ms, DemandType.AuxPID);
			driveLeftMaster.follow(driveRightMaster, FollowerType.AuxOutput1);
  }


  public void SetPower(double leftPower, double rightPower)
  {
    // m_drive.tankDrive(rightPower, leftPower);
    driveRightMaster.set(ControlMode.PercentOutput, -rightPower);
    driveRightMaster.set(ControlMode.PercentOutput, leftPower);
  }


  
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
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

AnalogInput sensorRight = new AnalogInput(0);
AnalogInput sensorLeft = new AnalogInput(1);


//test

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
     driveLeftSlave1.set(ControlMode.Follower, driveLeftMaster.getDeviceID());
     driveLeftSlave2.set(ControlMode.Follower, driveLeftMaster.getDeviceID());

    //  driveLeftMaster.setInverted(true);
    //  driveLeftSlave1.setInverted(true);
    //  driveLeftSlave2.setInverted(true);
    //  driveLeftMaster.setSensorPhase(false);

    //  driveRightMaster.setInverted(false);
    //  driveRightSlave1.setInverted(true);
    //  driveRightSlave2.setInverted(true);
    //  driveRightMaster.setSensorPhase(true);

     driveRightSlave1.set(ControlMode.Follower, driveRightMaster.getDeviceID());
     driveRightSlave2.set(ControlMode.Follower, driveRightMaster.getDeviceID());

      driveRightMaster.set(ControlMode.Velocity, 0); //Change control mode of talon, default is PercentVbus (-1.0 to 1.0)
      driveRightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30); //Set the feedback device that is hooked up to the talon

      driveLeftMaster.set(ControlMode.Velocity, 0); //Change control mode of talon, default is PercentVbus (-1.0 to 1.0)
      driveLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30); //Set the feedback device that is hooked up to the talon

		
		/* Set Neutral Mode */
    driveLeftMaster.setNeutralMode(NeutralMode.Coast);
    driveLeftSlave1.setNeutralMode(NeutralMode.Coast);
    driveLeftSlave2.setNeutralMode(NeutralMode.Coast);

    driveRightMaster.setNeutralMode(NeutralMode.Coast);
    driveRightSlave1.setNeutralMode(NeutralMode.Coast);
    driveRightSlave2.setNeutralMode(NeutralMode.Coast);
		
		/** Feedback Sensor Configuration */
		
		/* Configure the left Talon's selected sensor to a Quad Encoder*/
		
  }


  public void SetPower(double leftPower, double rightPower)
  {
    // m_drive.tankDrive(rightPower, leftPower);
    driveRightMaster.set(ControlMode.Velocity, rightPower * -5000);


    driveLeftMaster.set(ControlMode.Velocity, leftPower * 5000);

  }

  public void driveStraight(Boolean button, Double throttle){
    if (button){
    driveLeftMaster.set(ControlMode.Velocity, throttle * 1500);
    driveRightMaster.set(ControlMode.Velocity, throttle * -1500);
    }
    else {
      driveLeftMaster.set(ControlMode.Velocity, 0);
        driveRightMaster.set(ControlMode.Velocity, 0);
    }
      
  
    
  }

  public void squareUp(boolean button){
    if (button){
      if (sensorRight.getValue() < sensorLeft.getValue()){
        driveRightMaster.set(ControlMode.Velocity, 1500);
      }
      else if (sensorLeft.getValue() < sensorRight.getValue()){
        driveLeftMaster.set(ControlMode.Velocity, -1500);
      }
      else if ((sensorRight.getValue() - sensorLeft.getValue() < 2) && (sensorRight.getValue() - sensorLeft.getValue() > -8)){
        driveLeftMaster.set(ControlMode.Velocity, 1500);
        driveRightMaster.set(ControlMode.Velocity, -1500);


      }
    }
  }

  public void printVelocity(Boolean button){
    // System.out.println("Right encoder");
    // System.out.println(driveRightMaster.getSelectedSensorVelocity());
    // System.out.println("left encoder");
    // System.out.println(driveLeftMaster.getSelectedSensorVelocity());
  }

  
}

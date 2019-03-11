/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
// import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.constant;


/**
 * Add your docs here.
 */
public class driveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

//left side init
TalonSRX driveLeftMaster = new TalonSRX(constant.driveLeftMaster);
VictorSPX driveLeftSlave1 = new VictorSPX(constant.driveLeftSlave1);
VictorSPX driveLeftSlave2 = new VictorSPX(constant.driveLeftSlave2);
//right side init
TalonSRX driveRightMaster = new TalonSRX(constant.driveRightMaster);
VictorSPX driveRightSlave1 = new VictorSPX(constant.driveRightSlave1);
VictorSPX driveRightSlave2 = new VictorSPX(constant.driveRightSlave2);
//climber solonoid init
Solenoid climberSolenoid = new Solenoid(0);







  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());


     driveRightSlave1.set(ControlMode.Follower, driveRightMaster.getDeviceID()); //setting right side follower mode 
     driveRightSlave2.set(ControlMode.Follower, driveRightMaster.getDeviceID());
     //driveRightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30); //Set the feedback device that is hooked up to the talon

     driveLeftSlave1.set(ControlMode.Follower, driveLeftMaster.getDeviceID());//setting left side follower mode
     driveLeftSlave2.set(ControlMode.Follower, driveLeftMaster.getDeviceID());
     //driveLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30); //Set the feedback device that is hooked up to the talon

		
		/* Set Neutral Mode */
    driveLeftMaster.setNeutralMode(NeutralMode.Coast);
    driveLeftSlave1.setNeutralMode(NeutralMode.Coast);
    driveLeftSlave2.setNeutralMode(NeutralMode.Coast);

    driveRightMaster.setNeutralMode(NeutralMode.Coast);
    driveRightSlave1.setNeutralMode(NeutralMode.Coast);
    driveRightSlave2.setNeutralMode(NeutralMode.Coast);
		
		
    
    climberSolenoid.set(true); //turning Climber solenoid off (praying )

		
  }

 //main drive function
  public void SetPower(double leftPower, double rightPower)
  {
    // m_drive.tankDrive(rightPower, leftPower);
    driveRightMaster.set(ControlMode.PercentOutput, -rightPower);
    driveRightSlave1.set(ControlMode.PercentOutput, -rightPower);
    driveRightSlave2.set(ControlMode.PercentOutput, -rightPower);


    driveLeftMaster.set(ControlMode.PercentOutput, leftPower);
    driveLeftSlave1.set(ControlMode.PercentOutput, leftPower);
    driveLeftSlave2.set(ControlMode.PercentOutput, leftPower);


  }

// fun to play with not to eat arcade drive (kinda works kinda wonk)
  public void arcadeDrive(Double y2, Double y1, Double x){
    driveRightMaster.set(ControlMode.PercentOutput, -(((y1)+(-y2)+(x))/2));


    driveLeftMaster.set(ControlMode.PercentOutput, (((y1)+(-y2)-(x))/2));
  }

//climber release
  public void releaseTheKraken(Boolean button1, Boolean button2){
    if (button1 && button2){
      climberSolenoid.set(true);
    }
  }

  //Code that squares up with two range sensors

  // public void squareUp(boolean button){
  //   if (button){
  //     if (sensorRight.getValue() < sensorLeft.getValue()){
  //       driveRightMaster.set(ControlMode.Velocity, 1500);
  //     }
  //     else if (sensorLeft.getValue() < sensorRight.getValue()){
  //       driveLeftMaster.set(ControlMode.Velocity, -1500);
  //     }
  //     else if ((sensorRight.getValue() - sensorLeft.getValue() < 2) && (sensorRight.getValue() - sensorLeft.getValue() > -8)){
  //       driveLeftMaster.set(ControlMode.Velocity, 1500);
  //       driveRightMaster.set(ControlMode.Velocity, -1500);


  //     }
  //   }
  // }


  
}

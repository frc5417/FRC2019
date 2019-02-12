/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;





/**
 * Add your docs here.
 */
public class hatchIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX intake = new TalonSRX(9);
  DigitalInput zeroSensor = new DigitalInput(0);
  int hatchIntakeState = 1; //init variable for state of intake

  //the values below need to be changed for final robot, they are in encoder rotation units, 
  //to find values, rotate to the correct point, and find encoder value, and input it in the correct spot 

  static int nuetralPos = 1945; //position in nuetral state
  static int holdPos = 500; //position in holding hatch state
  static int pushPos = 4300; //position in pushing hatch state 

  /*hatch intake has 3 states:

  0:neutral state, not holding or pushing
  1:holding hatch
  2:pushing down hatch

  to change the state of the hatch, change this value to 0, 1, or 2
  */

  
  @Override
  public void initDefaultCommand() { //default command
    intake.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); //init encoder
    intake.setNeutralMode(NeutralMode.Brake); //sets motor to break mode
  }

   Boolean runOnce = false;
public void zeroIntake(Boolean button){
  if (button){
  intake.set(ControlMode.PercentOutput, -.1); //drive motor backwards
  if(zeroSensor.get()){ //if zero Switch reads true
    intake.set(ControlMode.PercentOutput,0); //stop motor
    intake.setSelectedSensorPosition(0); //zero out sensor
  }
}
  

}

  public void hatchIntakeloop(){
    switch(hatchIntakeState){ //find value of hatchIntakeState and :
      case 0: //if state is 0
          intake.set(ControlMode.Position, nuetralPos); //sets intake to nuetralPos
          break;
      case 1 : //if state is 1
          intake.set(ControlMode.Position, holdPos); //sets intake to holdPos
          break;
      case 2 : //if state is 2
          intake.set(ControlMode.Position, pushPos); //sets intake to pushPos
          break;
      default :  //if state is unreadable (someone broke something)
          intake.set(ControlMode.PercentOutput, 0); //if hatchIntakeState is null or non [0-2], turn motor off
          System.out.println("Hatch Intake shut down, hatchIntakeState: " + hatchIntakeState); 
    }
  }


public void cycleHatch(Boolean button_released){ //on button press, go the next stage of the intake (neutral, hold hatch, score hatch)
  if (button_released){ //if button is pushed and then released
    if (hatchIntakeState < 1){
      hatchIntakeState += 1; //set hatch to hold
    }
    else if (hatchIntakeState >= 1){
      hatchIntakeState = 0; //set hatch to nuetral
    }
  }
}

public void pushHatch(Boolean button_released){
  if (button_released){
    hatchIntakeState = 2; //sets hatch intake to state 2, throws out
  }
}

public void getZeroSensor(){
  System.out.println(hatchIntakeState);
  

}

}
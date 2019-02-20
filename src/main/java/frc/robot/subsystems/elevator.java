/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.constant;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;





/**
 * Add your docs here.
 */
public class elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX liftMaster = new TalonSRX(constant.liftMaster);
  TalonSRX liftSlave = new TalonSRX(constant.liftSlave);
  int liftState = 0; //init variable for state of liftMaster
  int setPoint = 0; //used for beta testing lift

  //the values below need to be changed for final robot, they are in encoder rotation units, 
  //to find values, rotate to the correct point, and find encoder value, and input it in the correct spot 

  static int floorPos = 0;  //position in nuetral state
  static int hatch1Pos = 0; //position in holding hatch state
  static int hatch2Pos = 0; //position in pushing hatch state 
  static int hatch3Pos = 0;
  static int cargo1Pos = 0;
  static int cargo2Pos = 0;
  static int cargo3Pos = 0;

  /*hatch liftMaster has 3 states:

  0:neutral state, not holding or pushing
  1:holding hatch
  2:pushing down hatch

  to change the state of the hatch, change this value to 0, 1, or 2
  */

  
  @Override
  public void initDefaultCommand() { //default command
    //setting pid constants
    liftMaster.config_kP(0, constant.liftP);
    liftMaster.config_kI(0, constant.liftI);
    liftMaster.config_kD(0, constant.liftD);

    liftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative); //init encoder
    liftMaster.setNeutralMode(NeutralMode.Brake); //sets motor to break mode
    liftSlave.follow(liftMaster); //lift slave follows lift master
    liftMaster.setSelectedSensorPosition(0); // zero sensor
    liftMaster.setSensorPhase(true);




  }
  

    //test code for positioning 
    public void changeHeight(Boolean button_one, Boolean button_two){
      if (button_one){
        liftMaster.set(ControlMode.Position, 1000);
      }
      else {
        liftMaster.set(ControlMode.Position, -0);

      }
    }
//prints lift position
    public int getLiftSensor(){
      return liftMaster.getSelectedSensorPosition();
    }
//zeros lift with driver station button
    public void zeroLift(Boolean button){
      if (button){
        liftMaster.setSelectedSensorPosition(0); // zero sensor

      }
    }
//test percent output for lift
    public void analogLift(Double input){
      liftMaster.set(ControlMode.PercentOutput,input * .4);
    }


    // final lift loop 

  /* public void liftLoop(){
    switch(liftState){ //find value of hatchliftMasterState and :
      case 0: //if state is 0
          liftMaster.set(ControlMode.Position, floorPos); //sets liftMaster to nuetralPos
          break;
      case 1 : //if state is 1
          liftMaster.set(ControlMode.Position, hatch1Pos); //sets liftMaster to holdPos
          break;
      case 2 : //if state is 2
          liftMaster.set(ControlMode.Position, cargo1Pos); //sets liftMaster to pushPos
          break;
      case 3 : //if state is 3
          liftMaster.set(ControlMode.Position, hatch2Pos); //sets liftMaster to pushPos
          break;
      case 4 : //if state is 4
          liftMaster.set(ControlMode.Position, cargo2Pos); //sets liftMaster to pushPos
          break;
      case 5 : //if state is 5
          liftMaster.set(ControlMode.Position, hatch3Pos); //sets liftMaster to pushPos
          break;
      case 6 : //if state is 6
          liftMaster.set(ControlMode.Position, cargo3Pos); //sets liftMaster to pushPos
          break;
      default :  //if state is unreadable (someone broke something)
          liftMaster.set(ControlMode.PercentOutput, 0); //if hatchliftMasterState is null or non [0-2], turn motor off
          System.out.println("lift shut down, liftState: " + liftState); 
    }
  } 
  */
}
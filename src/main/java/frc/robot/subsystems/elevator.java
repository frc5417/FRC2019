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
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.constant;

/**
 * Add your docs here.
 */
public class elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX liftMaster = new TalonSRX(18); //motor controllers
  VictorSPX liftSlave = new VictorSPX(12);

  DigitalInput topLimit = new DigitalInput(constant.liftTopSwitch); //limit switches
  DigitalInput bottomLimit = new DigitalInput(constant.liftBottomSwitch);
  

  int liftState = 0; //init variable for state of liftMaster
  int setPoint = 0; //used for beta testing lift

  Boolean firstHatchPress = true;

  //the values below need to be changed for final robot, they are in encoder rotation units, 
  //to find values, rotate to the correct point, and find encoder value, and input it in the correct spot 

  static int floorPos = 0;  //position in nuetral state
  static int hatch1Pos = 0; //position in hatch 1 height
  static int hatch2Pos = 0; //position in hatch 2 height 
  static int hatch3Pos = 0; //position in hatch 3 height
  static int cargo1Pos = 0; //position in cargo 1 height
  static int cargo2Pos = 0; //position in cargo 2 height
  static int cargo3Pos = 0; //position in cargo 3 height

  //THE ARRAY BELOW MUST CORRESPOND TO THE SWITCH STATEMENT "liftLoop" TO MAKE ANY SENSE IN THE DRIVER STATION
  String[] liftPositions = {"floor", "hatch1", "cargo1", "hatch2", "cargo2", "hatch3", "cargo3"};

  /*hatch liftMaster has 7 states:

  0:neutral state, not holding or pushing
  1:holding hatch
  2:pushing down hatch

  to change the state of the hatch, change this value to 0, 1, or 2
  */

  
  @Override
  public void initDefaultCommand() { //default command
    //setting pid constants

    //(PID SLOT, Value)
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
        liftMaster.set(ControlMode.Position, 1000); //sets lift to set point 1000
      }
      else {
        liftMaster.set(ControlMode.Position, 0); //sets lift point to set point 0

      }
    }



//prints lift position
    public int getLiftSensor(){
      return liftMaster.getSelectedSensorPosition(); //returns sensor position
    }

  //returns current lift position
  public String getLiftPos(){
    return liftPositions[liftState]; //liftState is current state of the lift used for switch statement, liftPosition is the array 
  }

//zeros lift with driver station button
    public void zeroLift(Boolean button){
      if (button){
        liftMaster.set(ControlMode.PercentOutput, .2);
        if(bottomLimit.get()){
          liftMaster.setSelectedSensorPosition(0); // zero sensor
          liftMaster.set(ControlMode.PercentOutput, 0);
        }
      }
    }

//test percent output for lift
    public void analogLift(Double input){
      if (input > .1 || input < -.1)
      liftMaster.set(ControlMode.PercentOutput, input * .8);
      liftSlave.set(ControlMode.PercentOutput, input * .8);
    }


    // final lift loop 

  public void liftLoop(){
    switch(liftState){ //find value of hatchliftMasterState and :
      case 0: //if state is 0
          setPoint = floorPos; //sets liftMaster to nuetralPos
          break;
      case 1 : //if state is 1
          setPoint = hatch1Pos; //sets liftMaster to hatch 1
          break;
      case 2 : //if state is 2
          setPoint = cargo1Pos; //sets liftMaster to cargo 1
          break;
      case 3 : //if state is 3
          setPoint = hatch2Pos; //sets liftMaster to hatch 2
          break;
      case 4 : //if state is 4
          setPoint = cargo2Pos; //sets liftMaster to cargo 2
          break;
      case 5 : //if state is 5
          setPoint = hatch3Pos; //sets liftMaster to hatch 3 
          break;
      case 6 : //if state is 6
          setPoint = cargo3Pos; //sets liftMaster to cargo 3
          break;
      default :  //if state is unreadable (someone broke something)
          liftMaster.set(ControlMode.PercentOutput, 0); //if hatchliftMasterState is null or non [0-6], turn lift off
          System.out.println("lift shut down, liftState: " + liftState); 
          break;
    }

    liftMaster.set(ControlMode.Position, setPoint);
  } 
  

  public void changeStage(Boolean buttonHatchReleased, Boolean buttonCargoReleased){
    if(buttonHatchReleased && firstHatchPress){
      liftState = 1;
      firstHatchPress = false;
    }

    else if (buttonHatchReleased && !firstHatchPress){
      liftState += 2;
    }

    else if(buttonCargoReleased){
      liftState += 2;
    }
  }

  public void floorReturn(Boolean buttonReleased){
    if (buttonReleased){
      liftState = 0;
      firstHatchPress = true;
    }
  }

}
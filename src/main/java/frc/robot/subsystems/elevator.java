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
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
//import frc.robot.Robot;
import frc.robot.constant;

/**
 * Add your docs here.
 */
public class elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public TalonSRX liftMaster = new TalonSRX(constant.liftMaster); //motor controllers
  public VictorSPX liftSlave = new VictorSPX(constant.liftSlave);

  DigitalInput topLimit = new DigitalInput(constant.liftTopSwitch); //limit switches
  DigitalInput bottomLimit = new DigitalInput(constant.liftBottomSwitch);


  int liftState = 0; //init variable for state of liftMaster
  int setPoint = 0; //where lift wants to go based off lift positions and button panel
  int offSet = 0; //established by moving analog stick, resets after each button press

  int liftStateHatch = 0;
  Boolean firstCargoPress = true;

  //the values below need to be changed for final robot, they are in encoder rotation units,
  //to find values, rotate to the correct point, and find encoder value, and input it in the correct spot

  static int floorPos = 0;  //position in nuetral state
  static int cargoHumanPos = 1250;
  static int cargoFloorPos = 0;
  static int hatch1Pos = 1000;
  static int hatch2Pos = 7000; //position in hatch 2 height
  static int hatch3Pos = 14050; //position in hatch 3 height
  static int cargo1Pos = 4250; //position in cargo 1 height
  static int cargo2Pos = 11200; //position in cargo 2 height
  static int cargo3Pos = 18000; //position in cargo 3 height

  //THE ARRAY BELOW MUST CORRESPOND TO THE SWITCH STATEMENT "liftLoop" TO MAKE ANY SENSE IN THE DRIVER STATION
 // String[] liftPositions = {"floor", "cargo1", "hatch2", "cargo2", "hatch3", "cargo3"};

  /*hatch liftMaster has 7 states:

  0:neutral state, not holding or pushing
  1:holding hatch
  2:pushing down hatch

  to change the state of the hatch, change this value to 0, 1, or 2;';'
  */


  @Override
  public void initDefaultCommand() { //default command

    liftMaster.configVoltageCompSaturation(12.0, 0);
		liftMaster.enableVoltageCompensation(true);

		liftMaster.configNominalOutputForward(0.0, 0);
		liftMaster.configNominalOutputReverse(0.0, 0);

		liftMaster.configPeakOutputForward(1.0, 0);
		liftMaster.configPeakOutputReverse(-0.7, 0);
    //setting pid constant

    liftSlave.follow(liftMaster);

    //(PID SLOT, Value)
    liftMaster.config_kP(0, constant.liftP);
    liftMaster.config_kI(0, constant.liftI);
    liftMaster.config_kD(0, constant.liftD);
    liftMaster.config_kF(0, 0.0);
    //liftMaster.configClosedLoopPeakOutput(0, 1.0);


    liftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative); //init encoder
    liftMaster.setNeutralMode(NeutralMode.Brake); //sets motor to break mode
    liftSlave.setNeutralMode(NeutralMode.Brake);

    liftSlave.follow(liftMaster); //lift slave follows lift master
    liftMaster.setSelectedSensorPosition(0); // zero sensor
    liftMaster.setSensorPhase(true);

    liftMaster.set(ControlMode.Position,0);

    System.out.println("Elevator Initialized");




  }




public void getLimitSwitches(){
  System.out.println("Top Limit: " + topLimit.get() +" Bottom Limit: " + bottomLimit.get());
}

//prints lift position
    public int getLiftSensor(){
      return liftMaster.getSelectedSensorPosition(); //returns sensor position
    }

  //returns current lift position
   // return liftPositions[liftState]; //liftState is current state of the lift used for switch statement, liftPosition is the array


//zeros lift with driver station button
    public void zeroLift(){

       // liftMaster.set(ControlMode.PercentOutput, .2);
        //if(bottomLimit.get()){
          liftMaster.setSelectedSensorPosition(0); // zero sensor
          liftMaster.set(ControlMode.PercentOutput, 0);
       // }

    }



//test percent output for lift
    public void analogLift(Double input){

      liftMaster.set(ControlMode.PercentOutput, input * .8);


  }


    // final lift loop

  public void liftLoop(Integer buttonPressed){
    switch(buttonPressed){ //find value of hatchliftMasterState and :
      case 2: //if state is 0
      //liftMaster.set(ControlMode.Position, cargoHumanPos);
      setPoint = cargoHumanPos;
       //sets liftMaster to nuetralPos
          break;
      case 4 : //if state is 2
      //liftMaster.set(ControlMode.Position, floorPos);
      setPoint = floorPos;
           //sets liftMaster to cargo 1
          break;
      case 6 : //if state is 3
      //liftMaster.set(ControlMode.Position, hatch1Pos);
      setPoint = hatch1Pos;
       //sets liftMaster to hatch 2
          break;
      case 8 : //if state is 4
      //liftMaster.set(ControlMode.Position, hatch2Pos);
      setPoint = hatch2Pos;
          break;
      case 10 : //if state is 5
      //liftMaster.set(ControlMode.Position, hatch3Pos);
      setPoint = hatch3Pos;
          break;
      case 12 : //if state is 6
      //liftMaster.set(ControlMode.Position, cargo3Pos);
      setPoint = cargo3Pos;
          break;
      case 14 :
      //liftMaster.set(ControlMode.Position, cargo2Pos);
      setPoint = cargo2Pos;
          break;
      case 16 :
      //liftMaster.set(ControlMode.Position, cargo1Pos);
      setPoint = cargo1Pos;
          break;
       default :  //if state is unreadable (someone broke something)
      setPoint = 0;
           System.out.println("lift shut down, liftState: " + setPoint);
           break;
    }

    if (setPoint<1)
      setPoint=1;

    //liftMaster.set(ControlMode.Position, setPoint, DemandType.ArbitraryFeedForward, constant.liftF); //.1 = feedfoward

    if(setPoint > constant.ELEVATOR_ZERO) {
      liftMaster.set(ControlMode.Position, setPoint, DemandType.ArbitraryFeedForward, constant.ELEVATOR_F);
    }

    else
    {
      if(liftMaster.getSelectedSensorPosition() > constant.ELEVATOR_ZERO_NEUTRAL_POSITION )
      {
        liftMaster.set(ControlMode.Position, setPoint, DemandType.ArbitraryFeedForward, constant.ELEVATOR_F_DOWN);
      }
      else
      {
        if(liftMaster.getSelectedSensorPosition() < constant.ELEVATOR_ZERO_NEUTRAL_POSITION_DEADBAND)
        {
          liftMaster.set(ControlMode.Position, setPoint, DemandType.ArbitraryFeedForward, constant.ELEVATOR_ZERO_F);

        }
        double slope = (constant.ELEVATOR_F_DOWN - constant.ELEVATOR_ZERO_F) / (constant.ELEVATOR_ZERO_NEUTRAL_POSITION - constant.ELEVATOR_ZERO_NEUTRAL_POSITION_DEADBAND);
        double y_intercept = constant.ELEVATOR_ZERO_F - (slope*constant.ELEVATOR_ZERO_NEUTRAL_POSITION_DEADBAND);
        double linear_F = slope*(liftMaster.getSelectedSensorPosition()) + y_intercept;
        liftMaster.set(ControlMode.Position, setPoint, DemandType.ArbitraryFeedForward, linear_F);
      }
    }

  }

  public void floorReturn(Boolean buttonReleased){
    if (buttonReleased){
      liftState = 0;
      firstCargoPress = true;
    }
  }

  public void changeStageTest(Boolean button){
    if (button){
      liftMaster.set(ControlMode.Position, hatch2Pos);
    }

    else{
      liftMaster.set(ControlMode.Position, 0);
    }
  }



}

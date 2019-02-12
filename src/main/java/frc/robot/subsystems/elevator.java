/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;



/**
 * Add your docs here.
 */
public class elevator extends Subsystem {

  //private CANEncoder neoEncoder;
  private CANPIDController liftPID; //declaring variable for PID controller
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput; //declaring PID constants

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANSparkMax liftMaster = new CANSparkMax(7, MotorType.kBrushless); //CAN Id 6
  CANSparkMax liftSlave = new CANSparkMax(8, MotorType.kBrushed); //CAN Id 7

  double setPoint = 0;
  int liftState = 0;
  Boolean firstHatchPress = false;

  static int liftZero = 0; // Lower lift to the lower most position
  static int liftHatch1 = 0;// Raise lift to first hatch position
  static int liftHatch2 = 0;// Raise lift to second hatch position
  static int liftHatch3 = 0;// Raise lift to third hatch position
  static int liftCargo1 = 0;// Raise lift to first cargo position
  static int liftCargo2 = 0;// Raise lift to second cargo position
  static int liftCargo3 = 0;// Raise lift to first hatch position



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setPoint = 0;

    //neoEncoder = liftMaster.getEncoder(); //assign encoder variable from master NEO 

    liftPID = liftMaster.getPIDController(); //assign name to PID contoller

    liftSlave.follow(liftMaster); //tell slave motor to follow master moter

        // PID coefficients
        kP = 0.05; 
        kI = 0;
        kD = 1; 
        kIz = 0; 
        kFF = 0; 
        kMaxOutput = 1; 
        kMinOutput = -1;
    
        // set PID coefficients
        liftPID.setP(kP);
        liftPID.setI(kI);
        liftPID.setD(kD);
        liftPID.setIZone(kIz);
        liftPID.setFF(kFF);
        liftPID.setOutputRange(kMinOutput, kMaxOutput);

  }
  public void liftloop(){
    switch(liftState){ //find value of hatchIntakeState and :
      case 0: //if state is 0
          setPoint = liftZero; //sets intake to nuetralPos
          break;
      case 1 : //if state is 1
          setPoint = liftHatch1;
          break;
      case 2 : //if state is 2
          setPoint = liftCargo1;
          break;
      case 3 : //if state is 3
          setPoint = liftHatch2;
          break;
      case 4 : //if state is 4
          setPoint = liftCargo2;
          break;
      case 5 : //if state is 5
          setPoint = liftHatch3;
          break;
      case 6 : //if state is 6
          setPoint = liftCargo3;
          break;
      default :  //if state is unreadable (someone broke something)
          liftMaster.set(0); //if liftState is null or non [0-6], turn motor off
          System.out.println("Lift shut down, liftState: " + liftState); 
    }
  }

  public void upHatch(Boolean button_released){
    if (button_released){
      if (firstHatchPress){
        firstHatchPress = true; //state that we have gone up one 
        liftState += 1; //lifts to first hatch stage
      }
      else if (liftState == 5){
        // do nothing
      }
    else{
      liftState += 2; //lifts 1 stage
    }
    }
  }

  public void upBall(Boolean button_released){
    if(button_released){
      liftState += 2; //lifts 1 stage
    }
  }

  public void downStage(Boolean button_released){
    if (button_released){
      liftState -= 2; //drops 1 stage 
    }
  }

  public void floorLift(Boolean button_released){
    if(button_released){
      liftState = 0; //drops lift to the floor
      firstHatchPress = false; //resets hatch first press
    }
  }

  
  public double getSetPoint() {
    return setPoint;
  }

  public int getLiftState() {
    return liftState;
  }

  public void liftLoop(){
    liftPID.setReference(setPoint, ControlType.kPosition); //sets lift to setPoint
  }

  public void elevatorAnalog(Double analogInput){
    setPoint += analogInput;
  }
}


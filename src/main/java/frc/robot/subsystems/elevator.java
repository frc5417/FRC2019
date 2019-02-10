/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;



/**
 * Add your docs here.
 */
public class elevator extends Subsystem {

  private CANEncoder neoEncoder;
  private CANPIDController liftPID; //declaring variable for PID controller
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput; //declaring PID constants

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANSparkMax liftMaster = new CANSparkMax(7, MotorType.kBrushless); //CAN Id 6
  CANSparkMax liftSlave = new CANSparkMax(8, MotorType.kBrushless); //CAN Id 7

  double setPoint = 0;



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setPoint = 0;

    neoEncoder = liftMaster.getEncoder(); //assign encoder variable from master NEO 

    liftPID = liftMaster.getPIDController(); //assign name to PID contoller

    liftSlave.follow(liftMaster); //tell slave motor to follow master moter

        // PID coefficients
        kP = 0.1; 
        kI = 1e-4;
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

        System.out.println(neoEncoder.getPosition()); // prints encoder position


  }

  public void liftLoop(){
    liftPID.setReference(setPoint, ControlType.kPosition);
  }

  public void liftStage(Boolean button){ 
    if (button){
      setPoint += .1; 
    }

  }

  public void dropStage(Boolean button){
    if (button){
      setPoint -= .1;
    }
  }
}


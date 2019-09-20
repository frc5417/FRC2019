


/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.constant;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;





/**
 * Add your docs here.
 */
public class cargoIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  VictorSPX cargoLeft = new VictorSPX(constant.cargoIntakeLeft);
  VictorSPX cargoRight = new VictorSPX(constant.cargoIntakeRight);

  VictorSPX cargoFloorLeft = new VictorSPX(constant.cargoFloorIntakeLeft);
  // VictorSPX cargoFloorRight = new VictorSPX(constant.cargoFloorIntakeRight);
  VictorSPX cargoFloorPivot = new VictorSPX(constant.cargoFloorIntakePivot);

  DigitalInput cargoCheck = new DigitalInput(constant.cargoSenseSwitch);
  DigitalInput floorLimit = new DigitalInput(constant.cargoFloorSwitch);




  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setting nuetral modes
    cargoLeft.setNeutralMode(NeutralMode.Coast);
    cargoRight.setNeutralMode(NeutralMode.Coast);

    cargoFloorLeft.setNeutralMode(NeutralMode.Coast);
    // cargoFloorRight.setNeutralMode(NeutralMode.Coast);

    //setting pivot nuetral mode
    cargoFloorPivot.setNeutralMode(NeutralMode.Brake);

  }

  public void stopCargoIntake(){ //turns off both cargo intake motors
    cargoLeft.set(ControlMode.PercentOutput,0);
    cargoRight.set(ControlMode.PercentOutput,0);

    cargoFloorLeft.set(ControlMode.PercentOutput, 0);
    // cargoFloorRight.set(ControlMode.PercentOutput, 0);
  }

//all following commands are button holds

//hooks cargo left


public void cargoLoop(Integer input){
  switch (input){
    case (0):
      cargoLeft.set(ControlMode.PercentOutput, 1);
      cargoRight.set(ControlMode.PercentOutput, -1); //up
      break;
    case (90):
      cargoFloorLeft.set(ControlMode.PercentOutput,1);
      // cargoFloorRight.set(ControlMode.PercentOutput,1);
      break;
    case (180):
      cargoLeft.set(ControlMode.PercentOutput,-1);
      cargoRight.set(ControlMode.PercentOutput,1);

      cargoFloorLeft.set(ControlMode.PercentOutput, -1);
      // cargoFloorRight.set(ControlMode.PercentOutput, 1);
      break;
    case(270):
      cargoFloorLeft.set(ControlMode.PercentOutput,-1);
      // cargoFloorRight.set(ControlMode.PercentOutput,1);
      break;
    default:
      stopCargoIntake();

  }
}

  public void pivotIntake(Boolean button){
    if(button){
      cargoFloorPivot.set(ControlMode.PercentOutput, 1);
    }
    else{
      cargoFloorPivot.set(ControlMode.PercentOutput, 0);
    }
  }

}

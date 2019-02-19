


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
  VictorSPX cargoFloorRight = new VictorSPX(constant.cargoFloorIntakeRight);
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
    cargoFloorRight.setNeutralMode(NeutralMode.Coast);

    //setting pivot nuetral mode
    cargoFloorPivot.setNeutralMode(NeutralMode.Brake);

  }

  public void stopCargoIntake(){ //turns off both cargo intake motors
    cargoLeft.set(ControlMode.PercentOutput,0);
    cargoRight.set(ControlMode.PercentOutput,0);

    cargoFloorLeft.set(ControlMode.PercentOutput, 0);
    cargoFloorRight.set(ControlMode.PercentOutput, 0);
  }

//all following commands are button holds

//hooks cargo left
  public void cargoLeftHook(Boolean button){ //shoots ball to the left
    if (button) {
      cargoLeft.set(ControlMode.PercentOutput,-.4);
      cargoRight.set(ControlMode.PercentOutput,-1);
    }
    else{
      stopCargoIntake();
    }
  }
  //hooks cargo to the right
  public void cargoRightHook(Boolean button){ //shoots ball to the right
    if (button) {
      cargoLeft.set(ControlMode.PercentOutput,-1);
      cargoRight.set(ControlMode.PercentOutput,-.4);
    }
    else{
      stopCargoIntake();
    }
  }
//shoots cargo straight out
  public void cargoFullSend(Boolean button){ //shoots cargo straight out
    if (button) {
      cargoLeft.set(ControlMode.PercentOutput, 1);
      cargoRight.set(ControlMode.PercentOutput, 1);
    }
    else{
      stopCargoIntake();
    }
    System.out.println("KOBE!!");
  }


//sucks cargo in
  public void cargoPull(Boolean button){ //sucks cargo in
    if (button) {
      cargoLeft.set(ControlMode.PercentOutput,-1);
      cargoRight.set(ControlMode.PercentOutput,-1);

      cargoFloorLeft.set(ControlMode.PercentOutput, -1);
      cargoFloorRight.set(ControlMode.PercentOutput, -1);

      if (!floorLimit.get()){
        cargoFloorPivot.set(ControlMode.PercentOutput, 1);
      }
    }
    else {
      stopCargoIntake();
      if (floorLimit.get()){
        cargoFloorPivot.set(ControlMode.PercentOutput, -.8);
      }
    }
  }

}

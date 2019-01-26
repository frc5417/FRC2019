/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;





/**
 * Add your docs here.
 */
public class cargoIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  VictorSP cargoLeft = new VictorSP(0);
  VictorSP cargoRight = new VictorSP(1);

  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void stopCargoIntake(){
    cargoLeft.set(0);
    cargoRight.set(0);
  }

  public void cargoLeftHook(Boolean button){
    if (button) {
      cargoLeft.set(-.4); //make other side negative if this intakes instead of outtakes
      cargoRight.set(1);
    }
    else{
      stopCargoIntake();
    }
  }
  public void cargoRightHook(Boolean button){
    if (button) {
      cargoLeft.set(-1); //make other side negative if this intakes instead of outtakes
      cargoRight.set(.4);
    }
    else{
      stopCargoIntake();
    }
  }

  public void cargoFullSend(Boolean button){
    if (button) {
      cargoLeft.set(-1); //make other side negative if this intakes instead of outtakes
      cargoRight.set(1);
    }
    else{
      stopCargoIntake();
    }
  }

  public void cargoPull(Boolean button)
    if (button) {
      cargoLeft.set(1); //make other side negative if this outtakes instead of intakes
      cargoRight.set(-1);
    }
    else {
      stopCargoIntake();
    }
  }

}

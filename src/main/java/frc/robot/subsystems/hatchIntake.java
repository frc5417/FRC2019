/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;





/**
 * Add your docs here.
 */
public class hatchIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  VictorSP piston = new VictorSP(3);
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  Boolean buttonScoreState = false;
  Boolean buttonGrabState = false;



  public void hatchForward(Boolean button){
    if(button){
      piston.set(.75);
    }
    else {
      piston.set(0);
    }
  }

  public void hatchReverse(Boolean button){
    if(button){
      piston.set(-.75);
    }
    else {
      piston.set(0);
    }
  }

    
  }
  

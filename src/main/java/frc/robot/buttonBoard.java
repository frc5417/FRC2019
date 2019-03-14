/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;


/**
 * Add your docs here.
 */
public class buttonBoard {
Joystick buttonBoard = new Joystick(2);

int recentPress = 0;

    public Integer getRecentButton(){
        if (buttonBoard.getRawButtonPressed(2)){
            recentPress = 2;
        }
        else if (buttonBoard.getRawButtonPressed(4)){
            recentPress = 4;
        }
        else if (buttonBoard.getRawButtonPressed(6)){
            recentPress = 6;
        }
        else if (buttonBoard.getRawButtonPressed(8)){
            recentPress = 8;
        }
        else if (buttonBoard.getRawButtonPressed(10)){
            recentPress = 10;
        }
        else if (buttonBoard.getRawButtonPressed(12)){
            recentPress = 12;
        }
        else if (buttonBoard.getRawButtonPressed(14)){
            recentPress = 14;
        }
        else if (buttonBoard.getRawButtonPressed(16)){
            recentPress = 16;
        }


        return recentPress;
    }




}

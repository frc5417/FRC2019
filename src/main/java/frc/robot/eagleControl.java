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
public class eagleControl {
    Joystick driverStick;
	boolean wasLBPressed = false;
	boolean LBbuttonPress = false;
	boolean wasRBPressed = false;
	boolean RBbuttonPress = false;
	boolean wasBackPressed = false;
	boolean backButtonPressed = false;
	boolean YButtonPressed = false;
	boolean wasYPressed = false;
	boolean wasXPressed = false;
	boolean XbuttonPress = false;
	boolean wasAPressed = false;
	boolean AbuttonPress = false;
	boolean wasBPressed = false;
	boolean BbuttonPress = false;
	boolean wasRTPressed = false;
	boolean wasLTPressed = false;
	boolean RTbuttonPress = false;
	boolean LTbuttonPress = false;
	boolean wasStartPressed = false;
	boolean StartbuttonPress = false;
	boolean wasDpadleftPressed = false;
	boolean DpadleftPress = false;
	boolean wasDpadrightPressed = false;
	boolean DpadrightPress = false;

	public eagleControl(Joystick driverStick) {
		this.driverStick = driverStick;
	}

	public boolean isLBHeldDown() {
		return driverStick.getRawButton(5);
	}

	public boolean isFirstLBPressed() {
		if (driverStick.getRawButton(5)) {
			if (wasLBPressed)
				LBbuttonPress = false;
			else
				LBbuttonPress = true;

			wasLBPressed = true;
		}
		
		else {
			wasLBPressed = false;
			LBbuttonPress = false;
		}
		return LBbuttonPress;
		
	}
	
	public boolean isFirstXPressed() {
		if (driverStick.getRawButton(3)) {
			if (wasXPressed)
				XbuttonPress = false;
			else
				XbuttonPress = true;

			wasXPressed = true;
		}
		
		else {
			wasXPressed = false;
			XbuttonPress = false;
		}
		return XbuttonPress;
		
	}
	public boolean isFirstDpadleftPressed() {
		if (driverStick.getPOV() == 270) {
			if (wasDpadleftPressed)
				DpadleftPress = false;
			else
				DpadleftPress = true;

			wasDpadleftPressed = true;
		}
		
		else {
			wasDpadleftPressed = false;
			DpadleftPress = false;
		}
		return DpadleftPress;
		
	}
	public boolean isFirstDpadrightPressed() {
		if (driverStick.getPOV() == 90) {
			if (wasDpadrightPressed)
				DpadrightPress = false;
			else
				DpadrightPress = true;

			wasDpadrightPressed = true;
		}
		
		else {
			wasDpadrightPressed = false;
			DpadrightPress = false;
		}
		return DpadrightPress;
		
	}
	public boolean isFirstRBPressed() {
		if (driverStick.getRawButton(6)) {
			if (wasRBPressed)
				RBbuttonPress = false;
			else
				RBbuttonPress = true;

			wasRBPressed = true;
		}
		
		else {
			wasRBPressed = false;
			RBbuttonPress = false;
		}
		return RBbuttonPress;
	}
	public boolean isFirstRTPressed() {
		if (driverStick.getRawAxis(3) > .2) {
			if (wasRTPressed)
				RTbuttonPress = false;
			else
				RTbuttonPress = true;

			wasRTPressed = true;
		}
		
		else {
			wasRTPressed = false;
			RTbuttonPress = false;
		}
		return RTbuttonPress;
	}
	public boolean isFirstLTPressed() {
		if (driverStick.getRawAxis(2) > .2) {
			if (wasLTPressed)
				LTbuttonPress = false;
			else
				LTbuttonPress = true;

			wasLTPressed = true;
		}
		
		else {
			wasLTPressed = false;
			LTbuttonPress = false;
		}
		return LTbuttonPress;
	}
	
	public boolean isFirstBPressed() {
		if (driverStick.getRawButton(2)) {
			if (wasBPressed)
				BbuttonPress = false;
			else
				BbuttonPress = true;

			wasBPressed = true;
		}
		
		else {
			wasBPressed = false;
			BbuttonPress = false;
		}
		return BbuttonPress;
		
	}
	public boolean isFirstAPressed() {
		if (driverStick.getRawButton(1)) {
			if (wasAPressed)
				AbuttonPress = false;
			else
				AbuttonPress = true;

			wasAPressed = true;
		}
		
		else {
			wasAPressed = false;
			AbuttonPress = false;
		}
		return AbuttonPress;
		
	}
	public boolean isFirstStartPressed() {
		if (driverStick.getRawButton(8)) {
			if (wasStartPressed)
				StartbuttonPress = false;
			else
				StartbuttonPress = true;

			wasStartPressed = true;
		}
		
		else {
			wasStartPressed = false;
			StartbuttonPress = false;
		}
		return StartbuttonPress;
		
	}
	
	public boolean isAHeldDown() {
		return driverStick.getRawButton(1);
		
	}
	public boolean isBHeldDown() {
		return driverStick.getRawButton(2);
	}
	public boolean isXHeldDown() {
		return driverStick.getRawButton(3);
		
	}
	public boolean isYHeldDown() {
		return driverStick.getRawButton(4);
		
	}
	public boolean isRBHeldDown() {
		return driverStick.getRawButton(6);
		
	}
	public double getLXValue() {
		return driverStick.getRawAxis(0);
	}
	public double getLYValue() {
		return driverStick.getRawAxis(1);
	}
	public double getLTValue() {
		return driverStick.getRawAxis(2);
	}
	public double getRTValue() {
		return driverStick.getRawAxis(3);
	}
	public double getRXValue() {
		return driverStick.getRawAxis(4);
	}
	public double getRYValue() {
		return driverStick.getRawAxis(5);
	}
	public boolean isStartHeldDown() {
		return driverStick.getRawButton(8);
	}
	public boolean isBackHeldDown() {
		return driverStick.getRawButton(7);
	}
	public boolean isDPadUpHeldDown() {
		return driverStick.getPOV(0) == 0;
	}
	public boolean isFirstBackPressed() {
		if (driverStick.getRawButton(7)) {
			if (wasBackPressed)
				backButtonPressed = false;
			else
				backButtonPressed = true;

			wasBackPressed = true;
		}
		
		else {
			wasBackPressed = false;
			backButtonPressed = false;
		}
		return backButtonPressed;
		
	}
	
	public boolean isFirstYPressed() {
		if (driverStick.getRawButton(4)) {
			if (wasYPressed)
				YButtonPressed = false;
			else
				YButtonPressed = true;

			wasYPressed = true;
		}
		
		else {
			wasYPressed = false;
			YButtonPressed = false;
		}
		return YButtonPressed;
		
	}
}

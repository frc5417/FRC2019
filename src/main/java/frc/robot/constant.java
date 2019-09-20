/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class constant {
    //Motor IDs
    public final static int driveLeftMaster = 7;//talon
    public final static int driveLeftSlave1 = 5;//victor
    public final static int driveLeftSlave2 = 6;//victor
    public final static int driveRightMaster = 9;//
    public final static int driveRightSlave1 = 10;//
    public final static int driveRightSlave2 = 11;//
    public final static int liftMaster = 8;//
    public final static int liftSlave = 12;//
    public final static int cargoFloorIntakeLeft = 13;//
    public final static int cargoFloorIntakeRight = 14;//
    public final static int cargoFloorIntakePivot = 3;//
    public final static int cargoIntake = 1;//
    public final static int hatchIntake = 4;//
    public final static int climbMaster = 20;
    public final static int climbSlave = 21;

    //limit switches
    public final static int hatchZeroSwitch = 0;
    public final static int cargoSenseSwitch = 1;
    public final static int cargoFloorSwitch = 2;
    public final static int liftTopSwitch = 4;
    public final static int liftBottomSwitch = 3;

    //solonoids
    public final static int climberSolenoid = 0;


    //liftPID
    public final static Double liftP = 0.5;
    public final static Double liftI = 0.0;
    public final static Double liftD = 0.0;

    //hatchPID
    public final static Double hatchP = 5.0;
    public final static Double hatchI = 0.0;
    public final static Double hatchD = 0.0;




















}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.cargoIntake;
import frc.robot.subsystems.driveTrain;
import frc.robot.subsystems.elevator;
import frc.robot.subsystems.hatchIntake;
import frc.robot.buttonBoard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();


  //Defining object variables (naming subsystems)
  public static Joystick mController;
  public static Joystick dController;
  public static buttonBoard bBoard;
  public static frc.robot.subsystems.driveTrain drive;
  public static hatchIntake hatchIntake;
  public static cargoIntake cargoIntake;
  public static elevator lift;
  public static double tapeYaw;
  







  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    CameraServer.getInstance().startAutomaticCapture(0);//Camera 1 init
    CameraServer.getInstance().startAutomaticCapture(1); //camera 2 init

    
    

    dController = new Joystick(0);//driver init
    mController = new Joystick(1);//manip init
    bBoard = new buttonBoard();


    drive = new driveTrain();//subsystem init (runs default commands)
    lift = new elevator();
    hatchIntake = new hatchIntake();
    cargoIntake = new cargoIntake();


  }


  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    hatchIntake.zeroIntake();   //DONT FORGET U DID THIS
    lift.zeroLift();

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        teleopPeriodic(); //runs teleop code in auton
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  double rightSpeed;
  double leftSpeed;


  @Override
  public void teleopPeriodic() {

     drive.SetPower(dController.getRawAxis(1), dController.getRawAxis(5)); //drive drive train

     //lift.analogLift((-mController.getRawAxis(2))+mController.getRawAxis(3)); //manip left stick Y axis
     System.out.println(lift.getLiftSensor());
     //System.out.println(bBoard.getRecentButton());
     //lift.getLimitSwitches();
     lift.liftLoop(bBoard.getRecentButton());

     //hatchIntake.driveHatch(mController.getRawButton(5), mController.getRawButton(6));
     //hatchIntake.zeroIntake(SmartDashboard.putBoolean("DB/Button 1", true));
     hatchIntake.cycleHatch(mController.getRawButtonReleased(1), mController.getRawButtonReleased(2), mController.getRawButtonReleased(4)); //manip A
    

     cargoIntake.pivotIntake(mController.getRawButton(3));
     cargoIntake.cargoLoop(mController.getPOV());

    // System.out.println(hatchIntake.getHatchState());

     SmartDashboard.putString("DB/String 0", "Lift Encoder @:" + Integer.toString(lift.getLiftSensor())); //prints encoder pos to dash
     //System.out.println(lift.getLiftSensor());
     //SmartDashboard.putString("DB/String 1", ("Lift position:" + lift.getLiftPos())); //prints lift pos to dash
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }
}

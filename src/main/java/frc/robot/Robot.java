/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.cargoIntake;
import frc.robot.subsystems.driveTrain;
import frc.robot.subsystems.elevator;
import frc.robot.subsystems.hatchIntake;
import edu.wpi.first.cameraserver.CameraServer;


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
  public static frc.robot.subsystems.driveTrain drive;
  public static hatchIntake hatchIntake;
  public static cargoIntake cargoIntake;
  public static elevator lift;
  







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

    
    

    mController = new Joystick(1);//manip init
    dController = new Joystick(0);//driver init

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


  @Overridex
  public void teleopPeriodic() {
 

    drive.SetPower(dController.getRawAxis(1), dController.getRawAxis(5)); //drive drive train

    lift.analogLift(mController.getRawAxis(1)); //manip left stick Y axis
    lift.zeroLift(SmartDashboard.putBoolean("DB/Button 0", true));// dash button 0 to zero lift encoder

    hatchIntake.cycleHatch(mController.getRawButtonReleased(1)); //manip A
    hatchIntake.pushHatch(mController.getRawButtonReleased(2)); //manip B

    cargoIntake.cargoFullSend(mController.getDirectionDegrees() == 0); //Manip D-pad Up
    cargoIntake.cargoRightHook(mController.getDirectionDegrees() == 90); //Manip D-pad Right
    cargoIntake.cargoPull(mController.getDirectionDegrees() == 180); //Manip D-pad Down
    cargoIntake.cargoLeftHook(mController.getDirectionDegrees() == 270); //Manip D-pad Left

    SmartDashboard.putString("DB/String 0", Integer.toString(lift.getLiftSensor())); //prints encoder pos to dash
    


   




  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }
}

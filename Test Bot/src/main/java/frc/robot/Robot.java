package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.APIs.RotationCalculator;


public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  Spark rotateSpark;
  Spark driveLeft1;
  Spark driveLeft2;
  Spark driveRight1;
  Spark driveRight2;

  SpeedControllerGroup rightDrive;
  SpeedControllerGroup leftDrive;
  DifferentialDrive chassis;

  Encoder rotateEnc;

  Joystick joystick1;
  JoystickButton button1;
  JoystickButton button2;
  JoystickButton button3;
  JoystickButton button4;

  RotationCalculator rotationCalculator;
  @Override
  public void disabledInit() {
    // TODO Auto-generated method stub
    rotateEnc.reset();
    super.disabledInit();

  }

  @Override
  public void robotInit() {
    rotateSpark = new Spark(4);
    driveLeft1 = new Spark(1);
    driveLeft2 = new Spark(3);
    driveRight1 = new Spark(2);
    driveRight2 = new Spark(0);

    leftDrive = new SpeedControllerGroup(driveLeft1, driveLeft2);
    rightDrive = new SpeedControllerGroup(driveRight1, driveRight2);
    chassis = new DifferentialDrive(leftDrive, rightDrive); 

    rotateEnc = new Encoder(0,1); //getRaw();

    joystick1 = new Joystick(0); 
    button1 = new JoystickButton(joystick1, 1);
    button2 = new JoystickButton(joystick1, 2);
    button3 = new JoystickButton(joystick1, 3);
    button4 = new JoystickButton(joystick1, 4);

    rotationCalculator = new RotationCalculator(1000, 1);

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  @Override
  public void teleopPeriodic() {
    if (joystick1.getRawButton(1)  == true) {
      rotateSpark.setSpeed(rotationCalculator.calculatePower(rotateEnc.getRaw(), .25));
      System.out.println(rotationCalculator.calculatePower(rotateEnc.getRaw(), .25));
      System.out.println("go to .25");
    } else if (button2.get() == true) {
      rotateSpark.setSpeed(rotationCalculator.calculatePower(rotateEnc.getRaw(), .5));
      System.out.println("go to .5");
    } else if (button3.get() == true) {
      rotateSpark.setSpeed(rotationCalculator.calculatePower(rotateEnc.getRaw(), 1));
      System.out.println("go to 1");
    } else if (button4.get() == true) {
      rotateSpark.setSpeed(rotationCalculator.calculatePower(rotateEnc.getRaw(), 0));
      System.out.println("go to 0");
    } else {
      rotateSpark.setSpeed(0);
    }
    System.out.println("working?");
    System.out.println(rotateEnc.getRaw());
    chassis.tankDrive(joystick1.getRawAxis(0), joystick1.getRawAxis(1));
  }

  @Override
  public void testPeriodic() {

  }
}
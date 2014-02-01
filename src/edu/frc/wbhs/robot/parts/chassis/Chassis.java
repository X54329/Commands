package edu.frc.wbhs.robot.parts.chassis;

import edu.frc.wbhs.robot.parts.Spikemotor;
import edu.frc.wbhs.robot.parts.pid.PIDOut;
import edu.frc.wbhs.robot.parts.pid.PIDSauce;
import edu.frc.wbhs.robot.parts.pid.PIDWrapper;
import edu.frc.wbhs.robot.parts.sensors.*;
import edu.frc.wbhs.robot.parts.Shooter;
import edu.wpi.first.wpilibj.templates.RobotTemplate;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Brendan, Brian
 */
public class Chassis {

	private DriveSide leftdrive;
	private DriveSide rightdrive;

	public GyroscopeWrapper gyro;
	private AccelerometerWrapper accelerometer;
	public PIDWrapper gyroPID;
	public PIDOut gyroPIDOut;
	public PIDSauce gyroPIDSauce;
	private AnalogChannel tilt;
	private Shooter shoot;
	private Spikemotor spike;
	public PickupArms arms;
	private DirectionalEncoder leftEncoder;
	private DirectionalEncoder rightEncoder;

	// private SomeSensor weirdsensor;
	public Chassis(int[] leftdrivePinIDs, int[] rightdrivePinIDs, int gyroPinID, int accelerometerPinID, int tilt, int SpikePin, int[] encoderPinsLeft, int[] encoderPinsRight) {
		System.out.print("Setting up chassis on the following pins:" + leftdrivePinIDs + " and " + rightdrivePinIDs + "...");
		leftdrive = new DriveSide(leftdrivePinIDs);
		rightdrive = new DriveSide(rightdrivePinIDs);
		System.out.println("done");
		// Initialize driving sensors
		System.out.print("Setting up gyro and accelerometer on pins " + gyroPinID + " and " + accelerometerPinID + "...");
		gyro = new GyroscopeWrapper(gyroPinID);
		accelerometer = new AccelerometerWrapper(accelerometerPinID);
		System.out.println("done");
		// Initialize PIDs for drive with sensors
		gyroPIDOut = new PIDOut();
		gyroPIDSauce = new PIDSauce(0);
		gyroPID = new PIDWrapper(RobotTemplate.GYRO_PID_P, RobotTemplate.GYRO_PID_I, RobotTemplate.GYRO_PID_D, RobotTemplate.GYRO_PID_F, gyroPIDSauce, gyroPIDOut, 5);
		spike = new Spikemotor(SpikePin);
		shoot = new Shooter();
		//arms = new PickupArms();

		leftEncoder = new DirectionalEncoder(encoderPinsLeft[1], encoderPinsLeft[0], RobotTemplate.WHEEL_DIAMETER);
		rightEncoder = new DirectionalEncoder(encoderPinsRight[1], encoderPinsRight[0], RobotTemplate.WHEEL_DIAMETER);

	}

	public void drive(double xAxis, double yAxis, double zAxis, int mode) {

		// NOTE: How can we add some kind of traction control into this?
		//       We should make this code cleaner.
		double leftSidePower = 0;
		double rightSidePower = 0;
		double requestedLinearSpeed = 0;
		double requestedAngularSpeed = 0;
		double gyroExpectedSpeed = 0;
		double gyroPidChange = 0;
		double speedScale = (zAxis / 2) + 0.5;
		//System.out.println("X Axis: " + xAxis);
		//System.out.println("Y Axis: " + yAxis);
		if (mode == 0) { // Manual mode
			if (Math.abs(xAxis) > 0.09 || Math.abs(yAxis) > 0.09) { //deadzone

				requestedLinearSpeed = yAxis;
				requestedAngularSpeed = xAxis;
				rightSidePower = (requestedLinearSpeed + requestedAngularSpeed); //this might turn the wrong way
				leftSidePower = (requestedLinearSpeed - requestedAngularSpeed);
				gyroExpectedSpeed = requestedAngularSpeed * RobotTemplate.ROBOT_MAX_ANGULAR_SPEED;
				//gyroPIDSauce.setSauceVal(gyro.getRate());
				//gyroPID.setSetpoint(gyroExpectedSpeed / RobotTemplate.ROBOT_MAX_ANGULAR_SPEED);
				//gyroPidChange = gyroPIDOut.getOutput();
				//leftSidePower += gyroPidChange * RobotTemplate.GYRO_PID_MULTIPLIER;
				//rightSidePower -= gyroPidChange * RobotTemplate.GYRO_PID_MULTIPLIER;
				leftdrive.setSpeed(RobotTemplate.LEFT_SIDE_MULTIPLIER * leftSidePower * speedScale);
				rightdrive.setSpeed(RobotTemplate.RIGHT_SIDE_MULTIPLIER * rightSidePower * speedScale);

			} else {
				leftdrive.setSpeed(0);
				rightdrive.setSpeed(0);
			}
			//System.out.println("Speed Scalar:" + speedScale);
			//System.out.println("Left Side Encoder Speed:" + -leftEncoder.getSpeed() * 6.28 / (1140 * 12));
			//System.out.println("Right Side Encoder Speed:" + rightEncoder.getSpeed() * 6.28 / (1140 * 12));
			System.out.println(gyro.getAngle());
		} else { // if auto mode is selected
			requestedLinearSpeed = yAxis;
			requestedAngularSpeed = xAxis;
			rightSidePower = (requestedLinearSpeed + requestedAngularSpeed); //this might turn the wrong way
			leftSidePower = (requestedLinearSpeed - requestedAngularSpeed);
			gyroExpectedSpeed = requestedAngularSpeed * RobotTemplate.ROBOT_MAX_ANGULAR_SPEED;
			leftdrive.setSpeed(RobotTemplate.LEFT_SIDE_MULTIPLIER * leftSidePower);
			rightdrive.setSpeed(RobotTemplate.RIGHT_SIDE_MULTIPLIER * rightSidePower);
		}
	}

	public void shoot(Joystick joystick) {
		drive(0, shoot.shoot(joystick), 1, 1);
	}

}

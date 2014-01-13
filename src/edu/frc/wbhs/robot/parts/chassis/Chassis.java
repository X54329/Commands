package edu.frc.wbhs.robot.parts.chassis;

import edu.frc.wbhs.robot.parts.sensors.*;
import edu.wpi.first.wpilibj.templates.RobotTemplate;

/**
 *
 * @author Brendan, Brian
 */
public class Chassis {

	private DriveSide leftdrive;
	private DriveSide rightdrive;

	private GyroscopeWrapper gyro;
	private AccelerometerWrapper accelerometer;
	// private SomeSensor weirdsensor;

	public Chassis(int[] leftdrivePinIDs, int[] rightdrivePinIDs, int gyroPinID, int accelerometerPinID) {
		System.out.print("Setting up chassis on the following pins:" + leftdrivePinIDs + " and " + rightdrivePinIDs + "...");
		leftdrive = new DriveSide(leftdrivePinIDs);
		rightdrive = new DriveSide(rightdrivePinIDs);
		System.out.println("done");
		System.out.print("Setting up gyro and accelerometer on pins " + gyroPinID + " and " + accelerometerPinID + "...");
		gyro = new GyroscopeWrapper(gyroPinID);
		accelerometer = new AccelerometerWrapper(accelerometerPinID);
		System.out.println("done");
	}

	public void drive(double xAxis, double yAxis, int mode) {
		double leftSidePower = 0;
		double rightSidePower = 0;
		double requestedLinearSpeed = 0;
		double requestedAngularSpeed = 0;
		double gyroExpectedSpeed = 0;
		if (mode == 0) { // arcade mode is selected
			requestedLinearSpeed = xAxis;
			requestedAngularSpeed = yAxis;
			rightSidePower = (requestedLinearSpeed + requestedAngularSpeed); //this might turn the wrong way
			leftSidePower = (requestedLinearSpeed - requestedAngularSpeed);
			gyroExpectedSpeed = requestedAngularSpeed * RobotTemplate.ROBOT_MAX_ANGULAR_SPEED;

		}
		leftdrive.setSpeed(leftSidePower);
		rightdrive.setSpeed(rightSidePower);

	}

}

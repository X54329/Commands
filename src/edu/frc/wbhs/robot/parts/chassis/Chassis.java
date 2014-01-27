package edu.frc.wbhs.robot.parts.chassis;

import edu.frc.wbhs.robot.parts.Spikemotor;
import edu.frc.wbhs.robot.parts.pid.PIDOut;
import edu.frc.wbhs.robot.parts.pid.PIDSauce;
import edu.frc.wbhs.robot.parts.pid.PIDWrapper;
import edu.frc.wbhs.robot.parts.sensors.*;
import edu.frc.wbhs.robot.parts.Shooter;
import edu.wpi.first.wpilibj.templates.RobotTemplate;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Brendan, Brian
 */
public class Chassis {

	private DriveSide leftdrive;
	private DriveSide rightdrive;

	private GyroscopeWrapper gyro;
	private AccelerometerWrapper accelerometer;
	private PIDWrapper gyroPID;
	private PIDOut gyroPIDOut;
	private PIDSauce gyroPIDSauce;
	private AnalogChannel tilt;
	private Shooter shoot;
	private Spikemotor spike;
        public PickupArms arms;
	// private SomeSensor weirdsensor;

	public Chassis(int[] leftdrivePinIDs, int[] rightdrivePinIDs, int gyroPinID, int accelerometerPinID, int tilt, int SpikePin) {
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
		arms = new PickupArms();

	}

	public void drive(double xAxis, double yAxis, int mode) {

		// NOTE: How can we add some kind of traction control into this?
		//       We should make this code cleaner.
		double leftSidePower = 0;
		double rightSidePower = 0;
		double requestedLinearSpeed = 0;
		double requestedAngularSpeed = 0;
		double gyroExpectedSpeed = 0;
		double gyroPidChange = 0;
		System.out.println("X Axis: " + xAxis);
		System.out.println("Y Axis: " + yAxis);
		if (Math.abs(xAxis) > 0.09 || Math.abs(yAxis) > 0.09) {
			if (mode == 0) { // arcade mode is selected
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
			}
			leftdrive.setSpeed(RobotTemplate.LEFT_SIDE_MULTIPLIER * leftSidePower);
			rightdrive.setSpeed(RobotTemplate.RIGHT_SIDE_MULTIPLIER * rightSidePower);
			System.out.println("Driving");
		} else {
			leftdrive.setSpeed(0);
			rightdrive.setSpeed(0);
		}
	}

	public void shoot() {
		drive(0, shoot.shoot(), 0);
	}

}

package edu.frc.wbhs.robot.parts.chassis;

import edu.frc.wbhs.robot.parts.pid.PIDOut;
import edu.frc.wbhs.robot.parts.pid.PIDSauce;
import edu.frc.wbhs.robot.parts.pid.PIDWrapper;
import edu.frc.wbhs.robot.parts.sensors.*;
import edu.frc.wbhs.robot.parts.shooter;
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
	private shooter shoot;
	// private SomeSensor weirdsensor;

	public Chassis(int[] leftdrivePinIDs, int[] rightdrivePinIDs, int gyroPinID, int accelerometerPinID
		,int tilt)
		{
		System.out.print("Setting up chassis on the following pins:" + leftdrivePinIDs + " and " + rightdrivePinIDs + "...");
		leftdrive = new DriveSide(leftdrivePinIDs);
		rightdrive = new DriveSide(rightdrivePinIDs);
		System.out.println("done");
		System.out.print("Setting up gyro and accelerometer on pins " + gyroPinID + " and " + accelerometerPinID + "...");
		gyro = new GyroscopeWrapper(gyroPinID);
		accelerometer = new AccelerometerWrapper(accelerometerPinID);
		System.out.println("done");
		gyroPIDOut = new PIDOut();
		gyroPIDSauce = new PIDSauce(0);
		gyroPID = new PIDWrapper(RobotTemplate.GYRO_PID_P, RobotTemplate.GYRO_PID_I, RobotTemplate.GYRO_PID_D, RobotTemplate.GYRO_PID_F, gyroPIDSauce, gyroPIDOut, 5);
		
		shoot = new shooter();
	}

	public void drive(double xAxis, double yAxis, int mode) {
		double leftSidePower = 0;
		double rightSidePower = 0;
		double requestedLinearSpeed = 0;
		double requestedAngularSpeed = 0;
		double gyroExpectedSpeed = 0;
		double gyroPidChange =0;
		if (mode == 0) { // arcade mode is selected
			requestedLinearSpeed = yAxis;
			requestedAngularSpeed = xAxis;
			rightSidePower = (requestedLinearSpeed + requestedAngularSpeed); //this might turn the wrong way
			leftSidePower = (requestedLinearSpeed - requestedAngularSpeed);
			gyroExpectedSpeed = requestedAngularSpeed * RobotTemplate.ROBOT_MAX_ANGULAR_SPEED;
			gyroPIDSauce.setSauceVal(gyro.getRate());
			gyroPID.setSetpoint(gyroExpectedSpeed / RobotTemplate.ROBOT_MAX_ANGULAR_SPEED);
			gyroPidChange = gyroPIDOut.getOutput();
			
			leftSidePower += gyroPidChange * RobotTemplate.GYRO_PID_MULTIPLIER;
			rightSidePower -= gyroPidChange * RobotTemplate.GYRO_PID_MULTIPLIER;
		}
		leftdrive.setSpeed(leftSidePower);
		rightdrive.setSpeed(rightSidePower);

	}
	
	public void shoot()
	{
		drive(0, shoot.shoot(), 0);
	}

}

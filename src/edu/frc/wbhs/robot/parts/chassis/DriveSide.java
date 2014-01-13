package edu.frc.wbhs.robot.parts.chassis;

import edu.frc.wbhs.robot.parts.Motor;
import edu.frc.wbhs.robot.parts.Wheel;

/**
 *
 * @author Brendan, Brian
 */

/*
 * Note: Because the sides of the robot have more than 1 wheel, we might want
 *       them as objects if the Wheel class has it's own sensors. For now, it's
 *	 pointless.
 *	 If we're creating a wheel with no motor, we might want to have the
 *	 Wheel class only contain sensors and have a MotorizedWheel class extend
 *	 it with the motor object there.
 */
public class DriveSide {

	private Motor drive;
	public Wheel drivewheel;

	public DriveSide(int[] pinIDs) {
		drive = new Motor(pinIDs[0]);
		drivewheel = new Wheel(drive);
	}

	public void setSpeed(double speed) {
		drivewheel.motor.setSpeed(speed);
	}

}

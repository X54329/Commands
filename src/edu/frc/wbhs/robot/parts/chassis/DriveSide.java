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
	private Motor drive2;
	private int i;

	public DriveSide(int[] pinIDs, int Inverted) {
		drive = new Motor(pinIDs[0]);
		drive2 = new Motor(pinIDs[1]);
		i = Inverted;
	}

	public void setSpeed(double speed) {
		drive.setPower(i*speed);
		drive2.setPower(i*speed);
	}

}

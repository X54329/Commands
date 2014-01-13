package edu.frc.wbhs.robot.parts;

import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Brendan, Brian
 */
public class Motor {

	private Victor actualmotor;

	public Motor(int pinID) {
		actualmotor = new Victor(pinID);
	}

	public double getPower() {
		return actualmotor.getSpeed();
	}

	public void setPower(double power) {
		actualmotor.set(power);
	}

}

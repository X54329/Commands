package edu.frc.wbhs.robot.parts;

/**
 *
 * @author Brendan
 */
public class Wheel {

	/**
	 * Diameter of the robot's wheels in inches.
	 */
	public static final double DIAMETER = 4.0;

	public Motor motor;

	public Wheel(Motor m) {
		this.motor = m;
	}

	public double getSpeed() {
		return motor.getPower() * DIAMETER; //we have to measure the speed at full power to make this useful
	}

}

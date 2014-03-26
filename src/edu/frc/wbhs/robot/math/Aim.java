package edu.frc.wbhs.robot.math;

import edu.wpi.first.wpilibj.templates.RobotTemplate;

/**
 *
 * @author Brendan
 */
public class Aim {
	/*	public double pickDistance(double motorPower)
	 {
	 double distance = (RobotTemplate.MOTOR_TO_VELOCITY_PROPORTION*motorPower)*(RobotTemplate.MOTOR_TO_VELOCITY_PROPORTION*motorPower)* Math.sin(RobotTemplate.THETA) * Math.cos(RobotTemplate.THETA)/ RobotTemplate.G;
	 return distance;

	 } */

	public double pickPower(double distance, double angle) //preferably in degrees
	{
		return ((1/RobotTemplate.MOTOR_TO_VELOCITY_PROPORTION) * ((Math.tan(angle) - Math.tan(RobotTemplate.THETA))) / (distance*Math.cos(angle) * RobotTemplate.PARABOLA_VELOCITY_CONSTANT));
	}
}

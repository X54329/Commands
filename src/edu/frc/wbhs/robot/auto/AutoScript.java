package edu.frc.wbhs.robot.auto;

import edu.frc.wbhs.robot.Robot;
import edu.frc.wbhs.robot.math.Point2D;
import edu.wpi.first.wpilibj.templates.RobotTemplate;

/**
 *
 * @author Brendan
 */
public class AutoScript {
	// This is where we create methods we use in the autonomous mode
	// TODO: make these methods do something
	
	// Reference to the robot
	private Robot robot;
	
	public AutoScript(Robot robot) {
		this.robot = robot;
	}
	
	public void runScript() {
		// Do autonomous script
	}
	
	/**
	 * 
	 * @param distance in wheel rotations
	 * @param speed from -1.0 to 1.0
	 * @param anglechange in degrees per second
	 */
	public void autoDrive(double distance, double speed, double anglechange) {
		//TODO: set up counting sensors and magic math for this
		
		double psudoxAxis = anglechange / RobotTemplate.ROBOT_MAX_ANGULAR_SPEED;
		double psudoyAxis = speed;
		
		robot.chassis.drive(psudoxAxis, psudoyAxis, 0);
		
		// how to stop? :'(
	}
	
	public void autoTurn(double degrees) {
		robot.chassis.drive(degrees / RobotTemplate.ROBOT_MAX_ANGULAR_SPEED, 0, 0);
		// how do we know when to stop?
	}
	
	public Point2D getFieldLocation() {
		//hey, should we get color sensors?
		return null;
	}
	
}

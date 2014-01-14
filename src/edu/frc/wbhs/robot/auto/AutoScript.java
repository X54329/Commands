package edu.frc.wbhs.robot.auto;

import edu.frc.wbhs.robot.Robot;
import edu.frc.wbhs.robot.math.Point2D;

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
	 * @param distance (won't mean anything)
	 * @param speed from -1.0 to 1.0
	 * @param anglechange in degrees per second
	 */
	public void autoDrive(double distance, double speed, double anglechange) {
		//TODO: set up counting sensors and magic math for this
		
		double psudoxAxis = anglechange / 180;
		double psudoyAxis = speed;
		
		robot.chassis.drive(psudoxAxis, psudoyAxis, 0);
		
	}
	
	public void autoTurn(double degrees, double speed) {
		
	}
	
	public Point2D getFieldLocation() {
		//hey, should we get color sensors?
		return null;
	}
	
}

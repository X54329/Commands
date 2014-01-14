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
	
	public void autoDrive(double distance, double speed, double anglechange) {
		//TODO: set up counting sensors and magic math for this
	}
	
	public void autoTurn(double degrees, double speed) {
		
	}
	
	public Point2D getFieldLocation() {
		//hey, should we get color sensors?
		return null;
	}
	
}

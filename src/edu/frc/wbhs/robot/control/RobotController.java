/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.control;

import edu.frc.wbhs.robot.Robot;
import edu.frc.wbhs.robot.auto.AutoScript;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.templates.RobotTemplate;

/**
 *
 * @author brendan
 */
public class RobotController {
	// this class takes all of the information from each different control section and decides how to use it
	// cancels out pushing by taking accelerometer & encoder data.
	// Runs all of the autoscript things sequentially, etc.

	public void run(Robot robot, Joystick joy, AutoScript scripter) {
		int num = 0;
		/*
		 PUT THINGS YOU WANT TO RUN ALWAYS, SUCH AS JOYSTICK CONTROL, OUTSIDE OF THE SWITCH
		 */

		if (joy.getRawAxis(RobotTemplate.X_AXIS_CHANNEL) > RobotTemplate.JOYSTICK_DEAD_ZONE) {
			// set num to something that lets us break out safely and give full control
			// back to driver
			if (num == 0) {
				num = 1;
			}
		} else {
			if (joy.getRawButton(1)) {
				num = 8;
			}
		}
		switch (num) {
			case 0: // 0 means we are starting up
				;
			case 1: // normal joystick control
				robot.drive(joy, 0);
				
			case 8: // auto shoot
				if(scripter.shoot())
				{
					num = 9;
				}
			case 9: // clean up from autoshooting?
				;

		}

	}

	public void dealWithSensors() {
		// get encoder and accelerometer vals to calculate linear speed
		// continuously use smart integration to calculate distance travelled
		// continuously use gyro, encoders, and camera to deal with angle
		// integrate all the vectors
		// Know Where We Are
	}
}

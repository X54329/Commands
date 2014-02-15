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
		int state = 0;
		/*
		 PUT THINGS YOU WANT TO RUN ALWAYS, SUCH AS JOYSTICK CONTROL, OUTSIDE OF THE SWITCH
		 */

		if (joy.getRawAxis(RobotTemplate.X_AXIS_CHANNEL) > RobotTemplate.JOYSTICK_DEAD_ZONE) {
			// set num to something that lets us break out safely and give full control
			// back to driver
			if (state == 0) {
				state = 1;
			}
		} else {
			if (joy.getRawButton(1)) {
				state = 8;
			}
			
			if(joy.getRawButton(2))
			{
				state = 15;
			}
		}
		if(joy.getRawButton(5))
		{
			state = 0;
		}
		switch (state) {
			case 0: // 0 means we are starting up
				break;
			case 1: // normal joystick control
				robot.drive(joy, 0);
				break;
			case 8: // auto shoot
				if (scripter.shoot()) {
					state = 9;
				}
				break;
			case 9: // clean up from autoshooting?
				break;

			case 15: // Shoot the ball
				if (scripter.pickUpBall() == 0) {
					;
				} else if (scripter.pickUpBall() == 1) {
					state = 16;
				} else if (scripter.pickUpBall() == 2) {
					state = 1;
				}
				break;
			case 16:
				break;

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

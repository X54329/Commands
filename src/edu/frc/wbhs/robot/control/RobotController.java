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

	double armPower = 0;
	int state;

	public void run(Robot robot, Joystick joy, AutoScript scripter) {

		/*
		 PUT THINGS YOU WANT TO RUN ALWAYS, SUCH AS JOYSTICK CONTROL, OUTSIDE OF THE SWITCH
		 */
		System.out.println("state: " + state);
		if (joy.getRawAxis(RobotTemplate.X_AXIS_CHANNEL) > RobotTemplate.JOYSTICK_DEAD_ZONE) {
			// set num to something that lets us break out safely and give full control
			// back to driver
			if (state == 0) {
				state = 1;
			}
		} else {
			if (joy.getRawButton(1)) {
				//state = 8;
			}

			if (joy.getRawButton(2)) {
				state = 15;
			}
		}
		if (joy.getRawButton(5)) {
			//state = 0;
		}
		switch (state) {
			case 0: // 0 means we are starting up
				state = 1;
				break;
			case 1: // normal joystick control
				robot.drive(joy, 0);
				//System.out.println("Arm Pot Val " + robot.chassis.arms.getPotVal());
				System.out.println("Catapult Pot Val: " + robot.chassis.catapult.pot.getVoltage());
				if (joy.getRawButton(4)) {
					armPower += 0.01;
					if (robot.chassis.arms.getPotVal() < RobotTemplate.POT_ARMS_MAX_SAFE) {
						robot.chassis.arms.motor1.setPower(-armPower);
					} else {
						//robot.chassis.arms.motor1.setPower(-1);
					}
				} else if (joy.getRawButton(5)) {
					armPower += 0.01;
					if (robot.chassis.arms.getPotVal() < RobotTemplate.POT_ARMS_MIN_SAFE) {
						robot.chassis.arms.motor1.setPower(armPower);
					} else {
						//robot.chassis.arms.motor1.setPower(-1);
					}
				} else {
					armPower = 0;
					robot.chassis.arms.motor1.setPower(0);
				}
				if (joy.getRawButton(6)) {
					robot.chassis.arms.motor2.setPower(-0.70);
				} else if (joy.getRawButton(7)) {
					robot.chassis.arms.motor2.setPower(0.70);
				} else {
					robot.chassis.arms.motor2.setPower(0);
					//System.out.println("Set to 0");
				}
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
					//System.out.println("Hello!");
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

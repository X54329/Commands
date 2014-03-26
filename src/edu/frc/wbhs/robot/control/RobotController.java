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
import edu.wpi.first.wpilibj.Timer;

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
	boolean DRIVING = true;
	Timer t = new Timer();

	public void run(Robot robot, Joystick joy, AutoScript scripter) {

		/*
		 PUT THINGS YOU WANT TO RUN ALWAYS, SUCH AS JOYSTICK CONTROL, OUTSIDE OF THE SWITCH
		 */
		if (DRIVING) {
			robot.drive(joy, 0);
		} else {
			// Stops the robot from staying on
			robot.chassis.drive(0, 0, 0, 0);
		}

		System.out.println("state: " + state);
		if (joy.getRawAxis(RobotTemplate.X_AXIS_CHANNEL) > RobotTemplate.JOYSTICK_DEAD_ZONE) {
			// set num to something that lets us break out safely and give full control
			// back to driver
			if (state == 0) {
				state = 1;
				DRIVING = true;
			}
		} else {
			if (joy.getRawButton(1)) {
				//state = 15;
			}

			if (joy.getRawButton(1)) {
				if (state == 1) {
					state = 7;
				}
			} else if (joy.getRawButton(2)) {
				state = 9;
				//} else if(joy.getRawButton(4)) {
				//	state = 10;
			} else {
				state = 1;
			}
		}

		if (joy.getRawButton(7)) {
			state = 0;
		}
		switch (state) {
			case 0: // 0 means we are starting up
				state = 1;
				DRIVING = true;
				break;
			case 1: // normal joystick control
				System.out.println("Running state 1");
				DRIVING = true;
				//System.out.println("Arm Pot Val " + robot.chassis.arms.getPotVal());
				//System.out.println("Catapult Pot Val: " + robot.chassis.catapult.pot.getVoltage());
				if (joy.getRawButton(4)) {
					armPower += 0.01;
					if (robot.chassis.arms.getPotVal() < RobotTemplate.POT_ARMS_MAX_SAFE) {
						robot.chassis.arms.motor1.setPower(-armPower);
					} else {
						//robot.chassis.arms.motor1.setPower(-1);
					}
				} else if (joy.getRawButton(5)) {
					armPower += 0.4;
					if (armPower > 1) {
						armPower = 1;
					}
					if (robot.chassis.arms.getPotVal() < RobotTemplate.POT_ARMS_MIN_SAFE) {
						robot.chassis.arms.motor1.setPower(armPower);
					} else {
						//robot.chassis.arms.motor1.setPower(-1);
					}
				} else {
					armPower = 0;
					robot.chassis.arms.motor1.setPower(0);
				}
				if (joy.getRawButton(9)) {
					robot.chassis.arms.motor2.setPower(-0.70);
				} else if (joy.getRawButton(3)) {
					robot.chassis.arms.motor2.setPower(0.70);
				} else {
					robot.chassis.arms.motor2.setPower(0);
					//System.out.println("Set to 0");
				}
				robot.chassis.catapult.shoot(0.0, 0.0);
				System.out.println("chassis arms pot: " + robot.chassis.arms.getPotVal());
				System.out.println("catapult pot: " + robot.chassis.catapult.pot.getVoltage());
				//System.out.println("gyro: " + robot.chassis.gyro.getRate());
				System.out.println("left Encoder: " + robot.chassis.leftEncoder.getSpeed());
				System.out.println("right Encoder: " + robot.chassis.rightEncoder.getSpeed());
				System.out.println("USD: " + robot.chassis.shooter.USD.getDistanceInches());
				//System.out.println("USD2: " + robot.chassis.shooter.USD2.getDistanceInches());
				break;
			case 7:
				t.reset();
				t.start();
				state = 8;
				break;
			case 8: // auto shoot
				DRIVING = false;
				if (t.get() < 400) {
					System.out.println("Shooting");
					robot.chassis.catapult.shoot(1, 1);
					System.out.println("POWER: " + 1);
				} else {
					robot.chassis.catapult.shoot(0, 0);
					t.stop();
					t.reset();
					state = 9;
				}
				break;

			case 9: // clean up from autoshooting?
				DRIVING = true;
				t.reset();
				t.start();
				state = 10;
				break;
			case 10:
				if (t.get() < 1200) {
					robot.chassis.catapult.shoot(-0.20, -0.20);
				} else {
					robot.chassis.catapult.shoot(0, 0);
					state = 1;
				}
				break;
			case 15: // Shoot the ball
				DRIVING = false;
				if (scripter.pickUpBall() == 0) {
					//System.out.println("Hello!");
				} else if (scripter.pickUpBall() == 1) {
					state = 16;
				} else if (scripter.pickUpBall() == 2) {
					state = 1;
				}
				break;
			case 16:
				DRIVING = true;
				break;
			case 22:
				DRIVING = true;
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

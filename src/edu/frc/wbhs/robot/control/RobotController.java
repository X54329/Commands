/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.control;

import edu.frc.wbhs.robot.Robot;
import edu.frc.wbhs.robot.auto.AutoScript;
import edu.frc.wbhs.robot.auto.MSTimer;
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
	private MSTimer timer = new MSTimer();

	private void doDrive(Robot robot, Joystick drive) {
		if (DRIVING) {
			// Drive with the joystick
			robot.drive(drive, 0);

		} else {
			// Set motor values back to zero
			robot.chassis.drive(0, 0, 0, 0);
		}
	}

	public void run(Robot robot, Joystick joy, int joyID, AutoScript scripter) {

		/*
		 PUT THINGS YOU WANT TO RUN ALWAYS, SUCH AS JOYSTICK CONTROL, OUTSIDE OF THE SWITCH
		 */
		// Joystick driving (with check) #########################################################################
		if (joyID == RobotTemplate.JOYSTICK) {
			doDrive(robot, joy);
		}

		// Debug
		//System.out.println("state: " + state);
		System.out.println(RobotTemplate.POT_ARMS_DOWN_VOLT + " : " + robot.chassis.arms.getPotVal() + " : " + RobotTemplate.POT_ARMS_UP_VOLT);

		// JOYSTICK 1 things #####################################################################################
		if (joyID == RobotTemplate.JOYSTICK) {
			if (joy.getRawAxis(RobotTemplate.X_AXIS_CHANNEL) > RobotTemplate.JOYSTICK_DEAD_ZONE) {
				// set num to something that lets us break out safely and give full control
				// back to driver
				if (state == 0) {
					state = 1;
					DRIVING = true;
				}
			}

			if (joy.getRawButton(2)) {
				state = 55;
			}
		} // JOYSTICK 2 things #####################################################################################
		else if (joyID == 2) {

			if (joy.getRawButton(1)) { // Button 1 (trigger) - Autoshoot in SM ========================================
				if (state == 1) {
					state = 7;
				}
			} else if (joy.getRawButton(10)) { // Button 10 (?) - Autoshoot+rollers ===================================
				if (state == 1) {
					state = 50;
				}
			} else if (joy.getRawButton(6)) { // Button 6 (base left up) - Cleanup autoshoot ============================
				state = 9;
				/*} else if(joy.getRawButton( 4)) {
				 state = 10;*/
			} else {
				state = 1;
			}
		}

		if (joy.getRawButton(7)) { // Button 7 (base left down) - Emergency? reset ====================================
			state = 0;
		}

		switch (state) {
			case 0: // STARTING UP ###############################################################################
				state = 1;
				DRIVING = true;
				break;
			case 1: // JOYSTICK CONTROL ##########################################################################

				//System.out.println("Running state 1");
				DRIVING = true;

				/*System.out.println("Arm Pot Val " + robot.chassis.arms.getPotVal());
				 System.out.println("Catapult Pot Val: " + robot.chassis.catapult.pot.getVoltage());*/
				// SECONDARY JOYSTICK ############################################################################
				if (joyID == 2) {

					// ARM UP? MOVEMENT ##########################################################################
					if (joy.getRawButton(4)) { // Button 4 (left) - Arms up ===========================================
						armPower += 0.01;
						if (robot.chassis.arms.getPotVal() < RobotTemplate.POT_ARMS_UP_VOLT) {
							robot.chassis.arms.updownMotor.setPower(-armPower);
						}/* else {
						 robot.chassis.arms.motor1.setPower(-1);
						 }*/

					} // ARM DOWN? MOVEMENT ########################################################################
					else if (joy.getRawButton(5)) { // Button 5 (right) - Arms down ===================================
						armPower += 0.4;
						if (armPower > 1) {
							armPower = 1;
						}
						if (robot.chassis.arms.getPotVal() > RobotTemplate.POT_ARMS_DOWN_VOLT) {
							robot.chassis.arms.updownMotor.setPower(armPower); // ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !
						} else {
							//robot.chassis.arms.motor1.setPower(-1);
						}
					} else {
						robot.chassis.arms.updownMotor.setPower(0);
					}
					// ROLLERS IN? MOVEMENT ######################################################################
					if (joy.getRawButton(2)) { // Button 2 (down) - Rollers in ========================================
						robot.chassis.arms.rollerMotor.setPower(-0.75);
					} // ROLLERS OUT? MOVEMENT #####################################################################
					else if (joy.getRawButton(3)) { // Button 3 (middle) - Rollers out ================================
						robot.chassis.arms.rollerMotor.setPower(0.75);
					} else {
						robot.chassis.arms.rollerMotor.setPower(0);
						//System.out.println("Set to 0");
					}
				}

				robot.chassis.catapult.shoot(0.0, 0.0);

				//System.out.println("chassis arms pot: " + robot.chassis.arms.getPotVal());
				//System.out.println("catapult pot: " + robot.chassis.catapult.pot.getVoltage());
				//System.out.println("gyro: " + robot.chassis.gyro.getRate());
				//System.out.println("left Encoder: " + robot.chassis.leftEncoder.getSpeed());
				//System.out.println("right Encoder: " + robot.chassis.rightEncoder.getSpeed());
				//System.out.println("USD: " + robot.chassis.shooter.USD.getDistanceInches());
				//System.out.println("USD2: " + robot.chassis.shooter.USD2.getDistanceInches());
				break;
			case 7: // AUTO SHOOT BEGIN ##########################################################################
				timer.reset();
				timer.set(400);
				state = 8;
				break;
			case 8: // AUTO SHOOT CONTINUOUS #####################################################################
				DRIVING = false;
				if (!timer.elapsed()) { // at 400
					System.out.println("Shooting");

					double power;

					// z axis shooting
					//power = joy.getRawAxis(RobotTemplate.Z_AXIS_CHANNEL) / 2.0 + 0.5;
					// full power shooting
					power = 1;

					robot.chassis.catapult.shoot(power, power);

					System.out.println("POWER: " + power);
					timer.update();
				} else {
					robot.chassis.catapult.shoot(0, 0);
					state = 9;
				}
				break;

			case 9: // CLEANUP AUTO SHOOT BEGIN ##################################################################
				DRIVING = true;
				//timer.reset();
				//timer.set(1200);
				state = 10;
				break;
			case 10: // CLEANUP AUTO SHOOT CONTINUOUS ############################################################
				//if (!timer.isPast(1200)) {
				//	timer.update();
				robot.chassis.catapult.shoot(-0.2, -0.2);
				//} else {
				//	robot.chassis.catapult.shoot(0, 0);
				state = 1;
				//}
				break;
			case 15: // UNUSED (Shoot the ball?) #################################################################
				DRIVING = false;
				if (scripter.pickUpBall() == 0) {
					//System.out.println("Hello!");
				} else if (scripter.pickUpBall() == 1) {
					state = 16;
				} else if (scripter.pickUpBall() == 2) {
					state = 1;
				}
				break;
			case 16: // UNUSED ##########################################
				DRIVING = true;
				break;
			case 22: // UNUSED ##########################################
				DRIVING = true;
				break;
			case 50: // AUTOSHOOT + ROLLERS BEGIN ################################################################
				timer.reset();
				timer.set(1000 + 333);
				state = 51;
				break;
			case 51: // AUTOSHOOT + ROLLERS CONTINUOUS ###########################################################
				DRIVING = false;
				if (!timer.isPast(333)) {
					robot.chassis.arms.rollerMotor.setPower(-1);
				} else if (!timer.elapsed()) { // at 400+250
					System.out.println("Shooting with rollers");

					double power;

					// z axis shooting
					//power = joy.getRawAxis(RobotTemplate.Z_AXIS_CHANNEL) / 2.0 + 0.5;
					// full power shooting
					power = 1;

					robot.chassis.catapult.shoot(power, power);

					System.out.println("POWER: " + power);
				} else {
					robot.chassis.arms.rollerMotor.setPower(0);
					robot.chassis.catapult.shoot(0, 0);
					state = 9;
				}
				timer.update();
				break;
			case 55:
				if (Math.abs(joy.getRawAxis(RobotTemplate.X_AXIS_CHANNEL)) < RobotTemplate.JOYSTICK_DEAD_ZONE || Math.abs(joy.getRawAxis(RobotTemplate.X_AXIS_CHANNEL)) < RobotTemplate.JOYSTICK_DEAD_ZONE) { //deadzone
					robot.chassis.getInPosition();
				} else {
					state = 1;
				}
				break;
			case 56:
				break;
			case 57:
				break;

		}

	}

	public void dealWithSensors() { // Unused
		// get encoder and accelerometer vals to calculate linear speed
		// continuously use smart integration to calculate distance travelled
		// continuously use gyro, encoders, and camera to deal with angle
		// integrate all the vectors
		// Know Where We Are
	}
}

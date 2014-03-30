package edu.frc.wbhs.robot.auto;

import edu.frc.wbhs.robot.Robot;
import edu.frc.wbhs.robot.math.Point2D;
import edu.frc.wbhs.robot.parts.pid.PIDOut;
import edu.frc.wbhs.robot.parts.pid.PIDSauce;
import edu.frc.wbhs.robot.parts.pid.PIDWrapper;
import edu.frc.wbhs.robot.parts.sensors.DirectionalEncoder;
import edu.frc.wbhs.robot.parts.sensors.GyroscopeWrapper;
import edu.wpi.first.wpilibj.templates.RobotTemplate;

/**
 *
 * @author Brian
 *
 * make a find ball get ball aim ball shoot ball return to side pass ball
 *
 */
public class AutoScript {
	// This is where we create methods we use in the autonomous mode
	// TODO: make these methods do something

	// Reference to the robot
	private Robot robot;
	private DirectionalEncoder leftEncoder;
	private PIDWrapper leftEncoderPID;
	private PIDOut leftEncoderPIDOut;
	private PIDSauce leftEncoderPIDSauce;
	private DirectionalEncoder rightEncoder;
	private PIDWrapper rightEncoderPID;    //
	private PIDOut rightEncoderPIDOut;     // These aren't being used
	private PIDSauce rightEncoderPIDSauce; //
	private boolean CurrentlyDriving;
	private GyroscopeWrapper gyro;
	private PIDWrapper gyroPID;
	private PIDOut gyroPIDOut;
	private PIDSauce gyroPIDSauce;
	private int ballPickupStage = 1; // Number from 1 to 3 that gives what we're supposed to do next

	public AutoScript(Robot robot) {
		this.robot = robot;

		//leftEncoder = new DirectionalEncoder(1, 2, RobotTemplate.WHEEL_DIAMETER);
		leftEncoderPIDOut = new PIDOut();
		leftEncoderPIDSauce = new PIDSauce(0);
		leftEncoderPID = new PIDWrapper(RobotTemplate.ENCODER_PID_P, RobotTemplate.ENCODER_PID_I, RobotTemplate.ENCODER_PID_D, RobotTemplate.ENCODER_PID_F, leftEncoderPIDSauce, leftEncoderPIDOut, 0.05);

		//rightEncoder = new DirectionalEncoder(3, 4, RobotTemplate.WHEEL_DIAMETER);
		rightEncoderPIDOut = new PIDOut();
		rightEncoderPIDSauce = new PIDSauce(0);
		rightEncoderPID = new PIDWrapper(RobotTemplate.ENCODER_PID_P, RobotTemplate.ENCODER_PID_I, RobotTemplate.ENCODER_PID_D, RobotTemplate.ENCODER_PID_F, rightEncoderPIDSauce, rightEncoderPIDOut, 0.05);

		gyro = robot.chassis.gyro;
		gyroPIDOut = new PIDOut();
		gyroPIDSauce = new PIDSauce(0);
		gyroPID = new PIDWrapper(RobotTemplate.GYRO_PID_P, RobotTemplate.GYRO_PID_I, RobotTemplate.GYRO_PID_D, RobotTemplate.GYRO_PID_F, gyroPIDSauce, gyroPIDOut, 0.05);
		gyroPID.enable();
	}

	public void init() {
		timer.reset();
		scriptTimer.reset();
		state = 0;
	}

	private int state = 1;
	private MSTimer scriptTimer = new MSTimer();
	private double armPower = 0;

	public void runScript() {
		// Implementation finished:
		//		- Only driving forward
		//

		switch (state) {
			case 0: // PICK UP BALL
				scriptTimer.reset();
				scriptTimer.set(333);
				state = 2;
				break;
			case 1: // UNUSED
				if (!scriptTimer.elapsed()) {
					robot.chassis.arms.rollerMotor.setPower(-0.85); // Rollers in
				} else {
					state = 2;
				}
				break;
			case 2:
				scriptTimer.reset();
				scriptTimer.set(1000);
				
				state = 3;
				break;
			case 3:
				if (!scriptTimer.elapsed()) {
					robot.chassis.arms.updownMotor.setPower(0.50); // Arms down
				} else {
					robot.chassis.arms.updownMotor.setPower(0); // Arms relax after time
					robot.chassis.arms.rollerMotor.setPower(0);
					state = 5;
					resetDrive();
					scriptTimer.reset();
					scriptTimer.set(300);
				}
				break;
			case 5:
				// MOVE CERTAIN TIME
				if (scriptTimer.elapsed()) {
					boolean done = workingDrive(3100, 0.5); // TUNE THE TIME
					if (done) {
						state = 10;
					}
				}
				break;
			case 10: // PUSH BALL IN POSITION --- UNUSED
				scriptTimer.reset();
				scriptTimer.set(0);
				state = 11;
				break;
			case 11:
				if (!scriptTimer.elapsed()) {
					robot.chassis.arms.rollerMotor.setPower(-0.85); // Rollers in
				} else {
					robot.chassis.arms.rollerMotor.setPower(0); // Rollers stop
					scriptTimer.reset();
					scriptTimer.set(2000);
					state = 12;
				}
				break;
			case 12: // LET BALL REST
				if (scriptTimer.elapsed()) {
					state = 15;
				}
				break;
			case 15: // AUTOSHOOT WITH ARMS DOWN BEGIN ###########################################################
				scriptTimer.reset();
				scriptTimer.set(800);
				state = 16;
				break;
			case 16: // AUTOSHOOT WITH ARMS DOWN CONTINUOUS PART 1 ######################################################
				if (!scriptTimer.elapsed()) {
					armPower += 0.4;
					if (armPower > 1) {
						armPower = 1;
					}
					if (robot.chassis.arms.getPotVal() > RobotTemplate.POT_ARMS_DOWN_VOLT) {
						robot.chassis.arms.updownMotor.setPower(armPower);
					}
					robot.chassis.arms.rollerMotor.setPower(-1);
				} else {
					// Arms left pushing down until end
					state = 17;
					scriptTimer.reset();
					scriptTimer.set(1500);
				}

				break;
			case 17: // AUTOSHOOT WITH ARMS DOWN CONTINUOUS PART 2 ##########################################

				if (!scriptTimer.elapsed()) {
					System.out.println("Shooting with arms down");

					double power = 1;

					robot.chassis.catapult.shoot(power, power);

					System.out.println("POWER: " + power);
				} else {
					state = 18; // or 1
					scriptTimer.reset();
					scriptTimer.set(600);
				}

				break;
			case 18:

				if (!scriptTimer.elapsed()) {
					robot.chassis.catapult.shoot(-0.2, -0.2);
					robot.chassis.arms.rollerMotor.setPower(0);
				} else {
					robot.chassis.catapult.shoot(0, 0);
					robot.chassis.arms.updownMotor.setPower(0);
					state = 999;
				}

				break;
		}
		scriptTimer.update();
	}

	boolean driving = false;
	private MSTimer timer = new MSTimer();

	public void resetDrive() {
		driving = false;
		timer.reset();
	}

	public boolean workingDrive(int mseconds, double speed) {
		if (!driving) {
			timer.set(mseconds);
			driving = true;
		} else if (!timer.elapsed()) {
			robot.chassis.drive(0, speed, 1, 0); // Drives
		}

		if (driving && timer.elapsed()) {
			robot.chassis.drive(0, 0, 0, 0); // Stops the robot
			return true;
		}

		timer.update();
		return false;
	}

	public boolean autoDrive(double distance, double speed) { //returns false while driving, true when completed

		double pseudoXAxis = 0;
		double pseudoYAxis = speed;

		if (!CurrentlyDriving) {

			robot.chassis.leftEncoder.resetCounter();
			robot.chassis.rightEncoder.resetCounter();

			leftEncoderPID.reset();
			leftEncoderPID.setSetpoint(distance);
			leftEncoderPID.enable();
			System.out.println("distance Setpoint: " + distance);

			CurrentlyDriving = true;

		} else {
			leftEncoderPIDSauce.setSauceVal(robot.chassis.leftEncoder.getDistance());
			System.out.println("Distance: " + robot.chassis.leftEncoder.getDistance());
			//double encoderPIDThreshold = 0;
			if (robot.chassis.leftEncoder.getDistance() >= distance) {//robot.chassis.leftEncoder.getDistance() < distance && robot.chassis.rightEncoder.getDistance() < distance) {
				pseudoYAxis = leftEncoderPIDOut.getOutput();
				System.out.println("PID: " + pseudoYAxis);

				robot.chassis.drive(pseudoXAxis, pseudoYAxis, 1, 0);
				return false;
			} else {
				System.out.println("Done");
				CurrentlyDriving = false;
				return true;
			}

		}
		return false;
	}

	public void autoTurn(double degrees) {
		robot.chassis.drive(degrees / RobotTemplate.ROBOT_MAX_ANGULAR_SPEED, 0, 1, 0);
	}

	public Point2D getFieldLocation() {
		// hey, should we get color sensors?
		// TODO: make this not return null
		return null;
	}

	public int pickUpBall() { //returns 0 when running, 1 when successfully completed, 2 when ball wasn't sucessfully picked up
		// Only call when next to ball 

		// Move Arms Down and turn on rollers:
		//System.out.println("Picking up ball");
		if (ballPickupStage == 1) {
			if (!robot.chassis.arms.moveArmsDown()) {
				robot.chassis.arms.moveRollers(1);
			} else {
				ballPickupStage = 2;
			}
		}
		if (ballPickupStage == 2) {
			if (!robot.chassis.arms.moveArmsUp()) {
				robot.chassis.arms.moveRollers(1);
			} else {
				ballPickupStage = 1;
				if (robot.chassis.arms.isBallInPlace()) {
					return 1;
				} else {
					return 2;
				}

			}
		}
		return 0;
	}

	public boolean shoot() {
		//if (robot.chassis.arms.moveArmsDown()) {
		// shoot with catapult
		//}
		//robot.shoot();
		return robot.chassis.shooter.readyToFire;
		// reset
		// put arms back up
		//return false;
	}

	public boolean moveToHeader(double degrees) {
		double GyroPid = 0;
		System.out.println(degrees);
		gyroPID.setSetpoint(degrees);
		gyroPIDSauce.setSauceVal(robot.chassis.gyro.getAngle());
		GyroPid = gyroPIDOut.getOutput();
		robot.chassis.drive(GyroPid, 0, 0, 1);
		System.out.println(robot.chassis.gyro.getAngle());
		System.out.println("GyroPid " + GyroPid);
		return false;
	}

}

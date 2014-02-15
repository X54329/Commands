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
 * @author Brendan
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
	private int ballPickupStage; // Number from 1 to 3 that gives what we're supposed to do next

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

	public void runScript() {
		autoDrive(10, 0.5, 0);
	}

	/**
	 *
	 * @param distance in wheel rotations
	 * @param speed from -1.0 to 1.0
	 * @param anglechange in degrees per second
	 * @return
	 */
	public boolean autoDrive(double distance, double speed, double anglechange) { //returns false while driving, true when completed
		//TODO: set up counting sensors and magic math for this

		double pseudoXAxis = 0;
		double pseudoYAxis = speed;
		double gyroPidChange = 0;
		double gyroExpectedDistance = anglechange;
		boolean doneTurning = false;

		if (anglechange - 3 < gyro.getAngle() && gyro.getAngle() < anglechange + 3) {
			doneTurning = true;
		}
		if (!doneTurning) {
			//gyroPIDSauce.setSauceVal(gyro.getRate());
			//gyroPID.setSetpoint(gyroExpectedDistance);
			//gyroPidChange = gyroPIDOut.getOutput();
			//robot.chassis.drive(gyroPidChange, 0, 1, 0);
			doneTurning = true;
		}

		if (doneTurning) {
			if (!CurrentlyDriving) {

				robot.chassis.leftEncoder.resetCounter();
				robot.chassis.rightEncoder.resetCounter();

				//leftEncoderPID.reset();
				leftEncoderPID.setSetpoint(distance);
				leftEncoderPID.enable();
				System.out.println("distance Setpoint: " + distance);

				CurrentlyDriving = true;

			} else {
				leftEncoderPIDSauce.setSauceVal(robot.chassis.leftEncoder.getDistance());
				System.out.println("Distance: " + robot.chassis.leftEncoder.getDistance());
				//double encoderPIDThreshold = 0;
				if (robot.chassis.leftEncoder.getDistance() <= distance) {//robot.chassis.leftEncoder.getDistance() < distance && robot.chassis.rightEncoder.getDistance() < distance) {
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
		if (robot.chassis.arms.moveArmsDown()) {
			// shoot with catapult
		}

		// reset
		// put arms back up
		return false;
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

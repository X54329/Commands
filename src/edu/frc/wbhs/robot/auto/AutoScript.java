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
    private DirectionalEncoder leftSideEncoder;
    private DirectionalEncoder rightSideEncoder;
    private boolean CurrentlyDriving;
    private GyroscopeWrapper gyro;
    private PIDWrapper gyroPID;
    private PIDOut gyroPIDOut;
    private PIDSauce gyroPIDSauce;

    public AutoScript(Robot robot) {
        this.robot = robot;
        leftSideEncoder = new DirectionalEncoder(0, 0, RobotTemplate.WHEEL_DIAMETER);
        rightSideEncoder = new DirectionalEncoder(0, 0, RobotTemplate.WHEEL_DIAMETER);
        gyro = new GyroscopeWrapper(RobotTemplate.GYRO_PIN);
        gyroPIDOut = new PIDOut();
        gyroPIDSauce = new PIDSauce(0);
        gyroPID = new PIDWrapper(RobotTemplate.GYRO_PID_P, RobotTemplate.GYRO_PID_I, RobotTemplate.GYRO_PID_D, RobotTemplate.GYRO_PID_F, gyroPIDSauce, gyroPIDOut, 0.05);

    }

    public void runScript() {
        // Do autonomous script
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

        double psudoxAxis = 0;
        double psudoyAxis = speed;
        double gyroPidChange = 0;
        double gyroExpectedDistance = anglechange;
        boolean doneTurning = false;

        if (anglechange - 3 < gyro.getAngle() && gyro.getAngle() < anglechange + 3) {
            doneTurning = true;
        }
        if (!doneTurning) {
            gyroPIDSauce.setSauceVal(gyro.getRate());
            gyroPID.setSetpoint(gyroExpectedDistance);
            gyroPidChange = gyroPIDOut.getOutput();
            robot.chassis.drive(gyroPidChange, 0, 0);
        }

        if (doneTurning) {
            if (!CurrentlyDriving) {

                leftSideEncoder.resetCounter();
                rightSideEncoder.resetCounter();
                CurrentlyDriving = true;

            } else {
                if (leftSideEncoder.getDistance() < distance && rightSideEncoder.getDistance() < distance) {
                    robot.chassis.drive(psudoxAxis, psudoyAxis, 0);
                    return false;
                } else {
                    return true;
                }

            }
            return false;
        }
        return false;

    }

    public void autoTurn(double degrees) {
        robot.chassis.drive(degrees / RobotTemplate.ROBOT_MAX_ANGULAR_SPEED, 0, 0);
    }

    public Point2D getFieldLocation() {
        // hey, should we get color sensors?
        // TODO: make this not return null
        return null;
    }

    public void pickUpBall() {

    }

}

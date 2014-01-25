/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.chassis;

import edu.frc.wbhs.robot.parts.Motor;
import edu.frc.wbhs.robot.parts.pid.PIDOut;
import edu.frc.wbhs.robot.parts.pid.PIDSauce;
import edu.frc.wbhs.robot.parts.pid.PIDWrapper;
import edu.frc.wbhs.robot.parts.sensors.PotWrapper;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.templates.RobotTemplate;

/**
 *
 * @author Brian
 */
public class Catapult {

	private Motor motor1;
	private Motor motor2;
	
	private DigitalInput stopsensorFront;
	private DigitalInput stopsensorBack;
	
	private PotWrapper pot;
	private double backVolts;
	
	private PIDWrapper potPID;
	private PIDOut potPIDOut;
	private PIDSauce potPIDSauce;

	public Catapult(int[] motorPinIds, int[] digiInputIds) {
		motor1 = new Motor(motorPinIds[0]);
		motor2 = new Motor(motorPinIds[1]);
		stopsensorFront = new DigitalInput(digiInputIds[0]);
		stopsensorBack = new DigitalInput(digiInputIds[1]);
	}
	
	public void calibrateBackAngle() {
		backVolts = pot.getVoltage();
	}

	public void update() {
		if (stopsensorFront.get()) {
			stop();
		}
		/*potPIDOut = new PIDOut();
		potPIDSauce = new PIDSauce(0);
		potPID = new PIDWrapper(RobotTemplate.SHOOT_PID_P, RobotTemplate.SHOOT_PID_I, RobotTemplate.SHOOT_PID_D, RobotTemplate.SHOOT_PID_F, potPIDSauce, potPIDOut, 0.05);*/
	}

	public void shoot(double power) {
		motor1.setPower(power);
		motor2.setPower(power);
	}

	public void reset() {
		// TODO: Add PID to make sure it stays back
		motor1.setPower(-0.1);
		motor2.setPower(-0.1);
	}

	public void stop() {
		motor1.setPower(0);
		motor2.setPower(0);
	}

}

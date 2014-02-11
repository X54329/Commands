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

	private PotWrapper pot;
	private double backVolts;
	private boolean resetting;

	private PIDWrapper potPID;
	private PIDOut potPIDOut;
	private PIDSauce potPIDSauce;

	public Catapult(int[] motorPinIds) {
		motor1 = new Motor(motorPinIds[0]);
		motor2 = new Motor(motorPinIds[1]);
		potPIDOut = new PIDOut();
		potPIDSauce = new PIDSauce(0);
		potPID = new PIDWrapper(RobotTemplate.SHOOT_PID_P, RobotTemplate.SHOOT_PID_I, RobotTemplate.SHOOT_PID_D, RobotTemplate.SHOOT_PID_F, potPIDSauce, potPIDOut, 0.05);
	}

	public void calibrate() {
		backVolts = pot.getVoltage();
	}

	public void update() {
		//if (stopsensorFront.get()) {
		//	stop();
		//}
		
		if (resetting) {
			if (pot.getVoltage() - backVolts < 0.01) {
				resetting = false;
				stop();
			} else {
				double potPidChange;
				potPIDSauce.setSauceVal(pot.getVoltage());
				potPID.setSetpoint(backVolts);
				potPidChange = potPIDOut.getOutput();
				motor1.setPower(potPidChange);
				motor2.setPower(potPidChange);
			}
		}
	}

	public void shoot(double power) {
		motor1.setPower(power);
		motor2.setPower(power);
	}

	public void reset() {
		resetting = true;
	}

	public void stop() {
		motor1.setPower(0);
		motor2.setPower(0);
	}

}

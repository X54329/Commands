/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.chassis;

import edu.frc.wbhs.robot.parts.Motor;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Brian
 */
public class Catapult {

	private Motor motor1;
	private Motor motor2;
	private DigitalInput stopsensorFront;
	private DigitalInput stopsensorBack;

	public Catapult(int[] motorPinIds, int[] digiInputIds) {
		motor1 = new Motor(motorPinIds[0]);
		motor2 = new Motor(motorPinIds[1]);
		stopsensorFront = new DigitalInput(digiInputIds[0]);
		stopsensorBack = new DigitalInput(digiInputIds[1]);
	}

	public void update() {
		if (stopsensorFront.get() || stopsensorBack.get()) {
			stop();
		}
	}

	public void shoot() {
		motor1.setPower(1.0);
		motor2.setPower(1.0);
	}

	public void reset() {
		// TODO: Add PID to make sure it stays back
		motor1.setPower(-1.0);
		motor2.setPower(-1.0);
	}

	public void stop() {
		motor1.setPower(0);
		motor2.setPower(0);
	}

}

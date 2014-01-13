/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.sensors;

import edu.wpi.first.wpilibj.Accelerometer;

/**
 *
 * @author Brendan
 */
public class AccelerometerWrapper {

	Accelerometer a;

	public AccelerometerWrapper(int inputPin) {
		this.a = new Accelerometer(inputPin);
	}

	public double getAccel() {
		return a.getAcceleration();
	}

	public double pidGet() {
		return a.pidGet();
	}

	public void setZero(double d) {
		a.setZero(d); //I'm not sure why we need a double
	}
}

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

	private Accelerometer realaccelerometer;

	public AccelerometerWrapper(int inputPin) {
		this.realaccelerometer = new Accelerometer(inputPin);
	}

	public double getAccel() {
		return realaccelerometer.getAcceleration();
	}

	public double pidGet() {
		return realaccelerometer.pidGet();
	}

	public void setZero(double d) {
		realaccelerometer.setZero(d); //I'm not sure why we need a double
	}
}

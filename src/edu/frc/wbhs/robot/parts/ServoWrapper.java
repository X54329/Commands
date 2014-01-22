/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frc.wbhs.robot.parts;

import edu.wpi.first.wpilibj.Servo;

/**
 *
 * @author Brian
 */
public class ServoWrapper {
	
	private Servo servo;
	private double minAngle;
	private double maxAngle;
	
	public ServoWrapper(int digiInputPin, double minAngle, double maxAngle) {
		servo = new Servo(digiInputPin);
		this.minAngle = minAngle;
		this.maxAngle = maxAngle;
	}
	
	public void set(double angle) {
		if (angle < minAngle) angle = minAngle;
		if (angle > maxAngle) angle = maxAngle;
		servo.set((angle - minAngle) / (maxAngle - minAngle));
	}
	
	public double get() {
		return servo.get();
	}
	
}

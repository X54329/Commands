/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.sensors;

import edu.wpi.first.wpilibj.Ultrasonic;

/**
 *
 * @author Brian
 */
public class USDWrapper {

	private Ultrasonic usdsensor;
	
	public USDWrapper(int inputPinID, int outputPinID) {
		usdsensor = new Ultrasonic(inputPinID, outputPinID);
	}

	public double getDistanceInches() {
		return usdsensor.getRangeInches();
	}
	
}

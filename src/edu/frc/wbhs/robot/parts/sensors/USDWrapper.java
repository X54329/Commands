/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.sensors;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Brian
 */
public class USDWrapper {

	public AnalogChannel usdsensor;
	
	public USDWrapper(int inputPinID, int outputPinID) {
		usdsensor = new AnalogChannel(inputPinID);
		//usdsensor.setAutomaticMode(true);
		//usdsensor.setEnabled(true);
	}

	public double getDistanceInches() {
		return 50 * usdsensor.getVoltage();
	}
	
}

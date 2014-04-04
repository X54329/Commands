/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalOutput;

/**
 *
 * @author Brian
 */
public class USDWrapper {
	
	public AnalogChannel usdsensor;
	//public DigitalOutput usdPower;
	private double fakeLastValue;
	private double realLastValue;
	
	public USDWrapper(int inputPinID) {
		System.out.println("Initializing USD on Analog Pin " + inputPinID);//+" and digital pin " + outputPinID);
		usdsensor = new AnalogChannel(inputPinID);
		//usdPower = new DigitalOutput(outputPinID);
		//usdPower.set(false);
		fakeLastValue = 0;
		realLastValue = 0;
		//usdsensor.setAutomaticMode(true);
		//usdsensor.setEnabled(true);
	}
	
	public double getDistanceInches() {
		// The magic number is the calculated volts per inch
		double currentVal = usdsensor.getVoltage() * 12.0 * 6.0 * 116.0 / 102.0;
		
		if (realLastValue != fakeLastValue) {
			;
		} else if (Math.abs(currentVal / realLastValue) > 0.01) {
			fakeLastValue = realLastValue;
			realLastValue = currentVal;
			return fakeLastValue;
		} else {
			realLastValue = currentVal;
			fakeLastValue = currentVal;
			return currentVal;
		}
		return currentVal;
	}
	
	/*public void turnOn() {
		usdPower.set(true);
	}
	
	public void turnOff() {
		usdPower.set(false);
	}
	
	public void pulse(double timeInMicroS) {
		usdPower.pulse(timeInMicroS / 1000000);
	}*/
}

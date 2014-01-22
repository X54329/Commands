/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frc.wbhs.robot.parts.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Brian
 */
public class DigitalInputWrapper {
	
	private DigitalInput limitswitch;
	
	public DigitalInputWrapper(int digitalInputPin) {
		limitswitch = new DigitalInput(digitalInputPin);
	}
	
	public boolean get() {
		return limitswitch.get();
	}
	
}

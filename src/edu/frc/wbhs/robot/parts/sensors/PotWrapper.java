/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frc.wbhs.robot.parts.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Brendan
 */
public class PotWrapper {
	
	private AnalogChannel pot;
	
	public PotWrapper(int inputID) {
		this.pot = new AnalogChannel(inputID);
	}
	
	public double getVoltage() {
		return pot.getVoltage();
	}
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.pid;

import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author Brendan
 * @author Josh
 * ask about encoder
 */
public class PIDSauce implements PIDSource {
	private double inputForPid;
	
	public PIDSauce(double input) {
		inputForPid = input;
	}

	public double pidGet() {
		return inputForPid;
	}

	public void setSauceVal(double inputValue)
	{
		inputForPid = inputValue;
	}
}

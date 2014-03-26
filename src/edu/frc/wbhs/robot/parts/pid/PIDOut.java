/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frc.wbhs.robot.parts.pid;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 * @author Brendan
 */
public class PIDOut implements PIDOutput {
	private double out;
	public void pidWrite(double output) {
		out = output;
	}
	
	public double getOutput()
	{
		return out;
	}
	
}

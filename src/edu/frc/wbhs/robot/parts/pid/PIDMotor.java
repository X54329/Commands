/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.pid;

import edu.frc.wbhs.robot.parts.Motor;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Brian
 */
public class PIDMotor extends Motor {

	// Reference to external potentiometer or something
	private AnalogChannel input;

	public PIDMotor(int pinID, AnalogChannel input) {
		super(pinID);
		this.input = input;
	}
	
}

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
public class PIDMotor {

	// References
	private AnalogChannel input;
	private Motor motor;

	public PIDMotor(AnalogChannel input, Motor m) {
		this.input = input;
		motor = m;
	}
}

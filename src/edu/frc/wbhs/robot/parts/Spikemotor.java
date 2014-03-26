/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frc.wbhs.robot.parts;

import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Jo
 */
public class Spikemotor {
	
	Relay Spike;
	public Spikemotor(int pin)
	{
			Spike = new Relay(pin);
	}
	
	public void goForeward()
	{
		Spike.set(Relay.Value.kForward);
	}
	
	public void goBackward()
	{
		Spike.set(Relay.Value.kReverse);
	}
	
	public void stop()
	{
		Spike.set(Relay.Value.kOff);
	}
	
}
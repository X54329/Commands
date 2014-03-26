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

public class PneumaticCylinder {
	
	private Relay expand;
	private Relay contract;
	
	public PneumaticCylinder(int expand, int contract)
	{
		this.expand = new Relay(expand);
		this.contract = new Relay(contract);
	}
	
	public void Expand()
	{
		this.expand.set(Relay.Value.kOn);
		this.expand.set(Relay.Value.kOff);
	}	

	public void Contract()
	{
		this.expand.set(Relay.Value.kOff);
		this.expand.set(Relay.Value.kOn);
	}
	
	public boolean isExtended()
	{
		return	this.expand.get().equals(Relay.Value.kOn);
	}
}

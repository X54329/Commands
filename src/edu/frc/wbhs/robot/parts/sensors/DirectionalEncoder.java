/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frc.wbhs.robot.parts.sensors;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
/**
 *
 * @author Josh
 */
public class  DirectionalEncoder
{
	Encoder encoder;
	
	public DirectionalEncoder(int Apin, int Bpin, double wheelDiameter)
	{
		encoder = new Encoder(Apin, Bpin);
		double wheelCircumfrace = wheelDiameter*3.14159;
		encoder.setDistancePerPulse(wheelCircumfrace);
		encoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
		encoder.start();
		
	}
	
	public double getDistance()
	{
		return encoder.getDistance();
	}
	
	public double getSpeed()
	{
		return encoder.pidGet();
	}
	
	public boolean getDirection()
	{
		return encoder.getDirection();
	}
	
	public void stopEncoder()
	{
		encoder.stop();
	}
	public void resetCounter()
	{
		encoder.reset();
	}
	public void end()
	{
		encoder.free();
	}
	
}
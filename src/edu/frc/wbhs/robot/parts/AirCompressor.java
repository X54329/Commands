/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frc.wbhs.robot.parts;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Jo
 */
public class AirCompressor
{
	Compressor compressor;
	public AirCompressor(int spike,int pressureValve)
	{
		compressor = new Compressor(spike,pressureValve);
		compressor.start();	
	}
	
	public void shutoff()
	{
		compressor.stop();
	}
	
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.pid;
import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.
/**
 *
 * @author Brendan
 */
public class PIDWrapper{
	PIDController pid;
	
	PIDWrapper (double setpoint, double source)
	{
		pid.enable();
		pid.setSetpoint(setpoint);
		pid.setContinuous(false);
	}
}

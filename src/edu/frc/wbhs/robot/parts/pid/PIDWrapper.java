/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.pid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
//import edu.wpi.first.wpilibj.
/**
 *
 * @author Brendan
 */
public class PIDWrapper extends PIDController {

	public PIDWrapper(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOut output, double period) {
		super(Kp, Ki, Kd, Kf, source, output, period);
	}
	
	
}

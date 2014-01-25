/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts;

import edu.frc.wbhs.robot.parts.chassis.Catapult;
import edu.frc.wbhs.robot.parts.pid.PIDOut;
import edu.frc.wbhs.robot.parts.pid.PIDSauce;
import edu.frc.wbhs.robot.parts.pid.PIDWrapper;
import edu.frc.wbhs.robot.parts.sensors.USDWrapper;
import edu.wpi.first.wpilibj.templates.RobotTemplate;

/**
 *
 * @author Brendan
 */
public class Shooter {

	private PIDOut udsPIDOut;
	private PIDSauce udsPIDSauce;
	private PIDWrapper udsPID;
	private USDWrapper USD;

	private Catapult catapult;
	private boolean readyToFire;
	
	public Shooter() {
		USD = new USDWrapper(RobotTemplate.USD_PIN_IN, RobotTemplate.USD_PIN_OUT);
		udsPIDOut = new PIDOut();
		udsPIDSauce = new PIDSauce(1);
		udsPID = new PIDWrapper(RobotTemplate.USD_PID_P, RobotTemplate.USD_PID_I, RobotTemplate.USD_PID_D, RobotTemplate.USD_PID_F, udsPIDSauce, udsPIDOut, 0.1);

	}

	public double shoot() {
		// Calculate PID
		udsPIDSauce.setSauceVal(USD.getDistanceInches());
		// System.out.println(RobotTemplate.SHOOTING_DISTANCE - USD.getDistanceInches());
		udsPID.setSetpoint(RobotTemplate.SHOOTING_DISTANCE);
		udsPID.enable();
		double udsPIDchange = udsPIDOut.getOutput();
		// System.out.print(udsPIDchange + "\r");
		
		if (RobotTemplate.SHOOTING_DISTANCE - USD.getDistanceInches() < RobotTemplate.TARGET_ZONE_SIZE) {
			
		}
		
		return udsPIDchange;
	}

}

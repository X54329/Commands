/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.chassis;

import edu.frc.wbhs.robot.parts.Motor;
import edu.frc.wbhs.robot.parts.pid.PIDOut;
import edu.frc.wbhs.robot.parts.pid.PIDSauce;
import edu.frc.wbhs.robot.parts.pid.PIDWrapper;
import edu.frc.wbhs.robot.parts.sensors.DigitalInputWrapper;
import edu.frc.wbhs.robot.parts.sensors.PotWrapper;
import edu.wpi.first.wpilibj.templates.RobotTemplate;

/**
 *
 * @author Brian
 */
public class PickupArms {

	public Motor motor1;
	public Motor motor2;
	private PotWrapper pot;
	private PIDOut potPIDOut;
	private PIDSauce potPIDSauce;
	private PIDWrapper potPID;
	private DigitalInputWrapper ballSwitch;

	public PickupArms(int motorPinId, int rotorMotorPinId, int potPinId, int switchPinId) {
		motor1 = new Motor(RobotTemplate.PICKUP_ARM_MOTOR);
		pot = new PotWrapper(RobotTemplate.PICKUP_POTENTIOMETER_PIN);
		motor2 = new Motor(RobotTemplate.PICKUP_ARM_ROTOR_MOTOR);
		//ballSwitch = new DigitalInputWrapper(RobotTemplate.BALL_SWITCH_PIN);

		potPIDOut = new PIDOut();
		potPIDSauce = new PIDSauce(0);
		potPID = new PIDWrapper(RobotTemplate.ARM_PID_P, RobotTemplate.ARM_PID_I, RobotTemplate.ARM_PID_D, RobotTemplate.ARM_PID_F, potPIDSauce, potPIDOut, 0.05);
		potPID.setOutputRange(-0.75, 0.75);
	}

	public boolean moveArmsDown() {
		// TODO: use a pid
		double potPidChange = 0;
		potPIDSauce.setSauceVal(pot.getVoltage());
		potPID.setSetpoint(RobotTemplate.POT_ARMS_DOWN_VOLT);
		potPidChange = potPIDOut.getOutput();
		
		//System.out.println("Moving Arm down: " + pot.getVoltage());
		
		
		if (pot.getVoltage() > RobotTemplate.POT_ARMS_MAX_SAFE || pot.getVoltage() < RobotTemplate.POT_ARMS_MIN_SAFE) {
			System.out.println("Something is wrong with the Arms potentiometer! Fix it!");
		} else {
			motor1.setPower(potPidChange);
		}

		if (pot.getVoltage() > RobotTemplate.POT_ARMS_DOWN_VOLT + 0.05 && pot.getVoltage() < RobotTemplate.POT_ARMS_DOWN_VOLT - 0.05) {
			return true;
		} else {
			return false;
		}
	}

	public void moveRollers(double power) {
		motor2.setPower(power);
	}

	public boolean moveArmsUp() {
		double potPidChange = 0;
		potPIDSauce.setSauceVal(pot.getVoltage());
		potPID.setSetpoint(RobotTemplate.POT_ARMS_UP_VOLT);
		potPidChange = potPIDOut.getOutput();
		//System.out.println("Moving Arm up" + pot.getVoltage());

		if (pot.getVoltage() > RobotTemplate.POT_ARMS_MAX_SAFE || pot.getVoltage() < RobotTemplate.POT_ARMS_MIN_SAFE) {
			System.out.println("Something is wrong with the Arms potentiometer! Fix it!");
		} else {
			System.out.println(potPidChange);
			motor1.setPower(potPidChange);
		}

		if (pot.getVoltage() > RobotTemplate.POT_ARMS_UP_VOLT + 0.05 && pot.getVoltage() < RobotTemplate.POT_ARMS_UP_VOLT - 0.05) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBallInPlace() {
		return true;
	}

	public double getPotVal() {
		return pot.getVoltage();
	}
}

package edu.frc.wbhs.robot;

import edu.frc.wbhs.robot.parts.chassis.Chassis;
import edu.frc.wbhs.robot.parts.pid.PIDOut;
import edu.frc.wbhs.robot.parts.pid.PIDSauce;
import edu.frc.wbhs.robot.parts.pid.PIDWrapper;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.templates.RobotTemplate;

/**
 *
 * @author Brendan
 */
/* TODO: add all the other parts that we need and set the joystick up for them
 make sure we're only sending the requested power down through a method
 so we can change it without messing with this class */
public class Robot {

	public Chassis chassis;

	public Robot(Chassis chassis) {
		System.out.print("setting up robot...");
		this.chassis = chassis;
		System.out.println("done");
	}

	public void drive(Joystick j, int mode) { // mode: 0 = arcade, 1 = tank

		// pull axes from the joystick	
		double xAxis = j.getRawAxis(RobotTemplate.X_AXIS_CHANNEL);
		double yAxis = j.getRawAxis(RobotTemplate.Y_AXIS_CHANNEL);
		double zAxis = j.getRawAxis(RobotTemplate.Z_AXIS_CHANNEL);
		chassis.drive(xAxis, yAxis, mode);
	}
	
	public void shoot()
	{

	}
	
}

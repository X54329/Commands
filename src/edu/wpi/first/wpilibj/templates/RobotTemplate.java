/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.frc.wbhs.dashboard.Dashboard;
import edu.frc.wbhs.robot.Robot;
import edu.frc.wbhs.robot.parts.chassis.Chassis;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotTemplate extends IterativeRobot {

	public static double PARABOLA_VELOCITY_CONSTANT = 0;
	public static int JOYSTICK = 1; // which joystick we are using, usually 1
	public static int X_AXIS_CHANNEL = 1; // which channel the x axis is on the joystick
	public static int Y_AXIS_CHANNEL = 2;
	public static int Z_AXIS_CHANNEL = 3;
	public static int[] RIGHT_SIDE_PINS = new int[]{1}; //which digital output the right side of the drive motors are
	public static int[] LEFT_SIDE_PINS = new int[]{5};
	public static int GYRO_PIN = 1; // analog input
	public static int ACCELEROMETER_PIN = 2; // analog input
	public static double ROBOT_MAX_ANGULAR_SPEED = 250; //in degrees per second
	public static double GYRO_PID_P = 0;
	public static double GYRO_PID_I = 0;
	public static double GYRO_PID_D = 0;
	public static double GYRO_PID_F = 0;
	public static double GYRO_PID_MULTIPLIER = 0.9;
	public static int POTID = 3;
	public static double MOTOR_TO_VELOCITY_PROPORTION = 0;
	public static double G = 9.8;
	public static double THETA = 0; // Angle of shooting
	public static double USD_PID_P = 0.05;
	public static double USD_PID_I = 0;
	public static double USD_PID_D = 0;
	public static double USD_PID_F = 0;
	public static int USD_PIN_IN = 7;
	public static int USD_PIN_OUT = 8;
	public static final double SHOOTING_DISTANCE = 15;
	/***************************************************************
	*                                                              *
	* All fields below this are NOT implemented into the dashboard *
	*                                                              *
	****************************************************************/
	public static final int SPIKE_PIN = 8;
	public static double WHEEL_DIAMETER = 4; //in inches
	public static double LEFT_SIDE_MULTIPLIER = -1;
	public static double RIGHT_SIDE_MULTIPLIER = 1;


	public Robot robot;
	public Chassis chassis;
	public Joystick joystick;
	public NetworkTable Output;
	public SmartDashboard dashboard;

	public void robotInit() {
		//NetworkTable Output =  new NetworkTable("Output", new NetworkTableProvider(new NetworkTableNode()));
		//output
		chassis = new Chassis(RIGHT_SIDE_PINS, LEFT_SIDE_PINS, GYRO_PIN, ACCELEROMETER_PIN, POTID, SPIKE_PIN); //set up the chassis
		robot = new Robot(chassis); //feed it to the robot
		joystick = new Joystick(JOYSTICK);
		dashboard = new SmartDashboard();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		robot.drive(joystick, 0); // 0 = arcade, 1 = tank
		//USD_PID_P = Output.getNumber("P", 0.5);
		//USD_PID_I = Output.getNumber("I", 0);
		//USD_PID_D = Output.getNumber("D", 0);
		
		Dashboard.getNumbers();
		Dashboard.putNumbers();
		//robot.shoot();
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}

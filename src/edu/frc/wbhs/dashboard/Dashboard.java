/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.dashboard;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotTemplate;

/**
 *
 * @author Brendan
 */
public class Dashboard {

	public static void putNumbers() {
		SmartDashboard.putNumber("PARABOLA_VELOCITY_CONSTANT", RobotTemplate.PARABOLA_VELOCITY_CONSTANT);
		SmartDashboard.putNumber("JOYSTICK", RobotTemplate.JOYSTICK);
		SmartDashboard.putNumber("X_AXIS_CHANNEL", RobotTemplate.X_AXIS_CHANNEL);
		SmartDashboard.putNumber("Y_AXIS_CHANNEL", RobotTemplate.Y_AXIS_CHANNEL);
		SmartDashboard.putNumber("Z_AXIS_CHANNEL", RobotTemplate.Z_AXIS_CHANNEL);
		SmartDashboard.putNumber("RIGHT_SIDE_PINS", RobotTemplate.RIGHT_SIDE_PINS[0]);
		SmartDashboard.putNumber("LEFT_SIDE_PINS", RobotTemplate.LEFT_SIDE_PINS[0]);
		SmartDashboard.putNumber("GYRO_PIN", RobotTemplate.GYRO_PIN);
		SmartDashboard.putNumber("ACCELEROMETER_PIN", RobotTemplate.ACCELEROMETER_PIN);
		SmartDashboard.putNumber("ROBOT_MAX_ANGULAR_SPEED", RobotTemplate.ROBOT_MAX_ANGULAR_SPEED);
		SmartDashboard.putNumber("GYRO_PID_P", RobotTemplate.GYRO_PID_P);
		SmartDashboard.putNumber("GYRO_PID_I", RobotTemplate.GYRO_PID_I);
		SmartDashboard.putNumber("GYRO_PID_D", RobotTemplate.GYRO_PID_D);
		SmartDashboard.putNumber("GYRO_PID_F", RobotTemplate.GYRO_PID_F);
		SmartDashboard.putNumber("GYRO_PID_MULTIPLIER", RobotTemplate.GYRO_PID_MULTIPLIER);
		SmartDashboard.putNumber("POTID", RobotTemplate.POT_ID);
		SmartDashboard.putNumber("MOTOR_TO_VELOCITY_PROPORTION", RobotTemplate.MOTOR_TO_VELOCITY_PROPORTION);
		SmartDashboard.putNumber("G", RobotTemplate.G);
		SmartDashboard.putNumber("THETA", RobotTemplate.THETA);
		SmartDashboard.putNumber("USD_PID_P", RobotTemplate.USD_PID_P);
		SmartDashboard.putNumber("USD_PID_I", RobotTemplate.USD_PID_I);
		SmartDashboard.putNumber("USD_PID_D", RobotTemplate.USD_PID_D);
		SmartDashboard.putNumber("USD_PID_F", RobotTemplate.USD_PID_F);
		SmartDashboard.putNumber("USD_PIN_IN", RobotTemplate.USD_PIN_IN[0]);
		SmartDashboard.putNumber("SHOOTING_DISTANCE", RobotTemplate.SHOOTING_DISTANCE);

	}

	public static void getNumbers() {
		SmartDashboard.getNumber("PARABOLA_VELOCITY_CONSTANT", RobotTemplate.PARABOLA_VELOCITY_CONSTANT);
		SmartDashboard.getNumber("JOYSTICK", RobotTemplate.JOYSTICK);
		SmartDashboard.getNumber("X_AXIS_CHANNEL", RobotTemplate.X_AXIS_CHANNEL);
		SmartDashboard.getNumber("Y_AXIS_CHANNEL", RobotTemplate.Y_AXIS_CHANNEL);
		SmartDashboard.getNumber("Z_AXIS_CHANNEL", RobotTemplate.Z_AXIS_CHANNEL);
		SmartDashboard.getNumber("RIGHT_SIDE_PINS", RobotTemplate.RIGHT_SIDE_PINS[0]);
		SmartDashboard.getNumber("LEFT_SIDE_PINS", RobotTemplate.LEFT_SIDE_PINS[0]);
		SmartDashboard.getNumber("GYRO_PIN", RobotTemplate.GYRO_PIN);
		SmartDashboard.getNumber("ACCELEROMETER_PIN", RobotTemplate.ACCELEROMETER_PIN);
		SmartDashboard.getNumber("ROBOT_MAX_ANGULAR_SPEED", RobotTemplate.ROBOT_MAX_ANGULAR_SPEED);
		SmartDashboard.getNumber("GYRO_PID_P", RobotTemplate.GYRO_PID_P);
		SmartDashboard.getNumber("GYRO_PID_I", RobotTemplate.GYRO_PID_I);
		SmartDashboard.getNumber("GYRO_PID_D", RobotTemplate.GYRO_PID_D);
		SmartDashboard.getNumber("GYRO_PID_F", RobotTemplate.GYRO_PID_F);
		SmartDashboard.getNumber("GYRO_PID_MULTIPLIER", RobotTemplate.GYRO_PID_MULTIPLIER);
		SmartDashboard.getNumber("POTID", RobotTemplate.POT_ID);
		SmartDashboard.getNumber("MOTOR_TO_VELOCITY_PROPORTION", RobotTemplate.MOTOR_TO_VELOCITY_PROPORTION);
		SmartDashboard.getNumber("G", RobotTemplate.G);
		SmartDashboard.getNumber("THETA", RobotTemplate.THETA);
		SmartDashboard.getNumber("USD_PID_P", RobotTemplate.USD_PID_P);
		SmartDashboard.getNumber("USD_PID_I", RobotTemplate.USD_PID_I);
		SmartDashboard.getNumber("USD_PID_D", RobotTemplate.USD_PID_D);
		SmartDashboard.getNumber("USD_PID_F", RobotTemplate.USD_PID_F);
		SmartDashboard.getNumber("USD_PIN_IN", RobotTemplate.USD_PIN_IN[0]);
		SmartDashboard.getNumber("SHOOTING_DISTANCE", RobotTemplate.SHOOTING_DISTANCE);

	}
}

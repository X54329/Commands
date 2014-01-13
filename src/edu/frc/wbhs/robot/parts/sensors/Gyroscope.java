package edu.frc.wbhs.robot.parts.sensors;
import edu.wpi.first.wpilibj.Gyro;

/**
 * Wrapper for gyroscopes
 * @author Brendan
 */
public class Gyroscope {
	Gyroscope(int inputPin) {
		Gyro g = new Gyro(inputPin);
		
	}
}

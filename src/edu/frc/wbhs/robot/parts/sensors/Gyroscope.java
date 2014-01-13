package edu.frc.wbhs.robot.parts.sensors;

import edu.wpi.first.wpilibj.Gyro;

/**
 * Wrapper for gyroscopes
 *
 * @author Brendan, Mostly Brian
 */
public class Gyroscope {

    private Gyro realgyro;

    public Gyroscope(int inputPin) {
	realgyro = new Gyro(inputPin);
    }

    public double getAngle() {
	return realgyro.getAngle();
    }
    
    public double getRate() {
	return realgyro.getRate();
    }

}

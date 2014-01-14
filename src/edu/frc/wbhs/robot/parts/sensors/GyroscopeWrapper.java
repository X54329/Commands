package edu.frc.wbhs.robot.parts.sensors;

import edu.wpi.first.wpilibj.Gyro;

/**
 * Wrapper for gyroscopes
 *
 * @author Brendan, Brian
 */
public class GyroscopeWrapper {

    private Gyro realgyro;

    public GyroscopeWrapper(int inputPin) {
        realgyro = new Gyro(inputPin);
    }

    public double getAngle() {
        return realgyro.getAngle();
    }

    public double getRate() {
        return realgyro.getRate();
    }

    public void reset() {
        realgyro.reset();
    }

    public void setSensitivity(double voltsPerDegreePerSecond) {
        realgyro.setSensitivity(voltsPerDegreePerSecond);
    }

}

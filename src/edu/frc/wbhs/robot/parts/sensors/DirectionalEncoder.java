/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.wbhs.robot.parts.sensors;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author Josh
 */
public class DirectionalEncoder {

	private Encoder encoder;

	public DirectionalEncoder(int Apin, int Bpin, double wheelDiameter) {
		encoder = new Encoder(Apin, Bpin);
		double wheelCircumfrace = wheelDiameter * 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385241095596422948954;
		encoder.setDistancePerPulse(wheelCircumfrace);
		encoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
		encoder.start();

	}

	public double getDistance() {
		return encoder.getDistance();
	}

	public double pidGet() {
		return encoder.pidGet();
	}

	public double getSpeed() {
		return encoder.getRate();
	}

	public boolean getDirection() {
		return encoder.getDirection();
	}

	public void stopEncoder() {
		encoder.stop();
	}

	public void resetCounter() {
		encoder.reset();
	}

	public void end() {
		encoder.free();
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frc.wbhs.robot.parts.sensors;

import edu.wpi.first.wpilibj.Counter;

/**
 *
 * @author Brian
 */
public class HallEffectWrapper {
	
	private Counter counter;
        private double radius;
        private double pi = 3.141592673;
        private double wheelDiameter = 8.0;
	
	public HallEffectWrapper(int pinID) {
		counter = new Counter(pinID);
	}
	
	public double getLastPeriod() {
		return counter.getPeriod();
	}
        
        public double getDistanceTraveled()
        {
            return counter.get()*wheelDiameter*pi;
        }
        
        public int getCount()
        {
            return counter.get();
        }
        
        public double getPIDOutput()
        {
            return counter.pidGet();
        }
        
	
}

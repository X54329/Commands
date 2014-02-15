/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frc.robot.measure;

import java.util.Timer;
import java.util.TimerTask;
import edu.frc.wbhs.robot.Robot;

/**
 *
 * @author brendan
 */
public class PotStorer {

	double[] values = new double[1200];
	int currentval = 0;
	Robot robot;

	public PotStorer(Robot r) {
		robot = r;
	}

	public void recordPot(double value) {
		values[currentval] = value;
		currentval++;
	}

	public void measurePot() {
		PotMeasurer pot1 = new PotMeasurer();
		PotMeasurer pot2 = new PotMeasurer();
		PotMeasurer pot3 = new PotMeasurer();
		PotMeasurer pot4 = new PotMeasurer();

		Timer timer = new Timer();

		timer.schedule(pot1, 1);
		timer.schedule(pot2, 6);
		timer.schedule(pot3, 11);
		timer.schedule(pot4, 16);
	}

	private class PotMeasurer extends TimerTask {

		public void run() {
			recordPot(robot.chassis.arms.getPotVal());
			System.out.println(robot.chassis.arms.getPotVal());
		}
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frc.wbhs.robot.auto;

/**
 *
 * This class was created because Brendan's timers seemed to suck
 * 
 * @author Brian
 * 
 */
public class MSTimer {
	
	public int timeinit;
	public int time;
	
	public MSTimer() {
		time = 0;
	}
	
	public void reset() {
		time = 0;
	}
	
	public void update() {
		time -= 20;
	}
	
	public void set(int timems) {
		timeinit = timems;
		time = timems;
	}
	
	public boolean elapsed() {
		return time <= 0;
	}
	
	public boolean isPast(int timems) {
		return (timeinit - time) <= timems;
	}
	
}

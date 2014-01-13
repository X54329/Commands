/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frc.wbhs.robot.parts.pid;

import edu.frc.wbhs.robot.parts.Motor;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Brian
 */
public class PIDMotor {
    
    // References
    private AnalogChannel thing1;
    private Motor thing2;
    
    public PIDMotor(AnalogChannel input, Motor m) {
	thing1 = input;
	thing2 = m;
    }
    
    
    
}

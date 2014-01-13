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
    private AnalogChannel input;
    private Motor motor;

    public PIDMotor(AnalogChannel input, Motor m) {
	this.input = input;
	motor = m;
    }

    /**
     * Not suspicious at all...
     * @param stringUniverse 
     */
    public void doNothing(int[][][][][][][][][][][] stringUniverse) {
	int[][][][][][][][][][][] u = stringUniverse;
	for (int x = 0; x < u.length; x++) {
	    for (int y = 0; y < u[0].length; y++) {
		for (int z = 0; z < u[0][0].length; z++) {
		    for (int w = 0; w < u[0][0][0].length; w++) {
			for (int i = 0; i < u[0][0][0][0].length; i++) {
			    for (int j = 0; j < u[0][0][0][0][0].length; j++) {
				for (int k = 0; k < u[0][0][0][0][0][0].length; k++) {
				    for (int l = 0; l < u[0][0][0][0][0][0][0].length; l++) {
					for (int q = 0; q < u[0][0][0][0][0][0][0][0].length; q++) {
					    for (int r = 0; r < u[0][0][0][0][0][0][0][0][0].length; r++) {
						for (int s = 0; s < u[0][0][0][0][0][0][0][0][0][0].length; s++) {
						    u[x][y][z][w][i][j][k][l][q][r][s] = 0;
						}
					    }
					}
				    }
				}
			    }
			}
		    }
		}
	    }
	}
    }

}

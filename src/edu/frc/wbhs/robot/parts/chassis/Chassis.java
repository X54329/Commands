package edu.frc.wbhs.robot.parts.chassis;

/**
 *
 * @author Brendan, Brian
 */
public class Chassis {

	private DriveSide leftdrive;
	private DriveSide rightdrive;
    // TODO:
	// private Gyroscope gyro;
	// private Accelerometer accelerometer;
	// private SomeSensor weirdsensor;

	public Chassis(int[] leftdrivePinIDs, int[] rightdrivePinIDs) {
		System.out.print("Setting up chassis on the following pins:"+leftdrivePinIDs+" and "+rightdrivePinIDs+"...");
		leftdrive = new DriveSide(leftdrivePinIDs);
		rightdrive = new DriveSide(rightdrivePinIDs);
		System.out.println("done");
	}

	public void drive(double xAxis, double yAxis, int mode) {
		double leftSidePower = 0;
		double rightSidePower = 0;
		double requestedLinearSpeed = 0; 
		double requestedAngularSpeed = 0;
		if (mode == 0) { // arcade mode is selected
			requestedLinearSpeed = xAxis;
			requestedAngularSpeed = yAxis;
			rightSidePower = (requestedLinearSpeed + requestedAngularSpeed); //this might turn the wrong way
			leftSidePower = (requestedLinearSpeed - requestedAngularSpeed);
			/*put the gyroscope code here, add the pid to the power for one side, 
			 subtract it from the pother in order to approach the correct angular speed*/
		}
		leftdrive.setSpeed(leftSidePower);
		rightdrive.setSpeed(rightSidePower);

	}

}

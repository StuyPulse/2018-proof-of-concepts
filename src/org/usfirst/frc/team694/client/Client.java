package org.usfirst.frc.team694.client;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Client {

	public static void main(String[] args) {
		NetworkTableInstance instance = NetworkTableInstance.getDefault();
		NetworkTable table = instance.getTable("");
		
		
		while(true) {
			System.out.println("x: " + table.getEntry("x").getNumber(0));

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

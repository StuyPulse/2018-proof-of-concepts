package org.usfirst.frc.team694.client;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Client {

	public static void main(String[] args) {
		NetworkTableInstance instance = NetworkTableInstance.getDefault();
		NetworkTable table = instance.getTable("");
		instance.startClientTeam(694);
		instance.startDSClient();

		System.out.println("Started NetworkTables Client!");

		NetworkTableEntry xEntry = table.getEntry("x");

		while (true) {
			System.out.println("x: " + xEntry.getDouble(0));

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

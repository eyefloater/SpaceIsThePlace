package com.gaby.space;

import java.sql.Connection;
import java.sql.ResultSet;

public class Commands {
	private static Connection connection = Game.getConnection();
	this.connection = connection;


	public static void examineRoom() {
		try {
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// get room description from a ResultSet
			ResultSet res = statement
					.executeQuery("SELECT DESCRIPTION FROM ROOMS WHERE ID = "
							+ Character.location);

			while (res.next()) {
				System.out.println(res.getString("DESCRIPTION"));
			}
			res.close();
			statement.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

	}


	public static void takeObject(String command) {
		String objectToTake = command.substring(5);
		try {
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// get room description from a ResultSet
			ResultSet res = statement.executeQuery("SELECT OBJECT FROM OBJECTS WHERE NAME ="+objectToTake);
			
		
			while (res.next()) {
				//changes location to inventory (will be room 1)
				res.updateInt(2, 1);	
				System.out.println("Goo takes the "+res.getString("objectToTake"));
			}
			res.close();
			statement.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		
	}

	public static void printInventory() {
		try {
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// get room description from a ResultSet
			int inv = 1;
			ResultSet res = statement.executeQuery("SELECT OBJECT FROM OBJECTS WHERE LOCATION ="+inv);
		
			while (res.next()) {
			
				System.out.println(res);
			}
			res.close();
			statement.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		
	}

	public static void listCommands() {
		System.out.println("List of actions:");
		System.out.println("---------------");
		System.out.println("look - to examine surroundings");
		System.out.println("look at ___ - to examine people and objects");
		System.out.println("take object");
		System.out.println("drop object");
		System.out.println("N, E, S, W - to move");
		System.out.println("Play ___ - to play an instrument");
		System.out.println("Eat ___, Drink ___, - be reasonable, ok?");
		System.out.println("Talk to ___ - talk to someone");
		System.out.println("i - view inventory");
		System.out.println("help - view this list");

	}

	public static void examineObject(String command) {
		String objectToLookAt = command.substring(7);
		try {
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// get room description from a ResultSet
			ResultSet res = statement.executeQuery("SELECT OBJECT FROM OBJECTS WHERE NAME ="+objectToLookAt);
			
		
			while (res.next()) {
				//prints object description	
				System.out.println(res.getString(4));
			}
			res.close();
			statement.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}


	public static void move(String command) {
		//moves in given direction if possible
		
	}

}

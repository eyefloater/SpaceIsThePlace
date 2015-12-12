package com.gaby.space;

import java.sql.Connection;
import java.sql.ResultSet;

public class Commands {
	private static Connection connection = Game.getConnection();

	// this.connection = connection;

	// command is look. gets description of room. usable ojbects should be in
	// bold/diff color?
	public static void examineRoom() {
		try {
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// get room description from a ResultSet
			ResultSet res = statement
					.executeQuery("SELECT DESCRIPTION FROM ROOMS WHERE ID = "
							+ Character.getLocation());

			while (res.next()) {
				System.out.println("\n" + res.getString("DESCRIPTION"));
			}
			res.close();
			statement.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

	}

	// assumes entry like "take rock", and strips first 5 characters, hopefully
	// leaving the name of the object as it is in the database.
	public static void takeObject(String command) {
		String objectToTake = command.substring(5);
		try {
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// get room description from a ResultSet
			ResultSet res = statement
					.executeQuery("SELECT OBJECT FROM OBJECTS WHERE NAME ="
							+ objectToTake);

			while (res.next()) {
				// changes location to inventory (will be room 1)
				res.updateInt(2, 1);
				System.out.println("Goo takes the "
						+ res.getString("objectToTake"));
			}
			res.close();
			statement.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

	}

	// inventory is room 1, so prints every object in room 1
	public static void printInventory() {
		try {
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// get room description from a ResultSet
			int inv = 1;
			ResultSet res = statement
					.executeQuery("SELECT OBJECT FROM OBJECTS WHERE LOCATION = 1");

			while (res.next()) {

				System.out.println(res);
			}
			res.close();
			statement.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

	}

	// command is "help", lists all commands
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
		// removes 'look at ' from command, leaving name of object
		String objectToLookAt = command.substring(7);
		try {
			java.sql.Statement statement = connection.createStatement();

			// get object description from a ResultSet
			ResultSet res = statement
					.executeQuery("SELECT DESCRIPTION FROM OBJECTS WHERE NAME ="
							+ objectToLookAt);

			while (res.next()) {
				// prints object description
				System.out.println(res.getString(4));
			}
			res.close();
			statement.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}

	public static void move(String command) {
		try {
			java.sql.Statement statement = connection.createStatement();

			//if (command == "n") {
				//if (Character.getLocation() == 2) {
					Character.setLocation(3);
					statement.close();
					examineRoom();
				//} else {
					//System.out.println("You can't go that way");
				//}
			//}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());

		}
	}
	
	public static void talk(String command){
		//dialog trees maybe stored in own class?
	}
	
	

}

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

	public static void examineObject(String command) {
		// removes 'look at ' from command, leaving name of object
		String objectToLookAt = command.substring(8);
		try {
			java.sql.Statement statement = connection.createStatement();
			String query = "SELECT * FROM OBJECTS WHERE NAME ='"
					+ objectToLookAt + "'";
			// get object description from a ResultSet
			ResultSet res = statement.executeQuery(query);
			boolean first = res.next();
			if (first == true) {
				// res.getString("DESCRIPTION");
				System.out.println(res.getString(4));
			}
			// System.out.println(res);

			res.close();
			statement.close();
		} catch (Exception e) {
			// System.err.println("Exception: " + e.getMessage());
			System.out.println("There is no " + objectToLookAt + " here.");
		}
	}

	// assumes entry like "take rock", and strips first 5 characters, hopefully
	// leaving the name of the object as it is in the database.
	public static void takeObject(String command) {
		int location = Character.getLocation();
		String objectToTake = command.substring(5);

		if (objectToTake.equals("id")) {
			Character.setHasID();
		}
		if (objectToTake.equals("rover")) {
			Character.setHasRover(true);
		}

		if (objectToTake.equals("leather jacket")) {
			Character.setHasLeatherJacket();
		}
		if (objectToTake.equals("lanyard")) {
			Character.setHasLanyard();
		}

		String name = "";
		try {
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// get room description from a ResultSet
			ResultSet res = statement
					.executeQuery("SELECT * FROM OBJECTS WHERE (NAME ='"
							+ objectToTake + "' AND LOCATION =" + location
							+ ")");
			boolean first = res.next();
			name = res.getString(3);
			if (first == true) {

				// changes location to inventory (will be room 1)
				// res.updateInt(2, 1);
				statement = connection.createStatement();
				int result = statement
						.executeUpdate("UPDATE OBJECTS SET LOCATION = 1 WHERE NAME = '"
								+ objectToTake + "'");
				System.out.println("GOO takes the " + name);
				connection.commit();

				res.close();
				statement.close();
			}
		} catch (Exception e) {
			// System.err.println("Exception: " + e.getMessage());

			System.out.println("There is no " + objectToTake + " here.");
		}

	}
	
	public static void dropObject(String command) {
		int location = Character.getLocation();
		String objectToDrop = command.substring(5);

		/*if (objectToTake.equals("id")) {
			Character.setHasID();
		}
		if (objectToTake.equals("leather jacket")) {
			Character.setHasLeatherJacket();
		}
		if (objectToTake.equals("lanyard")) {
			Character.setHasLanyard();
		}*/

		String name = "";
		try {
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// get object from a ResultSet
			ResultSet res = statement
					.executeQuery("SELECT * FROM OBJECTS WHERE (NAME = '"
							+ objectToDrop + "' AND LOCATION = 1)");
			boolean first = res.next();
			name = res.getString(3);
			if (first == true) {

				statement = connection.createStatement();
				int result = statement
						.executeUpdate("UPDATE OBJECTS SET LOCATION = "+location+" WHERE NAME = '"
								+ objectToDrop + "'");
				System.out.println("GOO drops the " + name);
				connection.commit();

				res.close();
				statement.close();
			}
		} catch (Exception e) {
			// System.err.println("Exception: " + e.getMessage());

			System.out.println("You don't have a " + objectToDrop);
		}

	}

	public static void moveSunRaToLunarPlain(){
		int location = Character.getLocation();
		String sunra = "sun ra";


		String name = "";
		try {
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// get object from a ResultSet
			ResultSet res = statement
					.executeQuery("SELECT * FROM OBJECTS WHERE NAME = '"+ sunra+"'");
			boolean first = res.next();
			name = res.getString(3);
			if (first == true) {

				statement = connection.createStatement();
				int result = statement
						.executeUpdate("UPDATE OBJECTS SET LOCATION = "+2+" WHERE NAME = '"
								+ sunra + "'");
				System.out.println("Sun Ra is outta here");
				connection.commit();

				res.close();
				statement.close();
			}
		} catch (Exception e) {
			// System.err.println("Exception: " + e.getMessage());

			System.out.println("You don't have a " + sunra);
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
					.executeQuery("SELECT * FROM OBJECTS WHERE LOCATION = 1");

			while (res.next()) {
				String name = res.getString(3);
				System.out.println(name);
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
		System.out.println("drop object");
		System.out.println("N, E, S, W - to move");
		System.out.println("Play ___ - to play an instrument");
		System.out.println("Eat ___, Drink ___, - be reasonable, ok?");
		System.out.println("Talk to ___ - talk to someone");
		System.out.println("i - view inventory");
		System.out.println("help - view this list");

	}

	public static void removeSuit() {
		Character.setHasSuitOn(false);
		System.out
				.println("GOO removes his spacesuit. Feels good. A little dip in the ocean would be perfect right now. Well, there's no ocean. :<");
	}

	public static void move(String command) {
		Character.decrementHope(1);
		int location = Character.getLocation();
		switch (location) {
		case 1: // inventory. not applicable
			break;
		case 2: // lunar surface south
			if (command.equals("n")) {
				Character.setLocation(3);
				examineRoom();
				break;
			} else {
				System.out.println("GOO doesn't want to go that way.");
				break;

			}

		case 3: // lunar surface
			if (command.equals("n")) {
				Character.setLocation(4);
				examineRoom();
				break;
			}
			if (command.equals("s")) {
				Character.setLocation(2);
				if (Character.hasSuitOn() == false) {
					System.out
							.println("You suffocated before you could freeze to death, GOO. Maybe a rover will find you someday. What a pity.");
					Character.setIsAlive(false);
				}

				examineRoom();
				break;
			} else {
				System.out.println("GOO doesn't want to go that way.");
				break;
			}

		case 4: // boardwalk
			if (command.equals("n")) {
				Character.setLocation(5);
				examineRoom();
				break;
			}
			if (command.equals("s")) {
				Character.setLocation(3);
				if (Character.hasSuitOn() == false) {
					System.out
							.println("Well you're walkin' on the moon without your spacesuit on, GOO. Keep on walkin', if that's what you really want.");
				}
				examineRoom();
				break;
			}
			if (command.equals("w")) {
				if (Character.hasID()) {
					Dialog.charlie();
					Character.setLocation(12);
					examineRoom();
					break;
				} else {
					System.out
							.println("Charlie steps forward and drawls, 'Sorry old friend, you need your Medtronic ID badge or I can't let you onto company property.'");
					break;
				}
			}
			if (command.equals("e")) {
				Character.setLocation(7);
				examineRoom();
				break;
			} else {
				System.out.println("GOO doesn't want to go that way.");
				break;
			}

		case 5: // space bar
			if (command.equals("n")) {
				Character.setLocation(6);
				examineRoom();
				break;
			}
			if (command.equals("s")) {
				Character.setLocation(4);
				examineRoom();
				break;
			} else {
				System.out
						.println("The Man built walls to hold GOO back, and they're pretty solid.");
				break;

			}

		case 6: // back room
			if (command.equals("s")) {
				Character.setLocation(5);
				examineRoom();
				break;
			} else {
				System.out
						.println("There's only one way outta here, and that's south.");
				break;
			}

		case 7: // boardwalk east
			if (command.equals("n")) {
				if (Character.hasLeatherJacket()) {
					Character.setLocation(8);
					examineRoom();
					break;
				} else {
					System.out
							.println("The doorman sniffs at your miner's clothing. \"Sorry pal, you're not getting in looking like that. This club is for cool, neato rockers. No miners allowed.\"");
					break;
				}
			}
			if (command.equals("w")) {
				Character.setLocation(4);
				examineRoom();
				break;
			}
			if (command.equals("e")) {
				Character.setLocation(10);
				examineRoom();
				break;
			} else {
				System.out.println("There's a plastic dome wall in the way.");
				break;
			}

		case 8: // jupiter Club
			if (command.equals("n")) {
				Character.setLocation(9);
				examineRoom();
				break;
			}
			if (command.equals("s")) {
				Character.setLocation(7);
				examineRoom();
				break;
			} else {
				System.out
						.println("The Man built walls to hold GOO back, and they're pretty solid.");
				break;
			}

		case 9: // rehearsal space
			if (command.equals("s")) {
				Character.setLocation(8);
				examineRoom();
				break;
			} else {
				System.out
						.println("There's only one way outta here, and that's south.");
				break;

			}

		case 10: // guitar center
			if (command.equals("w")) {
				Character.setLocation(8);
				examineRoom();
				break;
			}
			if (command.equals("e")) {
				Character.setLocation(11);
				examineRoom();
				break;
			} else {
				System.out.println("Nope");
				break;

			}

		case 11: // alley
			if (command.equals("w")) {
				Character.setLocation(10);
				examineRoom();
				break;
			} else {
				System.out
						.println("Maybe on Earth the back alleys lead somewhere. Here, you can only go W, back into Guitar Center");
				break;

			}

		case 12: // Mining HQ
			if (command.equals("n")) {
				Character.setLocation(13);
				examineRoom();
				break;
			}
			if (command.equals("w")) {
				if (Character.hasLanyard() == true) {
					Character.setLocation(14);
					examineRoom();
					break;
				} else {
					System.out
							.println("GOO loves all spaceships. But this one is particularly rad. "
									+ "Very sleek cruise liner, in the classic featureless-silver-cube style of the 2240''s. Only passengers and crew can board, and GOO is currently neither.");
					break;
				}
			}
			if (command.equals("e")) {
				Character.setLocation(4);
				examineRoom();
				break;
			} else {
				System.out
						.println("Only a maze of conveyer belts to the south. GOO doesn't want to be crushed by moondust.");
				break;
			}

		case 13: // old refinery
			if (command.equals("s")) {
				Character.setLocation(12);
				examineRoom();
				break;
			} else {
				System.out
						.println("GOO runs through a seemingly endless maze of alleyways, some of them flickering with forgotten holograph lights, and returns to the mouth of the refinery.");
				break;
			}

		case 14: // spaceship
			if (command.equals("e")) {
				Character.setLocation(12);
				examineRoom();
				break;
			} else {
				System.out
						.println("You can only leave a spaceship through a door.");
				break;
			}
		default:
			break;
		}

	}

	public static void moveBox(){
		System.out
		.println("GOO slides the box to one side, noticing that it's painted a rich shade of gold. \nIt's actually not a box at all, but the entrance to some kind of temple structure, deep beneath the moon. \nAfter descending staircase upon collonaded staircase, GOO finally reaches the bottom, \nwhere in the center of a bejewelled chamber glowing red with a hundred floating lights, sits a man. \nIt is Sun Ra.");
	
	}
	public static void talk(String command) {
		String npc = command.substring(8);
		Dialog.talk(npc);
	}

}

package com.gaby.space;

import java.sql.Connection;
import java.sql.ResultSet;

public class Commands {

	//gets room description from database, prints to GUI
	public static void examineRoom() {
		GUI.clearOptions();
		try {
			Connection connection = Game.getConnection();
			java.sql.Statement statement = connection.createStatement();

			// get room description from a ResultSet
			ResultSet res = statement
					.executeQuery("SELECT DESCRIPTION FROM ROOMS WHERE ID = "
							+ Character.getLocation());
			
			//prints description to results pane
			while (res.next()) {
				GUI.showResults("\n" + res.getString("DESCRIPTION"));

			}
			res.close();
			statement.close();
		} catch (Exception e) {
			GUI.showResults("Exception: " + e.getMessage());
		}


	}
	//takes "take object" command, creates a substring with only the objects name, finds that in database, and prints that object's description to GUI
	//LIMITATION: currently allows player to 'look at' an object without being in the room with it
	public static void examineObject(String command) {
		// removes 'look at ' from command, leaving name of object
		GUI.clearOptions();
		String objectToLookAt = command.substring(8);
		try {
			Connection connection = Game.getConnection();
			java.sql.Statement statement = connection.createStatement();
			String query = "SELECT * FROM OBJECTS WHERE NAME ='"
					+ objectToLookAt + "'";
			// get object description from a ResultSet
			ResultSet res = statement.executeQuery(query);
			boolean first = res.next();
			if (first == true) {
				// res.getString("DESCRIPTION");
				GUI.addToOptions(res.getString(4));
			}
			// System.out.println(res);

			res.close();
			statement.close();
		} catch (Exception e) {
			// System.err.println("Exception: " + e.getMessage());
			GUI.addToOptions("There is no " + objectToLookAt + " here.");
		}
	}

	// takes "take object" command, strips it to the name of the object, moves object from its room to the inventory (which is Room 1)
	public static void takeObject(String command) {
		int location = Character.getLocation();
		String objectToTake = command.substring(5);

		//objects important to plot, when acquired, produce state changes. you need hasID to be true, for example, to get past security
		if (objectToTake.equals("id")) {
			Character.setHasID();
		}
		if (objectToTake.equals("rover")) {
			Character.setHasRover(true);
		}

		if (objectToTake.equals("leather jacket")) {
			Character.setHasLeatherJacket();
		}
		if (objectToTake.equals("lanyards")) {
			Character.setHasLanyard(true);
		}
		if (objectToTake.equals("credit cube")) {
			Character.setHasCreditCube(true);
		}
		if (objectToTake.equals("drumset")) {
			Character.setHasDrums(true);
		}
		if (objectToTake.equals("saxophone")) {
			Character.setHasSaxophone(true);
		}

		String name = "";
		try {
			Connection connection = Game.getConnection();
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// selects object based on name and location, creates a ResultSet
			ResultSet res = statement
					.executeQuery("SELECT * FROM OBJECTS WHERE (NAME ='"
							+ objectToTake + "' AND LOCATION =" + location
							+ ")");
			boolean first = res.next();
			name = res.getString(3);
			if (first == true) {

				// changes location to inventory (room 1)
				statement = connection.createStatement();
				int result = statement
						.executeUpdate("UPDATE OBJECTS SET LOCATION = 1 WHERE NAME = '"
								+ objectToTake + "'");
				GUI.addToOptions("\n\nGOO takes the " + name);
				connection.commit();

				res.close();
				statement.close();
				printInventory();
			}
		} catch (Exception e) {
			// System.err.println("Exception: " + e.getMessage());

		}

	}
	//reverse of take object. changes location from inventory to wherever player is at the moment.
	public static void dropObject(String command) {
		int location = Character.getLocation();
		String objectToDrop = command.substring(5);

		/*
		 * if (objectToTake.equals("id")) { Character.setHasID(); } if
		 * (objectToTake.equals("leather jacket")) {
		 * Character.setHasLeatherJacket(); } if
		 * (objectToTake.equals("lanyard")) { Character.setHasLanyard(); }
		 */

		String name = "";
		try {
			Connection connection = Game.getConnection();
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
						.executeUpdate("UPDATE OBJECTS SET LOCATION = "
								+ location + " WHERE NAME = '" + objectToDrop
								+ "'");
				GUI.addToOptions("\n\nGOO drops the " + name);
				connection.commit();

				res.close();
				statement.close();
				printInventory();
			}
		} catch (Exception e) {
			// System.err.println("Exception: " + e.getMessage());

			GUI.addToOptions("You don't have a " + objectToDrop);
		}

	}

	

	// inventory is room 1, so prints every object in room 1
	public static void printInventory() {
		GUI.clearaInventory();
	    try {
	        Connection connection = Game.getConnection();
	        java.sql.Statement statement = connection.createStatement();
	 
	        // get room description from a ResultSet
	        ResultSet res = statement
	                .executeQuery("SELECT * FROM OBJECTS WHERE LOCATION = 1");
	 
	        while (res.next()) {
	            String name = " " + res.getString(3);
	            GUI.showInventory(name);
	        }
	        res.close();
	        statement.close();
	    } catch (Exception e) {
	        System.err.println("Exception: " + e.getMessage());
	    }
	 
	}

	// command is "help", lists all commands
	public static void listCommands() {
		GUI.clearCommands();
		GUI.showCommands("  List of actions:\n");

		GUI.showCommands("");
		GUI.showCommands("\n  look -to look around\n");
		GUI.showCommands("  look at object/person\n");
		GUI.showCommands("  talk to person\n");
		GUI.showCommands("  take (object)\n");
		GUI.showCommands("  drop (object)\n");
		GUI.showCommands("  N, E, S, W -to move");

	}

	//in case you want to give up, take off GOO's spacesuit, and walk on to the moon. :<
	public static void removeSuit() {
		Character.setHasSuitOn(false);
		GUI.showResults("GOO removes his spacesuit. Feels good. A little dip in the ocean would be perfect right now. Well, there's no ocean. Maybe a moon stroll then.");
	}

	//takes command from command line in GUI, looks for it here
	public static void proccessCommand(String command) {
		
		//i keep track of who's being talked to with the talkingTo variable 
		String talkingTo = Dialog.getTalkingTo();
		if (talkingTo != null) {

			if (talkingTo.equals("nico")) {
				Dialog.talkToNico(command);
			}
			if (talkingTo.equals("Charlie")) {
				Dialog.talkToCharlie(command);
			}

			if (talkingTo.equals("hugo")) {
				Dialog.talkToHugo(command);
			}
			if (talkingTo.equals("aggie")) {
				Dialog.talkToAggie(command);
			}

			if (talkingTo.equals("louis")) {
				Dialog.talkToLouis(command);
			}

			if (talkingTo.equals("shopkeeper")) {
				Dialog.talkToShopkeeper(command);
			}
			if (talkingTo.equals("sunra")) {
				Dialog.talkToSunRa(command);
			}
			if (talkingTo.equals("captain")) {
				Dialog.talkToCaptain(command);
			}
		}
		
		//commands
		command.toLowerCase();
		if (command.equals("help")) {
			Commands.listCommands();
		} else if (command.equals("look")) {
			Commands.examineRoom();
		} else if (command.contains("take")) {
			Commands.takeObject(command);
		} else if (command.contains("drop")) {
			Commands.dropObject(command);
		} else if (command.equals("i")) {
			Commands.printInventory();
		} else if (command.contains("look at")) {
			Commands.examineObject(command);
		} else if (command.equals("n")) {
			Commands.move(command);
		} else if (command.equals("e")) {
			Commands.move(command);
		} else if (command.equals("s")) {
			Commands.move(command);
		} else if (command.equals("w")) {
			Commands.move(command);
		} else

		if (command.contains("talk to")) {
			Commands.talk(command);
		} else if (command.contains("move box")) {
			Commands.moveBox();
		} else

		if (command.equals("get location")) {
			int location = Character.getLocation();
			String locale = Integer.toString(location);
			GUI.showResults(locale);
		} else if (command.equals("clear")) {
			GUI.clear();
		} else if (command.equals("remove suit")) {
			Commands.removeSuit();
		}
		
		//hope is a counter that decrements with every movement. if you wander around enough, 
		//the game asks you if you wouldn't really rather kill GOO, and tells you how. :<
		if (Character.getHope() == 20) {
			GUI.addToOptions("Is this really worth it?");
		}
		if (Character.getHope() == 10) {
			GUI.addToOptions("GOO, you're just wandering around. Maybe you should just give up.");
		}
		if (Character.getHope() == 5) {
			GUI.addToOptions("GOO, this is not going well, why don't you just walk into the vacuum of space and put yourself out of your misery? (just enter 'remove suit', kiddo. ");
		}

		if (Character.isAlive() == false) {
			GUI.showResults("GOO is dead. \n\nGAME OVER.");

		}

	}
	
	//switch statement for movement. case numbers correspond to room numbers in database. 
	//within each case/room, limits on direction of travel (n, e, s, w) are imposed with if statements.
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
				GUI.showResults("GOO doesn't want to go that way.");
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
					GUI.addToOptions("You suffocated before you could freeze to death, GOO. Maybe a rover will find you someday. What a pity.");
					Character.setIsAlive(false);
				}

				examineRoom();
				break;
			} else {
				GUI.showResults("GOO doesn't want to go that way.");
				break;
			}

		case 4: // boardwalk
			if (command.equals("n")) {
				GUI.clearOptions();
				Character.setLocation(5);
				examineRoom();
				break;
			}
			if (command.equals("s")) {
				GUI.clearOptions();
				Character.setLocation(3);
				if (Character.hasSuitOn() == false) {
					GUI.addToOptions("Well you're walkin' on the moon without your spacesuit on, GOO. Keep on walkin', if that's what you really want.");
				}
				examineRoom();
				break;
			}
			if (command.equals("w")) {
				GUI.clearOptions();
				if (Character.hasID()) {
					// Dialog.charlie();
					Character.setLocation(11);
					examineRoom();
					break;
				} else {
					GUI.addToOptions("Charlie steps forward and drawls, 'Sorry old friend, you need your Medtronic ID badge or I can't let you onto company property.'");
					break;
				}
			}
			if (command.equals("e")) {
				GUI.clearOptions();
				Character.setLocation(7);
				examineRoom();
				break;
			} else {
				GUI.addToOptions("GOO doesn't want to go that way.");
				break;
			}

		case 5: // space bar
			if (command.equals("n")) {
				Character.setLocation(6);
				examineRoom();
				break;
			}
			if (command.equals("s")) {
				GUI.clearOptions();
				Character.setLocation(4);
				examineRoom();
				break;
			} else {
				GUI.showResults("The Man built walls to hold GOO back, and they're pretty solid.");
				break;

			}

		case 6: // back room
			if (command.equals("s")) {
				Character.setLocation(5);
				examineRoom();
				break;
			} else {
				GUI.showResults("There's only one way outta here, and that's south.");
				break;
			}

		case 7: // boardwalk east
			if (command.equals("n")) {
				if (Character.hasLeatherJacket()) {
					Character.setLocation(8);
					examineRoom();
					break;
				} else {
					GUI.showResults("The doorman sniffs at GOO's miner's clothing. \"Sorry pal, you're not getting in looking like that. This club is for cool, neato rockers. No miners allowed.\"");
					break;
				}
			}
			if (command.equals("w")) {
				Character.setLocation(4);
				examineRoom();
				break;
			}
			if (command.equals("e")) {
				Character.setLocation(9);
				examineRoom();
				break;
			} else {
				GUI.showResults("There's a holographic ocean in the way.");
				break;
			}

		case 8: // jupiter Club

			if (command.equals("s")) {
				GUI.clearOptions();
				Character.setLocation(7);
				examineRoom();
				break;
			} else {
				GUI.showResults("The Man built walls to hold GOO back, and they're pretty solid.");
				break;
			}

		case 9: // guitar center
			if (command.equals("w")) {
				GUI.clearOptions();
				Character.setLocation(7);
				examineRoom();
				break;
			}
			if (command.equals("e")) {
				GUI.clearOptions();
				Character.setLocation(10);
				examineRoom();
				break;
			} else {
				GUI.showResults("Nope");
				break;

			}

		case 10: // alley
			if (command.equals("w")) {
				GUI.clearOptions();
				Character.setLocation(9);
				examineRoom();
				break;
			} else {
				GUI.showResults("Maybe on Earth the back alleys lead somewhere. Here on the moon, you can only go WEST, back into Guitar Centers.");
				break;

			}

		case 11: // Mining HQ
			if (command.equals("n")) {
				GUI.clearOptions();
				Character.setLocation(12);
				examineRoom();
				break;
			}
			if (command.equals("w")) {
				GUI.clearOptions();
				if (Character.hasLanyard() == true) {
					Character.setLocation(13);
					examineRoom();
					break;
				} else {
					GUI.showResults("GOO loves all spaceships. But this one is particularly rad. "
							+ "Very sleek cruise liner, in the classic featureless-silver-cube style of the 2240''s. Only passengers and crew can board, and GOO is currently neither.");
					break;
				}
			}
			if (command.equals("e")) {
				GUI.clearOptions();
				Character.setLocation(4);
				examineRoom();
				break;
			} else {
				GUI.showResults("Only a maze of conveyer belts to the south. GOO doesn't want to be crushed by moondust.");
				break;
			}

		case 12: // old refinery
			if (command.equals("s")) {
				GUI.clearOptions();
				Character.setLocation(11);
				examineRoom();
				break;
			} else {
				GUI.showResults("GOO runs through a seemingly endless maze of alleyways, some of them flickering with disorienting holographic graffitti, and returns to the mouth of the refinery.");
				break;
			}

		case 13: // spaceship
			if (command.equals("e")) {
				GUI.clearOptions();
				Character.setLocation(11);
				examineRoom();
				break;
			} else {
				GUI.showResults("You can only leave a spaceship through a door.");
				break;
			}
		default:
			break;
		}

	}

	public static void moveBox() {
		GUI.clearOptions();
		GUI.showResults("GOO slides the box to one side, noticing that it's painted a rich shade of gold. It's actually not a box at all, but the entrance to some kind of temple structure, deep beneath the moon. After descending staircase upon collonaded staircase, GOO finally reaches the bottom, where in the center of a bejewelled chamber glowing red with a hundred floating lights, sits a man. \n\nIt is Sun Ra.");
		GUI.clearOptions();

	}
	
	//strips "talk to person" command to their name, then goes to corresponding 'talkTo' method in dialog.
	public static void talk(String command) {
		String npc = command.substring(8);
		if (npc.equals("nico")) {
			Dialog.talkToNico(command);
		} else if (npc.equals("charlie")) {
			Dialog.talkToCharlie(command);
		} else if (npc.equals("hugo")) {
			Dialog.talkToHugo(command);
		} else if (npc.equals("aggie")) {
			Dialog.talkToAggie(command);
		} else if (npc.equals("louis")) {
			Dialog.talkToLouis(command);
		} else if (npc.equals("shopkeeper")) {
			Dialog.talkToShopkeeper(command);
		} else if (npc.equals("sun ra")) {
			Dialog.talkToSunRa(command);
		} else if (npc.equals("captain")) {
			Dialog.talkToCaptain(command);
		}

	}
	
	//sun ra blasts off onto the moon after his work is done. people are in the objects table - his location is changed.
	public static void moveSunRaToLunarPlain() {
		int location = Character.getLocation();
		String sunra = "sun ra";

		String name = "";
		try {
			Connection connection = Game.getConnection();
			java.sql.Statement statement = connection.createStatement();

			// getting the data back

			// get object from a ResultSet
			ResultSet res = statement
					.executeQuery("SELECT * FROM OBJECTS WHERE NAME = '"
							+ sunra + "'");
			boolean first = res.next();
			name = res.getString(3);
			if (first == true) {

				statement = connection.createStatement();
				int result = statement
						.executeUpdate("UPDATE OBJECTS SET LOCATION = " + 2
								+ " WHERE NAME = '" + sunra + "'");
				GUI.addToOptions("\n\nSun Ra is outta here");
				connection.commit();

				res.close();
				statement.close();
			}
		} catch (Exception e) {
			GUI.showResults("Exception: " + e.getMessage());

		}

	}

}

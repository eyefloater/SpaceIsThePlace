package com.gaby.space;

import java.sql.Connection;
import java.util.Scanner;

public class Game {

	private static Connection connection;

	public static Connection getConnection() {
		return connection;
	}

	public Game(Connection connection) {
		Game.connection = connection;

		System.out.println("sPaCe Is ThE pLaCe\n");
		Commands.listCommands();
		Commands.printInventory();

		//initializes states of character
		Character.setLocation(3);
		Character.setHope(40);
		Character.setHasSuitOn(true);
		Character.setIsAlive(true);
		Character.setHasCreditCube(false);
		Character.setHasRover(false);
		
		Character.setHasBigIdea(false);
		Character.setHasDrums(false);
		Character.setHasSaxophone(false);
		Character.setHasLanyard(false);
		
		//dialog uses counters to keep track of which sub-dialog to go to. initialized here.
		Dialog.setNicoCounter(1);
		Dialog.setAggieCounter(1);
		Dialog.setLouisCounter(1);
		Dialog.setCharlieCounter(1);

		Commands.examineRoom();
		
		GUI.showResults("The sun has just dipped out of sight. The smooth surface of the lunar plain extends to the horizon.  The end of another moon day, and of another day of unemployment for GOO, ex-miner. \n\nThe Medtronic enclave with its vast dome of age-warped, slightly murky plastic looms to the NORTH. His home, for now. Through it he can see the outline of mining town he''s called home for 20 years. There is a world back there that matters to somebody. But it may as well be a projection, for all it matters to him. A dying company town, and around it, a manmade Earth landscape with it''s fake deserts, fake marshes, fake woods. Only thing worse than monotony, he reflects, is variation without purpose: these lands were built to be carbon collectors,  any variation is purely sentimental, decorative. He knows from the movies what real Earth landscapes used to be like: the transition from one to another always chaotic and imperceptible. It is in the abrupt boundaries between these manmade ''ecosystems'' that the clumsy hand of its human designers is most evident in Medtronic''s terraformed zone. ''''A goddamn golf course'''', as his grandpa used to say. Built in a hurry, like any other frontier town. \n\nGOO turns away, feeling a little stiff in his company-issue space suit, and strides out into the silent, glimmering lunar countryside. \rOur thoughts repeat themselves endlessly, not that any of us keep count. Beneath that patterned spiral, emotions repeat themselves to another rhythm. After a hundred solitary night walks out here on the barren plains outsdide the Zone, GOO was used to letting his mind billow out into the vacuum like a solar sail, and lately, he''d been feeling the same mixture of dread and adrenaline beneath his silent observations: I''ve come unquilted, there''s nothing holding me here anymore. He knows the Earth is floating behind him, above the dome. He isn''t about to turn and look at either place. GOO''s mind quiets, spirit rising. \n\nTonight he''s watching for Jupiter."); 
		
		//main game loop. checks for 
		do {

			System.out.println("\nEnter a command:");
			Scanner scanner = new Scanner(System.in);
			String command = scanner.nextLine();
			command.toLowerCase();
			if (command.equals("help")) {
				Commands.listCommands();
			}
			if (command.equals("look")) {
				Commands.examineRoom();
			}
			if (command.contains("take")) {
				Commands.takeObject(command);
			}
			if (command.contains("drop")) {
				Commands.dropObject(command);
			}
			if (command.equals("i")) {
				Commands.printInventory();
			}
			if (command.contains("look at")) {
				Commands.examineObject(command);
			}
			if (command.equals("n")) {
				Commands.move(command);
			}
			if (command.equals("e")) {
				Commands.move(command);
			}
			if (command.equals("s")) {
				Commands.move(command);
			}
			if (command.equals("w")) {
				Commands.move(command);
			}
			
			
			if (command.contains("talk to")) {
				Commands.talk(command);
			}
			if (command.contains("move box")) {
				Commands.moveBox();
			}
			
			
			if (command.equals("get location")) {
				int location = Character.getLocation();
				System.out.println(location);
			}
			if (command.equals("remove suit")) {
				Commands.removeSuit();
			}

			if (Character.getHope() == 20) {
				System.out.println("Is this really worth it?");
			}
			if (Character.getHope() == 10) {
				System.out.println("GOO, you're just wandering around. Maybe you should just give up.");
			}
			if (Character.getHope() == 5) {
				System.out
						.println("GOO, this is not going well, why don't you just walk into the vacuum of space and put yourself out of your misery? (just enter 'remove suit', kiddo. ");
			}

			if (Character.isAlive() == false) {
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("GOO is dead. GAME OVER, obviously.");
				System.out.println("PLAY AGAIN? y/n");
				if(scanner.equals("y")){System.exit(0); /*start new game*/}   //<-------------------------------------
				else{System.exit(0);}
			}
			
		} while (true);

		// read data base configuration

	}

	public void start() {
		// TODO Auto-generated method stub

	}

}

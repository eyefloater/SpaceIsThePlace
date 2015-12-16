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

		Commands.listCommands();
		Commands.printInventory();

		//initializes states of character
		Character.setLocation(3);
		Character.setHope(40);
		Character.setHasSuitOn(true);
		Character.setIsAlive(true);
		Character.setHasCreditCube(false);
		Character.setHasRover(false);
		
		//these are the central victory requirements, with haslanyard=true allowing entrance to spaceship 
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
		
		GUI.showResults("The sun has just dipped out of sight. The smooth surface of the lunar plain extends to the horizon.  The end of another moon day, and of another day of unemployment for GOO, ex-miner. The Medtronic enclave with its vast dome of age-warped, slightly murky plastic looms to the NORTH. His home, for now. Through it he can see the outline of the dying mining town he's called home for 20 years. GOO turns away, feeling a little stiff in his company-issue space suit, and strides out into the silent, glimmering lunar countryside. After a hundred solitary night walks out here on the barren plains outsdide the Zone, GOO is used to letting his mind billow out into the vacuum like a solar sail, and lately, he's been feeling the same mixture of dread and adrenaline beneath his silent observations: I've come unquilted, there's nothing holding me here anymore. He knows the Earth is floating behind him, above the dome. He isn't about to turn and look at either place. GOO's mind quiets, spirit rising. \n\nTonight he's watching for Jupiter."); 
		
		//main game loop (in command line version. there is no longer a loop as such, command line in GUI sends commands to Commands.proccessCommand(); 
		do {

			
		} while (true);

		// read data base configuration

	}

	public void start() {
		// TODO Auto-generated method stub

	}

}

package com.gaby.space;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class Game {

	private static Connection connection;

	public static Connection getConnection() {
		return connection;
	}

	public Game(Connection connection) {
		this.connection = connection;

		System.out.println("sPaCe Is ThE pLaCe\n");
		System.out.println("Type 'help' for a list of commands.");

		Character.setLocation(3);
		Character.setHope(40);
		Character.setHasSuitOn(true);
		Character.setIsAlive(true);
		Commands.examineRoom();
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

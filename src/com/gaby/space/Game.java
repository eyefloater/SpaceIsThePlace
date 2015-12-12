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
		Character.setLocation(2);
		Commands.listCommands();
		Commands.examineRoom();
		do {
		
			System.out.println("\nEnter a command:");
			Scanner scanner = new Scanner(System.in);
			String command = scanner.nextLine();
			command.toLowerCase();
			if(command.equals("help")){Commands.listCommands();}
			if(command.equals("look")){Commands.examineRoom();}
			if(command.contains("take")){Commands.takeObject(command);}
			if(command.equals("i")){Commands.printInventory();}
			if(command.contains("look at")){Commands.examineObject(command);}
			if(command.equals("n")){Commands.move(command);}
			if(command.equals("e")){Commands.move(command);}
			if(command.equals("s")){Commands.move(command);}
			if(command.equals("w")){Commands.move(command);}
			if(command.equals("get location")){int location = Character.getLocation(); System.out.println(location);}



			

		} while (true);

		// read data base configuration
		
		
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

}
	

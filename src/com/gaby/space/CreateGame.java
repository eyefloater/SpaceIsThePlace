package com.gaby.space;

import java.sql.Connection;

public class CreateGame {
	
	//main method, starts database, connection and creates new game
	public static void main(String[] args) {
		new GUI();
		GameDataBase dataBase = new GameDataBase();
		Connection connection = dataBase.getConnection();
		
		playGame(connection);
		dataBase.closeDataBase();
	}

	private static void playGame(Connection connection) {
		Game game = new Game(connection);
		game.start();
	}

}
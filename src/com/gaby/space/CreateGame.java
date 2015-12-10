package com.gaby.space;

import java.sql.Connection;

public class CreateGame {
	

	public static void main(String[] args) {
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
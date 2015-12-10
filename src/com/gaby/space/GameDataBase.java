package com.gaby.space;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//using apache derby so users don't have to install any database - it 
//runs in-memory, creates database at run-time, goes away when game is closed
//its a SQL database that can persist the game's data in a real database
public class GameDataBase {
	
	//string tells apache derby to create an in-memory database
	public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	//GAMEDB is the database schema
	private static final String dbName = "GAMEDB";
	//connection between client and server sides of derby
	private Connection connection = null;


	public GameDataBase()	{
		createDataBase();
	}
	
	public void closeDataBase()	{
		dropSchema();
		dropDataBase();
		closeConnection();
		shutdown();
	}


	private void dropDataBase() {
		try {
			Statement stmt = connection.createStatement();
			stmt.execute("DROP TABLE ROOMS");
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void dropSchema() {
		try {
			Statement stmt = connection.createStatement();
			stmt.execute("DROP SCHEMA GAMESCHEMA RESTRICT");
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	private void createDataBase() {
		try {
			//creates a new instance of derby client
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			// Get a connection, creates connection to derby server side
			connection = DriverManager.getConnection("jdbc:derby:memory:"+dbName+";create=true;user=me;password=mine");
			
			//Statement allows running of SQL statements in the game's database. 
			Statement stmt = connection.createStatement();
			//creates schema, "rooms" table, "doors" table and inserts rows into those tables (individual rooms and doors)
			//rooms have two columns: an integer ID (primary key) and a varchar (string) for its name	
			stmt.execute("CREATE SCHEMA GAMESCHEMA AUTHORIZATION ME");
			
			stmt.execute("CREATE TABLE ROOMS (ID INT, NAME VARCHAR(12), DESCRIPTION VARCHAR(128))");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (1, 'Inventory', '')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (2, 'Second', 'Blue walls')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (3, 'Third','Blue walls')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (4, 'Fourth','Blue walls')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (5, 'Fifth','Blue walls')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (6, 'Sixth','Blue walls')");
			
			stmt.execute("CREATE TABLE DOORS (ID INT, ROOM1 INT, ROOM2 INT)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (1, 1, 2)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (2, 2, 3)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (3, 3, 4)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (4, 4, 5)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (5, 5, 6)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (6, 6, 1)");
			
			stmt.execute("CREATE TABLE OBJECTS (ID INT, LOCATION INT, NAME VARCHAR(12), DESCRIPTION VARCHAR(64), OWNED BOOLEAN)");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION, OWNED) VALUES (1, 2, 'GUITAR', 'The guitar is red.', FALSE)");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION, OWNED) VALUES (2, 2, 'CUP', 'The cup says I LOVE NY.', FALSE)");
			
			//commiting sends it from client to the database, checks for errors
			connection.commit();
			stmt.close();
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	
	private void closeConnection()	{
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	


	private void shutdown() {
		try {
//			if (stmt != null) {
//				stmt.close();
//			}
			if (connection != null) {
				DriverManager.getConnection("jdbc:derby:memory:GAMEDB;shutdown=true");
				connection.close();
			}
		} catch (SQLException sqlExcept) {

		}

	}

	public Connection getConnection() {
		return connection;
	}
}
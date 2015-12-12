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
			
			stmt.execute("CREATE TABLE ROOMS (ID INT, NAME VARCHAR(20), DESCRIPTION VARCHAR(8000))");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (1, 'Inventory', '')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (2, 'Lunar Surface', 'The sun has just dipped out of sight. The smooth surface of the lunar plain extends to the horizon. \nGOO parks his 2-track just off the road, climbs out with a spare tank and looks back up the road as he shuts the door. \nTo the North, the Medtronic enclave with its vast dome of age-warped, slightly murky plastic. \n\nGOO turns, feeling a little weak in the legs as he scrambles down the embankment to stride \nout into the silent, glimmering lunar countryside.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (3, 'Boardwalk','The boardwalk that runs along the Zone's northern boundary extends before GOO, its stained plastic planks lit with overlapping planes of garish neon reds, blues, and sickly greens that emanate from the dense jumble of tourist shops that loom over its landward side. To the west, , behind the flickering, 100 year old projected ocean view, GOO can hear the grinding and hum of the platinum mines. GOO's family property is a single 4-story tower of corrugated tin and plastic wedged between a brothel and a PayDay Loan shop. Outside his grandfather's ground floor cafe, a dozen folded Fanta Limon parasols emerge haphazardly from a jumble of plastic tables, flapping lamely in the artificial ocean-scented breeze. Inside, the cafe is brightly lit but deserted; GOO's friend Agnes is washing glasses. Two brothel workers puff on E-cigarettes at the mouth of the narrow alley, wrapped in long, silvery miner's jackets, clearly at the end of their shift.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (4, 'Space Bar','THE SPACIOUS SPACE BAR, as the neon sign proclaims is in fact quite a spacious place. It is completely deserted, except for Agnes, who is washing an endless series of glasses in the sink behind the counter. The stereo is playing the usual mixture of 20th century ye-ye, country, and modern Algorhythmia pop. What dross.')");
			
			stmt.execute("CREATE TABLE DOORS (ID INT, ROOM1 INT, ROOM2 INT)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (1, 1, 2)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (2, 2, 3)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (3, 3, 4)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (4, 4, 5)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (5, 5, 6)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (6, 6, 1)");
			
			stmt.execute("CREATE TABLE OBJECTS (ID INT, LOCATION INT, NAME VARCHAR(12), DESCRIPTION VARCHAR(64), OWNED BOOLEAN)");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION, OWNED) VALUES (1, 1, 'tank', 'Goo likes breathing', FALSE)");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION, OWNED) VALUES (1, 2, 'moon rock', 'Shiny moon rock', FALSE)");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION, OWNED) VALUES (2, 2, 'GUITAR', 'The guitar is red.', FALSE)");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION, OWNED) VALUES (3, 4, 'CUP', 'The cup says I LOVE NY.', FALSE)");
			
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
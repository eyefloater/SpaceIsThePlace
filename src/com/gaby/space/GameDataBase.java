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
			
			//database of 'rooms'. 
			stmt.execute("CREATE TABLE ROOMS (ID INT, NAME VARCHAR(20), DESCRIPTION VARCHAR(8000))");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (1, 'Inventory', '')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (2, 'Lunar Surface SOUTH', 'About 100 clicks south of the Zone, GOO comes across an abandoned rover.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (3, 'Lunar Surface', 'The sun has just dipped out of sight. The smooth surface of the lunar plain extends to the horizon. \nGOO parks his 2-track just off the road, climbs out with a spare tank and looks back up the road as he shuts the door. \nTo the North, the Medtronic enclave with its vast dome of age-warped, slightly murky plastic. \n\nGOO turns, feeling a little weak in the legs as he scrambles down the embankment to stride \nout into the silent, glimmering lunar countryside.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (4, 'Boardwalk','The boardwalk that runs along the Zone's northern boundary extends before GOO, its stained plastic planks lit with overlapping planes of garish neon reds, blues, and sickly greens that emanate from the dense jumble of tourist shops that loom over its landward side. To the west, , behind the flickering, 100 year old projected ocean view, GOO can hear the grinding and hum of the platinum mines. GOO's family business, the Space Bar Bar, is a one story affair of corrugated tin and , wedged between a brothel and a PayDay Loan shop, both boarded up. A dozen folded Fanta Limon parasols emerge haphazardly from a jumble of plastic tables outside the bar, flapping lamely in the artificial ocean-scented breeze. To the NORTH, the cafe is brightly lit but deserted; GOO's friend Nico is washing glasses. To the WEST, a miner mans the Medtronic checkpoint, puffing an E-cigarete To the EAST, GOO can see the holographic marquee of the Jupiter Club.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (5, 'Space Bar','THE SPACE BAR BAR is indeed quite spacious inside. It is also completely deserted, except for Nico, who stands washing an endless series of glasses in the sink behind the counter. The stereo is playing the usual mixture of 20th century ye-ye, country, and modern Algorhythmia pop. GOO sees his wallet ontop of the gaming console.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (6, 'Back room', 'This is were GOO sleeps. There's a small cot, a space heater, Goo's record player and records, and a large pile of dirty clothes. Home sweet home. ')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (7, 'Boardwalk EAST', 'The eastern stretch of the Medtronic boardwalk is bustling with what passes for nightlife on 2270's Luna. The Jupiter club, with it's holographic marquee, is to the NORTH, Guitar Center is to the EAST.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (8, 'Jupiter Club', 'The Jupiter Club is less busy than it used to be, when mining was still a viable career choice in this particular company town. Still, the house band, a motley group of Martian refugees called the Tireds From Mars is churning out some passable covers of 2050's asteroid mining ballads. The manager, Louis, watches them with a glazed look on his face. ')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (9, 'Rehearsal Space', 'The nearly empty rehearsal room; at least there's a working PA.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (10, 'Shop', 'Guitar Center. GOO has seen all this before. The shopkeeper looks slightly sinister. GOO notices a gleaming silver drumkit in the corner.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (11, 'Alley', 'GOO steps into the alley behind the shop. The dumpsters are overflowing. There's a large box in the shadows; a thin reedy buzz emanates from within. ')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (12, 'Mining HQ', 'The noise around the mining facility is unbearable. There's no pleasant projected ocean view here, just a monstrous accumulation of ore, and at least a dozen 40 year old robots shoveling away methodically at the pile. The single human around is your old boss, Hugo F. He's up to his shins in dust, still cursing in his wild Venusian accent to some unlucky Medtronic Customer Service AI over the phone, brandishing an actual shovel in his other hand.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (13, 'Old refinery', 'A broad concrete expanse bounded by the grim outbuildings of an oil refinery. The surface of the walls are so featureless nearby that GOO is afraid they're computer generated, but they darken with rich grime as they recede into the complex. Aggie sees him before he sees her. She is already running northwards, one hand dragging along the east wall as she skips backwards. She knows he will follow, as he always does- hence the mixture of joy and irony on her face as it recedes into the narrowing half shadow. Beneath the grime, the shadowy walls behind her start to glow a rosy pink, as if in response to her presence; as if he were entering her heart.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (14, 'Spaceship', 'GOO loves all spaceships. But this one is particularly rad. Very sleek, in the classic featureless-silver-cube style of the 2240's. A clearly intoxicated spaceship captain is sitting near the coffee machine, muttering to himself')");
			
			//probably get rid of this. openDoor can just be a method that is called to check if you have a key, when 
			//you enter N S E or W and there is a door in the way. 
			stmt.execute("CREATE TABLE DOORS (ID INT, ROOM1 INT, ROOM2 INT)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (1, 1, 2)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (2, 2, 3)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (3, 3, 4)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (4, 4, 5)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (5, 5, 6)");
			stmt.execute("INSERT INTO DOORS (ID, ROOM1, ROOM2) VALUES (6, 6, 1)");
			
			//database of all objects, including those in inventory, which is represented as room 1.
			stmt.execute("CREATE TABLE OBJECTS (ID INT, LOCATION INT, NAME VARCHAR(12), DESCRIPTION VARCHAR(64), OWNED BOOLEAN)");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION, OWNED) VALUES (1, 1, 'tank', 'Goo likes breathing', FALSE)");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION, OWNED) VALUES (1, 2, 'moon rock', 'Shiny moon rock', FALSE)");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION, OWNED) VALUES (1, 2, 'rover', 'This little guy should be easy to carry.', FALSE)");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION, OWNED) VALUES (2, 2, 'Sun Ra's saxophone', 'The real deal.', FALSE)");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION, OWNED) VALUES (3, 4, 'CUP', 'The cup says I LOVE NY. GOO has no idea what ny means or how it's pronounced.', FALSE)");
			//rover
			//wallet
			//ID
			//AGGIE's CREDIT STICK
			//LEATHER JACKET
			//RECORDS
			//RECORD PLAYER
			//DRUMKIT
			//LANYARDS
			//ROBOTS
			//ORE
			//LARGE BOX
			//
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
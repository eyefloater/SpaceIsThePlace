package com.gaby.space;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//using apache derby so users don't have to install any database - it 
//runs in-memory, creates database at run-time, goes away when game is closed
//its a SQL database that can persist the game's data in a real database
public class GameDataBase {

	// string tells apache derby to create an in-memory database
	public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	// GAMEDB is the database schema
	private static final String dbName = "GAMEDB";
	// connection between client and server sides of derby
	private Connection connection = null;

	public GameDataBase() {
		createDataBase();
	}

	public void closeDataBase() {
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

	/*
	 * to update a column value in the current row. In a scrollable ResultSet
	 * object, the cursor can be moved backwards and forwards, to an absolute
	 * position, or to a position relative to the current row. The following
	 * code fragment updates the NAME column in the fifth row of the ResultSet
	 * object rs and then uses the method updateRow to update the data source
	 * table from which rs was derived.
	 * 
	 * rs.absolute(5); // moves the cursor to the fifth row of rs
	 * rs.updateString("NAME", "AINSWORTH"); // updates the // NAME column of
	 * row 5 to be AINSWORTH rs.updateRow(); // updates the row in the data
	 * source
	 */
	private void createDataBase() {
		try {
			// creates a new instance of derby client
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			// Get a connection, creates connection to derby server side
			connection = DriverManager.getConnection("jdbc:derby:memory:"
					+ dbName + ";create=true;user=me;password=mine");

			// Statement allows running of SQL statements in the game's
			// database.
			Statement stmt = connection.createStatement();

			// creates schema, "rooms" table, "objects" table and inserts rows
			// into those tables (individual rooms and objects)
			// rooms have 3 columns: an integer ID (primary key) varchar name,
			// varchar description (add shortened description for after first
			// visit)
			// objects have 4: id, location, name, description
			stmt.execute("CREATE SCHEMA GAMESCHEMA AUTHORIZATION ME");

			// database of rooms, probably have description print only first
			// time, then abbreviated version
			stmt.execute("CREATE TABLE ROOMS (ID INT, NAME VARCHAR(40), DESCRIPTION VARCHAR(7000))");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (1, 'Inventory', '')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (2, 'Lunar Surface SOUTH', 'About 100 clicks south of the Zone, GOO comes across an abandoned rover.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (3, 'Lunar Surface', 'The sun has just dipped out of sight. The smooth surface of the lunar plain extends to the horizon. \nGOO parks his 2-track just off the road, climbs out with a spare tank and looks back up the road as he shuts the door. \nTo the North, the Medtronic enclave with its vast dome of age-warped, slightly murky plastic. \n\nGOO turns, feeling a little weak in the legs as he scrambles down the embankment to stride \nout into the silent, glimmering lunar countryside.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (4, 'Boardwalk', 'The boardwalk that runs along the Zone''s northern boundary extends before GOO, its stained plastic planks \nlit with overlapping planes of garish neon reds, blues, and sickly greens that emanate from the dense jumble of tourist shops that loom over its landward side. \nTo the west, , behind the flickering, 100 year old projected ocean view, \nGOO can hear the grinding and hum of the platinum mines. GOO''s family business, the Space Bar Bar, \nis a one story affair of corrugated tin and , wedged between a brothel and a PayDay Loan shop, both boarded up. \nA dozen folded Fanta Limon parasols emerge haphazardly from a jumble of plastic tables outside the bar, flapping lamely in the artificial ocean-scented breeze. \nTo the NORTH, the cafe is brightly lit but deserted; GOO''s friend Nico is washing glasses. \nTo the WEST, a security guard, Charlie, mans the Medtronic checkpoint, puffing an E-cigarete To the EAST, GOO can see the holographic marquee of the Jupiter Club.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (5, 'Space Bar Bar', 'THE SPACE BAR BAR is indeed quite spacious inside. It is also completely deserted, except for Nico, \nwho stands washing an endless series of glasses in the sink behind the counter. The stereo is playing the usual mixture of \n20th century ye-ye, country, and modern Algorhythmia pop. GOO sees his wallet ontop of the gaming console.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (6, 'Back room', 'This is where GOO sleeps. There''s a small cot, a space heater, Goo''s record player and records, and a \nlarge and very dirty pile of clothes. Home sweet home. ')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (7, 'Boardwalk EAST', 'The eastern stretch of the Medtronic boardwalk is bustling with what passes for nightlife on 2270''s Luna. \nThe Jupiter club, with it''s holographic marquee, is to the NORTH, Guitar Center is to the EAST.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (8, 'Jupiter Club', 'The doorman whistles appreciatively as you stroll into The Jupiter Club, polished leather gleaming.  \nIt''s less busy than it used to be, when mining was still a viable career choice in this particular company town. \nStill, the house band, a motley group of Martian refugees called the Tireds From Mars is churning out some passable covers of 2050''s asteroid mining ballads. \nThe manager, Louis, watches them with a glazed look on his face. ')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (9, 'Rehearsal Space', 'The nearly empty rehearsal room; at least there''s a working PA.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (10, 'Guitar Center', 'Guitar Center. GOO has seen all this before. The shopkeeper looks slightly sinister. \nThe back door to the EAST has a small sign taped to it, reading DO NOT ENTER THE SUN IS TOO BRIGHT  \nGOO notices a gleaming silver drumkit in the corner.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (11, 'Alley', 'GOO steps into the alley behind the shop. The dumpsters are overflowing. \nThere''s a large box in the shadows; a thin reedy buzz emanates from within. ')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (12, 'Mining HQ', 'The noise around the mining facility is unbearable. \nThere''s no pleasant projected ocean view here, just a monstrous accumulation of ore, and at least a dozen 40 year old robots shoveling away methodically at the pile. \nThe single human around is your old boss, Hugo F. He''s up to his shins in dust, still cursing in his wild Venusian accent to some \nunlucky Medtronic Customer Service AI over the phone, brandishing an actual shovel in his other hand.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (13, 'Old refinery', 'A broad concrete expanse bounded by the grim outbuildings of an oil refinery. "
					+ "The surface of the walls are so featureless nearby that GOO is afraid they''re computer generated, but they darken with rich grime as they recede into "
					+ "the complex. Aggie sees him before he sees her. She is already running northwards, one hand dragging along the east wall as she skips backwards. "
					+ "She knows he will follow, as he always does- hence the mixture of joy and irony on her face as it recedes into the narrowing half shadow. "
					+ "Beneath the grime, the shadowy walls behind her start to glow a rosy pink, as if in response to her presence; as if he were entering her heart.')");
			stmt.execute("INSERT INTO ROOMS (ID, NAME, DESCRIPTION) VALUES (14, 'Spaceship', 'GOO feels a little dizzy. Hasn''t been on a ship for almost 20 years. The foyer is a sweeping Art Deco number in brilliant brushed steel. \nAggie and Nico put down their gear and gaze around dreamily. \nA clearly intoxicated spaceship captain is sitting near the coffee machine, "
					+ "muttering to himself. GOO WINS! YOU WIN! CONGRATULATIONS, SPACE IS THE PLACE!')");

			// database of all objects, including those in inventory, which is
			// represented as room 1.
			stmt.execute("CREATE TABLE OBJECTS (ID INT, LOCATION INT, NAME VARCHAR(40), DESCRIPTION VARCHAR(3000))");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (1, 1, 'saxophone reed', 'Not much fun without a saxophone.')");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (2, 2, 'moon rock', 'Shiny moon rock')");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (3, 2, 'rover', 'This little guy should be easy to carry.')");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (4, 11, 'saxophone', 'The real deal.')");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (5, 5, 'cup', 'The cup says I LOVE NY. GOO has no idea what ny means"
					+ " or how it''s pronounced.')");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (6, 5, 'wallet', 'Hmm, it''s empty. What were you doing last night, GOO?')");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (7, 5, 'id', 'Goo Enriquez, non-citizen, CLASS F Miner. EXPIRED. Unrenewable without employ. "
					+ "Can''t get employ without renewing it. Lovely. ')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (8, 12, 'credit cube', 'The display reads 50,000 credits. That would buy a ton of Neptune Juice, GOO concedes.')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (9, 6, 'leather jacket', 'It''s black, it''s shiny, it says ''GO'' on the back in silver. Preeeeety rad.')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (10, 6, 'laser discs', 'A fine collection of space jazz.')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (11, 6, 'laser disc player', 'This thing must be 200 years old.')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (12, 10, 'drumset', 'A beautiful, silver 5-piece jazz kit.')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (13, 8, 'lanyards', 'Your''s says ''''Goo Enriquez, Jupiter Cruises Employee.''''')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (14, 11, 'box', 'However''s making that buzzing sound is behind this box. Try ''move box'', maybe?')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (15, 5, 'nico', 'Your cousin Nico is the only member of your family left alive. Although you have an aunt living somewhere in Mars City. He''s a stellar bass player.')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (16, 4, 'charlie', 'Charlie has the face of a man who has spent most of his 140 years waist-deep in moon dust. A lovely guy, GOO''s glad he finally got this security gig.')");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (17, 8, 'Louis', 'Whadda guy! What a terrible, terrible guy. This guy still thinks he''s the king of the lunar nightlife, although his club''s been half-dead for 20 years.')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (18, 11, 'sun ra', 'Space is the place. GOO didn''t realize Sun Ra was actually still alive.')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (19, 10, 'shopkeeper', 'He''s reading a comic book called ''''WHY WE''RE SORRY WE MOSTLY BLEW UP THE EARTH'''' by the Martian Nihilist Front.')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (19, 10, 'hugo', 'Your former boss. He''s hardnosed and, GOO imagines, fairly depressed to be literally the only human being at this jobsite. Robots don''t make great company.')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (20, 11, 'aggie', 'Oh, Aggie. Why are you still on the moon? You''re too good for this place.')");

			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (21, 14, 'captain', 'This guy looks like your average cruiseship captain, only his white uniform is a few sizes too small, and his hat is just tiny.')");
			stmt.execute("INSERT INTO OBJECTS (ID, LOCATION, NAME, DESCRIPTION) VALUES (22, 6, 'pile of clothes', 'After digging through his great unwashed, GOO comes across his old leather jacket. Still supple and shiny as ever; still the coolest thing he''s ever owned.')");

			// commiting sends it from client to the database, checks for errors
			connection.commit();
			stmt.close();
		} catch (SQLException | InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void closeConnection() {

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void shutdown() {
		try {
			// if (stmt != null) {
			// stmt.close();
			// }
			if (connection != null) {
				DriverManager
						.getConnection("jdbc:derby:memory:GAMEDB;shutdown=true");
				connection.close();
			}
		} catch (SQLException sqlExcept) {

		}

	}

	public Connection getConnection() {
		return connection;
	}
}
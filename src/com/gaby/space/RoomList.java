/*package com.gaby.space;

import com.gaby.space.RoomList.Room;

public class RoomList {
	
	
	   accessible from which other rooms?
	   objects in room (database)
	 * 
	 
	
	public enum Room {
		FLAT(new String[] {"LOBBY", "REHEARSAL_ROOM", "SPACEBAR"}),
		LOBBY(new String[] {"FLAT", "GREENROOM"}), 
		REHEARSAL_ROOM(new String[] {"FLAT"}), 
		GREENROOM(new String[] {"LOBBY", "ON_STAGE"}), 
		ON_STAGE(new String[] {"GREENROOM", "STARSHIP"}), 
		SPACEBAR(new String[] {"FLAT"}), 
		STARSHIP(new String[] {"ON_STAGE"}), 
		INVENTORY(new String[] {});
	
		private final String[] adjoins;
		
		Room(String[] adjoins) {
			this.adjoins = adjoins;
		}
	};

	public Room[] adjoiningRooms(int numberOfRooms) {
		Room[] rooms = new Room[numberOfRooms];
		return rooms;
	}
	
	
	
}
*/
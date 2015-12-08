package com.gaby.space;

import com.gaby.space.RoomList.Room;

public class Character {
/*player class
status -
    isAlive? -
        if(isAlive=false{print "You died!"; Startup.endGame}
    health -
        if(health <1){isAlive=false}
    hope -
        if(hope <1){"You gave up!"; Startup.endGame}
    grace -
        determines quality of musical performance, affected by food, inspiration, bandmates
    location -
        rooms numbered. after every "pickup key" check if key is in same location, if not, "There's no key here"
	list of actions

	takeObject -
		checks if player is in room, then moves object from room to inventory,
		then edits text file to remove object from description of room_

	inventory -
		
 */
	private boolean isAlive;
	private int health;
	private int hope;
	private int grace;
	private Room location;
	
	public Character() {};
	
	private void takeObject(Object obj, Character actor) {
		if (obj.location == actor.location)
		{ 
			obj.location = Room.INVENTORY;
		}
		else
		{
			System.out.println("ERROR!");
		}	
	}
}

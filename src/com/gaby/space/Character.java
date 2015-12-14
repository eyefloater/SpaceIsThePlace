package com.gaby.space;

//import com.gaby.space.RoomList.Room;

//there's only one main character, so i've represented him with this abstract class
public abstract class Character {

	//location, used a lot.
	private static int location;
	
	private static int hope;
	
	//these are states that need to change for the player to advance the plot. used getters and setters.
	
	private static boolean hasLanyard; //gets GOO hired onto spaceship to WIN. player needs saxophone, drums, and a big idea to get gig
	
	private static boolean hasBigIdea;
	private static boolean hasDrums;
	private static boolean hasSaxophone;
	
	private static boolean hasID; //needed to get past security guard
	private static boolean hasLeatherJacket; //needed to get into nightclub
	private static boolean hasRover; //traded for credits
	private static boolean hasCreditCube;  //used to buy drums
	
	private static boolean isAlive; 
	private static boolean hasSuitOn; //removable to die on the moon

	public static int getHope() {
		return hope;
	}

	public static void setHope(int hope) {
		Character.hope = hope;
	}

	public static void decrementHope(int hopeDeduction) {
		hope = hope - hopeDeduction;
	}

	public static boolean isAlive() {
		return isAlive;
	}

	public static void setIsAlive(boolean isAlive) {
		Character.isAlive = isAlive;
	}

	public static boolean hasSaxophone() {
		return hasSaxophone;
	}

	public static void setHasSaxophone(boolean hasSaxophone) {
		Character.hasSaxophone = hasSaxophone;
	}

	public static int getLocation() {
		return location;
	}

	public static void setLocation(int location) {
		Character.location = location;
	}

	public static boolean hasSuitOn() {
		return hasSuitOn;
	}

	public static void setHasSuitOn(boolean hasSuitOn) {
		Character.hasSuitOn = hasSuitOn;
	}

	public static boolean hasID() {

		return hasID;
	}

	public static void setHasID() {
		Character.hasID = true;
	}

	public static boolean hasLanyard() {

		return hasLanyard;
	}

	public static void setHasLanyard(boolean hasLanyard) {
		Character.hasLanyard = hasLanyard;
	}

	public static boolean hasLeatherJacket() {
		return hasLeatherJacket;
	}
	public static void setHasLeatherJacket() {
		Character.hasLeatherJacket = true;
	}

	public static boolean hasBigIdea() {
		return hasBigIdea;		
	}

	public static void setHasBigIdea(boolean hasBigIdea) {
		Character.hasBigIdea = hasBigIdea;
		
	}

	public static boolean hasRover() {
		return hasRover;	
		
	}

	public static void setHasRover(boolean hasRover) {
		Character.hasRover = hasRover;
		
	}

	public static void setHasDrums(boolean hasDrums) {
		Character.hasDrums = hasDrums;
		
	}
	public static boolean hasDrums() {
		return hasDrums;
		
	}

	public static void setHasCreditCube(boolean hasCreditCube) {
		Character.hasCreditCube = hasCreditCube;
		
	}
	public static boolean hasCreditCube() {
		return hasCreditCube;
		
	}

}

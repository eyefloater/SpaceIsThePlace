package com.gaby.space;

//import com.gaby.space.RoomList.Room;

//there's only one main character, might as well be an abstract class i think
public abstract class Character {

	private static int location;
	private static int hope;

	private static boolean hasSaxophone;
	private static boolean hasPracticed;
	private static boolean isAlive;
	private static boolean hasSuitOn;
	private static boolean hasID;
	private static boolean hasLanyard;
	private static boolean hasLeatherJacket;
	private static boolean hasBigIdea;
	private static boolean hasRover;
	private static boolean hasHugoCredits;
	private static boolean hasDrums;

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

	public static boolean hasPracticed() {
		return hasPracticed;
	}

	public static void setHasPracticed(boolean hasPracticed) {
		Character.hasPracticed = hasPracticed;
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

	public static void setHasLanyard() {
		Character.hasLanyard = true;
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

	public static void setHasBigIdea() {
		Character.hasBigIdea = true;
		
	}

	public static boolean hasRover() {
		return hasRover;	
		
	}

	public static void setHasRover(boolean hasRover) {
		Character.hasRover = hasRover;
		
	}

	public static boolean hasHugoCredits() {
		return hasHugoCredits;
		
	}
	public static void setHasHugoCredits(boolean hasHugoCredits) {
		Character.hasHugoCredits = hasHugoCredits;
		
	}

	public static void setHasDrums(boolean b) {
		Character.hasDrums = hasDrums;
		
	}
	public static boolean hasDrums() {
		return hasDrums;
		
	}


}

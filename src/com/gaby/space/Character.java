package com.gaby.space;

//import com.gaby.space.RoomList.Room;

//there's only one main character, might as well be an abstract class i think
public abstract class Character {

	private static int location;
	private static int hope;

	private static boolean hasSaxophone;
	private static boolean hasBassPlayer;
	private static boolean hasDrummer;
	private static boolean hasPracticed;
	private static boolean isAlive;
	private static boolean hasSuitOn;

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

	public static boolean hasBassPlayer() {
		return hasBassPlayer;
	}

	public static void setHasBassPlayer(boolean hasBassPlayer) {
		Character.hasBassPlayer = hasBassPlayer;
	}

	public boolean hasDrummer() {
		return hasDrummer;
	}

	public static void setHasDrummer(boolean hasDrummer) {
		Character.hasDrummer = hasDrummer;
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

}

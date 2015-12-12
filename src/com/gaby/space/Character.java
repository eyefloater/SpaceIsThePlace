package com.gaby.space;

//import com.gaby.space.RoomList.Room;

//there's only one main character, might as well be an abstract class i think
public abstract class Character {

	//death at 0 health;
	private int health = 5;
	private static int location;

	private static boolean hasSaxophone;
	private static boolean hasBassPlayer;
	private static boolean hasDrummer;
	private static boolean hasPracticed;
	private static boolean isAlive;

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		Character.isAlive = isAlive;
	}

	public boolean isHasSaxophone() {
		return hasSaxophone;
	}

	public void setHasSaxophone(boolean hasSaxophone) {
		Character.hasSaxophone = hasSaxophone;
	}

	public boolean isHasBassPlayer() {
		return hasBassPlayer;
	}

	public void setHasBassPlayer(boolean hasBassPlayer) {
		Character.hasBassPlayer = hasBassPlayer;
	}

	public boolean isHasDrummer() {
		return hasDrummer;
	}

	public static void setHasDrummer(boolean hasDrummer) {
		Character.hasDrummer = hasDrummer;
	}

	public static boolean isHasPracticed() {
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

}

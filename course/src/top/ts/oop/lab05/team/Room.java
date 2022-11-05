package top.ts.oop.lab05.team;

import java.lang.management.ThreadInfo;

class Room {
	private int maxPlayerNum;

	public Room() {}

	public Room(int maxPlayerNum) {
		this.maxPlayerNum = maxPlayerNum;
	}

	public void setMaxPlayerNum(int maxPlayerNum) {
		this.maxPlayerNum = maxPlayerNum;
	}

	public int getMaxPlayerNum() {
		return maxPlayerNum;
	}
}

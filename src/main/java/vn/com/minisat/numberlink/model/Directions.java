package vn.com.minisat.numberlink.model;

public enum Directions {
	UP(0), DOWN(1), LEFT(2), RIGHT(3), ;
	private int value;
	private Directions(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
}

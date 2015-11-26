package vn.com.minisat.numberlink.model;

public enum ConnectivePatterns {
	VERTICAL_LINE(0), UP_AND_LEFT(1), UP_AND_RIGHT(2), DOWN_END_LEFT(3), 
	DOWN_END_RIGHT(4), HORIZONTAL_LINE(5), BLANK(6);
	private int value;
	private ConnectivePatterns(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
}

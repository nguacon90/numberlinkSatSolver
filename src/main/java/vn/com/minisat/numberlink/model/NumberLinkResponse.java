package vn.com.minisat.numberlink.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class NumberLinkResponse implements Serializable{
	private List<List<Cell>> cells;
	private int rowNum;
	private int colNum;
	private long times;
	private boolean isSatisfiable;
	public NumberLinkResponse() {
		cells = new ArrayList<>();
	}
	public List<List<Cell>> getCells() {
		return cells;
	}
	public void setCells(List<List<Cell>> cells) {
		this.cells = cells;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getColNum() {
		return colNum;
	}
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	public boolean isSatisfiable() {
		return isSatisfiable;
	}
	public void setSatisfiable(boolean isSatisfiable) {
		this.isSatisfiable = isSatisfiable;
	}
	public long getTimes() {
		return times;
	}
	public void setTimes(long times) {
		this.times = times;
	}
}

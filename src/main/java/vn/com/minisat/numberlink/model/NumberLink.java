package vn.com.minisat.numberlink.model;

import java.util.ArrayList;
import java.util.List;

public class NumberLink {
	private int rows; // height
	private int cols; //width
	
	private List<List<Integer>> fields;
	
	public NumberLink() {
		setFields(new ArrayList<>());
	}

	public List<List<Integer>> getFields() {
		return fields;
	}

	public void setFields(List<List<Integer>> fields) {
		this.fields = fields;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "NumberLink {fields=" + fields + "}";
	}
}
package vn.com.minisat.numberlink.model;


public class NumberLink {
	private int[][] matrix;
	
	public NumberLink(int x, int y) {
		this.matrix = new int[x][y];
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				stringBuilder.append(matrix[i][j]).append(" ");
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}
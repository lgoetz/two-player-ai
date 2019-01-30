package tictactoe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public abstract class Board implements Iterable<Field>{
	protected Field[][] fields;
	protected int rows;
	protected int cols;
	
	protected abstract Board copyBoard();

	
	public Board(int rows, int cols) {
		fields = new Field[rows][cols];
		this.rows = rows;
		this.cols = cols;

		this.init();
	}
	
	public Board(Field[][] fields) {
		this.fields = fields;
		this.rows = fields.length;
		this.cols = fields[0].length;
	}

	public abstract GameOverEnum gameOver();

	public String view() {
		String s = Board.class.getName();
		s += "(" + rows + "," + cols + ")\n";

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				s += fields[i][j].toString();
				if (j < cols - 1)
					s += "\t";
			}
			if (i < rows - 1)
				s += "\n";
		}
		return s;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (cols != other.cols)
			return false;
		if (rows != other.rows)
			return false;
		if (!Arrays.deepEquals(fields, other.fields))
			return false;
		return true;
	}

	public abstract void init();

	
	public Vector<Board> generatePossibleMoves(PlayerEnum nextPlayer) {
		Vector<Board> moves = new Vector<Board>();

		Board boardClone = this.copyBoard();
		for(Field f : boardClone) {
			if (f.isEmpty()) {
				f.content = nextPlayer;
				moves.add(boardClone.copyBoard());
				f.setEmpty();
			}
		}
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < cols; j++) {
//				if (this.fields[i][j].isEmpty()) {
//					Board boardClone = this.copyBoard();
//					
//					// TODO this only works for tictactoe but not for e.g. chess
//					boardClone.fields[i][j].content = nextPlayer;
//					moves.add(boardClone);
//				}
//			}
//		}
		return moves;
	}
	
	public Iterator<Field> iterator(){
		class BoardIterator implements Iterator<Field>{
			private Field[][] fields;
			private int currentIndex;
			private int rows;
			private int cols;

			public BoardIterator(Field[][] fields, int rows, int cols) {
				this.fields = fields;
				this.currentIndex = 0;
				this.rows = rows;
				this.cols = cols;
			}

			@Override
			public boolean hasNext() {
				return currentIndex < rows*cols;
			}

			@Override
			public Field next() {
				int row = currentIndex/cols;
				int col = currentIndex%cols;
				currentIndex++;
				return this.fields[row][col];
			}
			
		}
		return new BoardIterator(fields, rows, cols);
	}
	
	
	public String scoreHeatmap(double[] scores) {
		String s = "";

		int emptyCounter = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (this.fields[i][j].isEmpty()) {
					s += String.format("%.2f", scores[emptyCounter++]);
				}
				else {
					s += fields[i][j].toString();
				}
				if (j < cols - 1)
					s += "\t";
			}
			if (i < rows - 1)
				s += "\n";
		}
		return s;
	}
		
}

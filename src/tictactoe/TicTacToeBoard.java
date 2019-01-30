package tictactoe;

public class TicTacToeBoard extends Board {
	
	int boardSize;

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public TicTacToeBoard(int boardSize) {
		super(boardSize, boardSize);
		this.boardSize = boardSize;
	}
	
	public TicTacToeBoard(Field[][] fields) {
		super(fields);
	}

	@Override
	public void init() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.fields[i][j] = new Field();
			}
		}
	}

	protected Board copyBoard() {
		Board boardCopy = new TicTacToeBoard(boardSize);
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				boardCopy.fields[i][j].content = this.fields[i][j].content;
			}
		}
		return boardCopy;
	}


	public boolean isWinRow(int row) {
		PlayerEnum first = fields[row][0].content;
		if (first == null) {
			return false;
		}

		for (int col = 1; col < boardSize; col++) {
			if (fields[row][col].content != first) {
				return false;
			}
		}

		return true;
	}
	
	public boolean isWinCol(int col) {
		PlayerEnum first = fields[0][col].content;
		if (first == null) {
			return false;
		}

		for (int row = 1; row < boardSize; row++) {
			if (fields[row][col].content != first) {
				return false;
			}
		}

		return true;
	}
	
	public boolean isWinUpperDiag() {
		PlayerEnum first = fields[0][0].content;
		if (first == null) {
			return false;
		}

		for (int i = 1; i < boardSize; i++) {
			if (fields[i][i].content != first) {
				return false;
			}
		}
		return true;
	}
		
	public boolean isWinLowerDiag() {
		PlayerEnum first = fields[fields.length-1][0].content;
		if (first == null) {
			return false;
		}

		for (int i = 1; i < boardSize; i++) {
			if (fields[fields.length-1-i][i].content != first) {
				return false;
			}
		}

		return true;
	}
	
	public boolean isFull() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (fields[i][j].content == null) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public GameOverEnum gameOver() {
		for (int i = 0; i < boardSize; i++) {
			if (isWinRow(i) || isWinCol(i)) {
				return GameOverEnum.PLAYER_WON;
			}
		}
		if (isWinLowerDiag() || isWinUpperDiag()) {
			return GameOverEnum.PLAYER_WON;
		}
		if (isFull()) {
			return GameOverEnum.DRAW;
		}

		return GameOverEnum.ONGOING;
	}
	
	public int scoreLowerDiag(PlayerEnum nextPlayer) {
		int diagScore = 0;

		for (int i = 1; i < boardSize; i++) {
			if (fields[fields.length-1-i][i].content != nextPlayer & fields[fields.length-1-i][i].content != null) {
				return diagScore;
				}
			else if (fields[fields.length-1-i][i].content == nextPlayer) {
				diagScore += 1;
				}
			}
		return diagScore;
		}

	public int scoreUpperDiag(PlayerEnum nextPlayer) {
		int diagScore = 0;

		for (int i = 1; i < boardSize; i++) {
			if (fields[i][i].content != nextPlayer & fields[i][i].content != null) {
				return diagScore;
				}
			else if (fields[i][i].content == nextPlayer) {
				diagScore += 1;
				}
			}
		return diagScore;

	}		
	
	public int scoreCol(int col, PlayerEnum nextPlayer) {
		int diagScore = 0;

		for (int row = 1; row < boardSize; row++) {
			if (fields[row][col].content != nextPlayer & fields[row][col].content != null) {
				return diagScore;
			}
			else if (fields[row][col].content == nextPlayer) {
				diagScore += 1;
				}
		}

		return diagScore;
	}
	
	public int scoreRow(int row, PlayerEnum nextPlayer) {
		int diagScore = 0;

		for (int col = 1; col < boardSize; col++) {
			if (fields[row][col].content != nextPlayer & fields[row][col].content != null) {
				return diagScore;
			}
			else if (fields[row][col].content == nextPlayer) {
				diagScore += 1;
				}
		}

		return diagScore;
	}


}

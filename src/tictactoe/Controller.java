package tictactoe;

import java.util.Vector;

// 

public class Controller {

	public Controller(Board board, Player p1, Player p2) {
		super();
		this.board = board;
		this.p1 = p1;
		this.p2 = p2;
	}

	public void run() throws Exception {
		PlayerEnum currentPlayer = null;
		GameOverEnum gameOver = board.gameOver();
		while (gameOver == GameOverEnum.ONGOING) {
			currentPlayer = nextPlayer();
			System.out.print("Turn: ");
			System.out.println(currentPlayer);
			System.out.println(board.view());

			Board selectedMove = activePlayer.selectMove(board, currentPlayer);

			// check move validity
			Vector<Board> possibleMoves = board.generatePossibleMoves(currentPlayer);
			assert possibleMoves.size() > 0;
			if (!possibleMoves.contains(selectedMove)) {
				throw new Exception("Invalid move!");
			}
			else {
				board = selectedMove;
			}

			gameOver = board.gameOver();
		}
		System.out.println(board.view());

		if (gameOver == GameOverEnum.PLAYER_WON) {
			System.out.print(currentPlayer);
			System.out.print(" (");
			System.out.print(activePlayer);
			System.out.println(") won!");
		}
		else
			System.out.print("Draw!");
	}

	private PlayerEnum nextPlayer() {
		if (activePlayer == p1)
			activePlayer = p2;
		else if (activePlayer == p2)
			activePlayer = p1;
		else
			activePlayer = p1; // first move

		if (activePlayer == p1)
			return PlayerEnum.PLAYER1;
		else
			return PlayerEnum.PLAYER2;
	}

	protected Board board;
	protected Player p1;
	protected Player p2;
	protected Player activePlayer;
}

package tictactoe;

public class RandomPlayer extends ComputerPlayer{

	public RandomPlayer(int maxDepth) {
		super(maxDepth);
	}

	@Override
	protected double treeSearchEval(Board board, PlayerEnum nextPlayer) {
		return Math.random();
	}
	

}
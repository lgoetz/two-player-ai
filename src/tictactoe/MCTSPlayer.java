package tictactoe;

import java.util.Vector;

public class MCTSPlayer extends ComputerPlayer {
	
	protected long maxTimeMillis;
	
	public MCTSPlayer(long maxTimeMillis) {
		super(Integer.MAX_VALUE);
		
		this.maxTimeMillis = maxTimeMillis;
	}

	@Override
	protected double treeSearchEval(Board board, PlayerEnum nextPlayer) {
		return mcts(board, nextPlayer);
	}

	protected double mcts(Board board, PlayerEnum lastPlayer) {
		moveCounter += 1;
		currentDepth += 1;

		GameOverEnum gameOver = board.gameOver();
		double resultScore;
		
		if (gameOver != GameOverEnum.ONGOING) {
			resultScore = gameOverScore(lastPlayer, gameOver);
			addToDepthHistogram(currentDepth);
		}
		else if (currentDepth >= maxDepth) {
			addToDepthHistogram(currentDepth);
			resultScore = heuristic(board, nextPlayer(lastPlayer));
		} else {
			
			PlayerEnum nextPlayer = nextPlayer(lastPlayer);
			Vector<Board> moves = board.generatePossibleMoves(nextPlayer);

			Board randomMove = moves.get((int)(Math.random()*moves.size()));
			
			resultScore = mcts(randomMove, nextPlayer);
		}
		currentDepth -= 1;
		return resultScore;
	}

	@Override
	protected double[] evaluateMoves(PlayerEnum currentPlayer, Vector<Board> moves) {
		double[] scores = new double[moves.size()];
		double[] counters = new double[moves.size()];

		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis()-startTime < this.maxTimeMillis) {
			Board move = moveCandidate(moves);

			double score = treeSearchEval(move, currentPlayer);
			int index = moves.indexOf(move);
			scores[index] += score;
			counters[index] += 1;
		}
		
		for (int i = 0; i < counters.length; i++) {
			if (counters[i] > 0) {
				scores[i] /= counters[i];
			}
		}
			
		
		return scores;
	}

	protected Board moveCandidate(Vector<Board> moves) {
		Board randomMove = moves.get((int)(Math.random()*moves.size()));
		return randomMove;
	}


}


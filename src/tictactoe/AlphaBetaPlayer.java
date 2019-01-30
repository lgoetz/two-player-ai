package tictactoe;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

public class AlphaBetaPlayer extends ComputerPlayer {
	
	private boolean disablePruning;

	public AlphaBetaPlayer(int maxDepth) {
		super(maxDepth);
		disablePruning = false;
	}
	
	public AlphaBetaPlayer(boolean disablePruning, int maxDepth) {
		super(maxDepth);
		this.disablePruning = disablePruning;
	}
	
	@Override
	protected double treeSearchEval(Board board, PlayerEnum nextPlayer) {
		return alphaBeta(board, nextPlayer, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}
	
	protected double alphaBeta(Board board, PlayerEnum lastPlayer, double alpha, double beta) {
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
			SortedSet<Double> scores = new TreeSet<Double>();

			for (Board move : moves) {
				double score = alphaBeta(move, nextPlayer, alpha, beta);
				scores.add(score);
				
				if (disablePruning)
					continue;
				
				if (nextPlayer == PlayerEnum.PLAYER2) {
					beta = Math.min(beta, Collections.min(scores));
				} else {
					alpha = Math.max(alpha, Collections.max(scores));
				}
				if (alpha >= beta) {
					break;
				}
			}
			
			if (nextPlayer == PlayerEnum.PLAYER2) {
				resultScore = Collections.min(scores);
			} else {
				resultScore = Collections.max(scores);
			}
		}
		currentDepth -= 1;
		return resultScore;
	}




}

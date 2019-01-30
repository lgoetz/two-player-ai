package tictactoe;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ComputerPlayer extends Player {
	protected final double PLAYER1_WIN_SCORE = 1;
	protected final double PLAYER2_WIN_SCORE = -1;
	protected final double DRAW_SCORE = 0;

	long moveCounter;
	int currentDepth;
	Map<Integer, Long> depthHistogram;
	Map<Integer, Double> heatMap;
	protected int maxDepth;

	public ComputerPlayer(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	protected abstract double treeSearchEval(Board board, PlayerEnum nextPlayer);

	@Override
	public Board selectMove(Board board, PlayerEnum currentPlayer) {
		moveCounter = 0;
		currentDepth = 0;
		depthHistogram = new HashMap<Integer, Long>();
		heatMap = new HashMap<Integer, Double>();

		Vector<Board> moves = board.generatePossibleMoves(currentPlayer);
//		Collections.shuffle(moves);
		long startTime = System.currentTimeMillis();
		
		System.out.print("Evaluating ");
		System.out.print(moves.size());
		System.out.print(" moves");
		double[] scores = evaluateMoves(currentPlayer, moves);
		System.out.println();

		// find best rated moves and pick one at random
		int move;
		List<Double> scoresAsList = Arrays.stream(scores).boxed().collect(Collectors.toList());
		double bestScore;
		if (currentPlayer == PlayerEnum.PLAYER1) {
			bestScore = Collections.max(scoresAsList);
			
		} else
			bestScore = Collections.min(scoresAsList);
		List<Integer> allIndexes = IntStream.range(0, scores.length).boxed().filter(i -> scores[i]==bestScore)
				.collect(Collectors.toList());
		move = allIndexes.get((int)(Math.random()*allIndexes.size()));
		long endTime = System.currentTimeMillis();

		System.out.print(this.getClass().getName());
		System.out.print(" move took ");
		System.out.print(endTime - startTime);
		System.out.print("ms to evaluate ");
		System.out.print(moveCounter);
		System.out.println(" positions.");
		System.out.print("Simulated ");
		System.out.print(getNumGamesPlayed());
		System.out.print(" games with depths: ");
		System.out.println(depthHistogram);

		System.out.print("Score heatmap for ");
		System.out.println(currentPlayer);
		System.out.println(board.scoreHeatmap(scores));
		return moves.get(move);

	}

	protected double[] evaluateMoves(PlayerEnum currentPlayer, Vector<Board> moves) {
		double[] scores = new double[moves.size()];


		moves.stream().forEach((move) -> {
			System.out.print(".");
			double score = treeSearchEval(move, currentPlayer);
			scores[moves.indexOf(move)] = score;
		});
		return scores;
	}


	protected PlayerEnum nextPlayer(PlayerEnum lastPlayer) {
		PlayerEnum nextPlayer;
		if (lastPlayer == PlayerEnum.PLAYER1)
			nextPlayer = PlayerEnum.PLAYER2;
		else
			nextPlayer = PlayerEnum.PLAYER1;
		return nextPlayer;
	}

	protected double gameOverScore(PlayerEnum lastPlayer, GameOverEnum gameOver) {
		assert gameOver != GameOverEnum.ONGOING;

		if (gameOver == GameOverEnum.DRAW) {
			return DRAW_SCORE;
		} else {
			if (lastPlayer == PlayerEnum.PLAYER1)
				return PLAYER1_WIN_SCORE;
			else
				return PLAYER2_WIN_SCORE;
		}
	}

	private long getNumGamesPlayed() {
		long result = 0;
		for (Long value : depthHistogram.values()) {
			result += value;
		}
		return result;
	}

	protected void addToDepthHistogram(int depth) {
		Long lastVal = depthHistogram.get(depth);
		if (lastVal == null) {
			lastVal = Long.valueOf(0);
		}
		depthHistogram.put(depth, lastVal + 1);
	}

	public long getMoveCounter() {
		return moveCounter;
	}

//	public double heuristic(Board board, PlayerEnum nextPlayer) {
//		if (board instanceof TicTacToeBoard) {
//			return 0;
//		}
//		assert false;
//		return 0;
//	}
	
	public double heuristic(Board board, PlayerEnum nextPlayer) {
		int stateScore = 0;
		
		if (board instanceof TicTacToeBoard) {
			TicTacToeBoard castedBoard = (TicTacToeBoard) board;
			for (int i = 0; i < castedBoard.boardSize; i++) {
				stateScore += castedBoard.scoreRow(i, nextPlayer);
				stateScore += castedBoard.scoreCol(i, nextPlayer);
			}
			stateScore += castedBoard.scoreLowerDiag(nextPlayer);
			stateScore += castedBoard.scoreUpperDiag(nextPlayer);
		}
		return stateScore;
	}
	
	

	

}

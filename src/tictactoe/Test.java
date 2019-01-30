package tictactoe;


public class Test {

	public static void main(String[] args) {
	
		Board b = new TicTacToeBoard(4);
		
		Player p1 = new AlphaBetaPlayer(9);
//		Player p2 = new AlphaBetaPlayer(7);

		Player p2 = new MCTSPlayer(2000);
//		Player p1 = new MiniMaxPlayer();
//		Player p2 = new MiniMaxPlayer();
		Controller c = new Controller(b, p1, p2);
		try {
			c.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}

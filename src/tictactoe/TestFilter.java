package tictactoe;

import java.util.Random;

public class TestFilter {

	public static void main(String[] args) {

		// generate a big array (aka board) and a small array (aka filter)
		int rows = 5;
		int cols = 5;

		Long[][] big = new Long[rows][cols];
		Long[][] small = new Long[1][4];
		Long empty_val = 0l;

		Long[] board_vals = new Long[3];
		board_vals[0] = 0l;
		board_vals[1] = 1l;
		board_vals[2] = 2l;

		System.out.println("Board:");
		int seed = 23;
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				Random randomGenerator = new Random(seed);
				seed += 1;
				big[i][j] = board_vals[randomGenerator.nextInt(3)];
				System.out.print(big[i][j]);
				System.out.print('.');
			}
			System.out.println();
		}

		small[0][0] = 0l;
		small[0][1] = 1l;
		small[0][2] = 2l;
		small[0][3] = 0l;

		int small_rows = small.length;
		int small_cols = small[0].length;

		System.out.println("Filter:");
		for (int i=0; i<small_rows; i++) {
			for (int j=0; j<small_cols; j++) {
				System.out.print(small[i][j]);
				System.out.print('.');
			}
			System.out.println();
		}

		int count=-1;
			SingleFilter<Long> f = new SingleFilter<Long>(small, empty_val);
			try {
				count = f.apply(big);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Count:");
				System.out.println(count);
					
		}
		}




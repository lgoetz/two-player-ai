package tictactoe;

import java.util.Random;
import java.util.Vector;

public class TestMultiFilter {

	public static void main(String[] args) {

		// generate a big array (aka board)
		int rows = 5;
		int cols = 5;

		Long[][] big = new Long[rows][cols];

		Long[] board_vals = new Long[3];
		board_vals[0] = 0l;
		board_vals[1] = 1l;
		board_vals[2] = 2l;

		// print board
		System.out.println("Board:");
		int seed = 23;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Random randomGenerator = new Random(seed);
				seed += 1;
				big[i][j] = board_vals[randomGenerator.nextInt(3)];
				System.out.print(big[i][j]);
				System.out.print('.');
			}
			System.out.println();
		}

		// generate a vector of several small filters
		Vector<Filter<Long>> filters = new Vector<Filter<Long>>();
		int n_filters = 4;
		
		for (int i = 0; i < n_filters; i++) {
			Random randomGenerator = new Random(seed);
			seed += 1;
			
			Long[][] small = new Long[1][4];
			for (int row = 0; row < small.length; row++)
			{
				for (int col = 0; col < small[row].length; col++)
				{
					small[row][col]= (long) randomGenerator.nextInt(3);
				}
			}

			Filter<Long> filter = new SingleFilter<Long>(small, 0l);
			System.out.println(filter);
			filters.addElement(filter);
		}
		
		
		// hand filters to MultiFilter
		int count = 0;
		MultiFilter<Long> f = new MultiFilter<Long>(filters);
		try {
			count = f.apply(big);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Count:");
		System.out.println(count);

	}
}

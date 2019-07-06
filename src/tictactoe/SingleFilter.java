package tictactoe;

public class SingleFilter<T> implements Filter<T>{
	
	private T[][] filter;
	private T empty;

	public SingleFilter(T[][] filter, T empty) {
		this.filter = filter;
		this.empty = empty;
	}
	
	public String toString()
	{
		String output = "";
		
		for (int row = 0; row < filter.length; row++)
		{
			int num_cols = filter[row].length;
			for (int col = 0; col < num_cols; col++)
			{
				output += filter[row][col].toString();
				if (col < num_cols-1)
					output += ",";
			}
			if (row < filter.length-2)
				output += "\n";
			
		}
		
		return output;
	}
	
	public int apply(T[][] array) throws Exception{		

		int board_rows = array.length;
		int board_cols = array[0].length;
		
		int counter = 0;
		
			
		int filter_rows = filter.length;
		int filter_cols = filter[0].length;
		
		boolean filter_empty = true;
		for (int k=0; k < filter_rows; k++) {
			for (int l=0; l < filter_cols; l++) {
				if (filter[k][l] != empty) {
					filter_empty = false;
				}
			}
		}
		if (filter_empty)
			throw new Exception("Filter must not be empty");
		
		assert !filter_empty;
		
		assert filter_rows <= board_rows;
		assert filter_cols <= board_cols;
		
		
		for (int i=0; i <= board_rows-filter_rows; i++) {
			for (int j=0; j <= board_cols-filter_cols; j++) {
				
				
				boolean equal = true;
				for (int k=0; k < filter_rows; k++) {
					for (int l=0; l < filter_cols; l++) {
						if (filter[k][l] != empty) {
							if (array[i+k][j+l] != filter[k][l]) {
								equal = false;
							}
			
						}
					}
				}
				
				if (equal) {
					counter++;
				}
			}
		}

		
		return counter;
	}
}



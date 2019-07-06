package tictactoe;

import java.util.Vector;

public class MultiFilter<T> implements Filter<T>{
	
	private Vector<Filter<T>> filters;

	public MultiFilter(Vector<Filter<T>> filters) {
		this.filters = filters;
	}
	
	public int apply(T[][] array) throws Exception{		
		
		int counter = 0;
		for (Filter<T> f:filters) {
			 int count = f.apply(array);
			 counter += count;
		 }
		return counter;
	}
	
}



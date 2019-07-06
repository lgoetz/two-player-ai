package tictactoe;

public interface Filter<T> {
	
	public int apply(T[][] array) throws Exception;
}


package tictactoe;

public class Field {
	
	protected PlayerEnum content;

	public Field() {
		content = null; // Field is empty
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (content != other.content)
			return false;
		return true;
	}

	public Field(PlayerEnum content) {
		this.content = content;
	}
	
	public String toString() {
		if (content == null) {
			return ".";
		}
		return content.toString();
	}
	
	public boolean isEmpty() {
		return content == null; 
	}
	
	public void setEmpty() {
			content = null; 
	}

	
}

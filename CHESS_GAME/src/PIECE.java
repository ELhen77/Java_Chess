
public abstract class PIECE {
	
	public abstract void move();
	
	public char piece_char = ' '; 
	
	public void setChar(char c){
		piece_char = c;
	};
	
	public char getChar(){
		return piece_char;
	}
	
	public boolean playerOne = false;
	
	public boolean flipped = false;
	
	int x = 0;
	int y = 0;

	public boolean hasntmoved = true;

}

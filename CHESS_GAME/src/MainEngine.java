
public class MainEngine {
	public static void main(String[] args) {
		
		Board bd = new Board();
		
		bd.CreateBoard();
		
		
		
		PIECE P = new Pawn();

		//bd.MOVE_THIS(1, 7); 
		//bd.MOVE_TO( bd.Piece_board[1][7], 3 ,4);
		Board B = new Board();
		GAME_ENGINE GE = new GAME_ENGINE(B);
		System.out.println( );
		GE.STARTGAME();
	
		
	
	}

}

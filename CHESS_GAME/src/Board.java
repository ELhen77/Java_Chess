import java.util.ArrayList;
public class Board {
	
	static boolean GameProgress ;
	public GAME_ENGINE G_E;
	public boolean firstRound = true;

	//DECIDED TO USE ENUMS INSTEAD FOR BOARD, 
	//enums were a giant mistake, enums are statically defined classes, All Pawn enums share teh same address. each one mutates the other ones
	public enum Pieces {
		
		Pawn('P'), Rook('R'), Bishop('B'), Knight('H'), Queen('Q') , King('K');
		char ch;
		
		int x = 0;
		int y = 0;
		
		boolean playerOne = false;
		
		Pieces(char c){
			ch = c;
		}

	}
	
	public PIECE[][] Piece_board = new PIECE[8][8];

	public boolean PLAYERONESTATE = false;
	
	public char[][] board = new char[8][8];
	
	//constructor for board
	public Board(){
		
		for(int i = 0; i < (8); i++)
			for(int j = 0; j < (8) ; j++){
				Piece_board[i][j] = null;
			}
		
		//Create each object individually , each object has there own address, each one can be altered and not affect the others 
		/** 
 		Pawn p = new Pawn();
		p.playerOne = false;
		for(int i = 0; i < 8; i++)
	    Piece_board[6][i] = p;
		  **/
		//error with code snippet creating only one pawn all share that VM address. each one alters each other
		//pawn 1 
		Pawn P1 = new Pawn();
		P1.playerOne = true;
		Piece_board[1][0] = P1;
		//pawn 2
		Pawn P2 = new Pawn();
		P2.playerOne = true;
		Piece_board[1][1] = P2;
		//pawn 3
		Pawn P3 = new Pawn();
		P3.playerOne = true;
		Piece_board[1][2] = P3;
		//pawn 4
		Pawn P4 = new Pawn();
		P4.playerOne = true;
		Piece_board[1][3] = P4;
		//pawn 5
		Pawn P5 = new Pawn();
		P5.playerOne = true;
		Piece_board[1][4] = P5;
		//pawn 6
		Pawn P6 = new Pawn();
		P6.playerOne = true;
		Piece_board[1][5] = P6;
		//pawn 7
		Pawn P7 = new Pawn();
		P7.playerOne = true;
		Piece_board[1][6] = P7;
		//pawn 8
		Pawn P8 = new Pawn();
		P8.playerOne = true;
		Piece_board[1][7] = P8;
		
	
		//player two pieces
		Pawn p1 = new Pawn();
		Piece_board[6][0] = p1;
		//pawn 2
		Pawn p2 = new Pawn();
		Piece_board[6][1] = p2;
		//pawn 3
		Pawn p3 = new Pawn();
		Piece_board[2][2] = p3;
		//pawn 4
		Pawn p4 = new Pawn();
		Piece_board[6][3] = p4;
		//pawn 5
		Pawn p5 = new Pawn();
		Piece_board[6][4] = p5;
		//pawn 6
		Pawn p6 = new Pawn();
		Piece_board[6][5] = p6;
		//pawn 7
		Pawn p7 = new Pawn();
		Piece_board[6][6] = p7;
		//pawn 8
		Pawn p8 = new Pawn();
		Piece_board[6][7] = p8;
		
		
		
	}
	
	public void CreateBoard(){
		
		for(int i = 0; i < (8); i++)
			for(int j = 0; j < (8) ; j++){
				if(Piece_board[i][j] == null)
					board[i][j] = '*';
				else if(Piece_board[i][j].playerOne == true)
						board[i][j] = Character.toLowerCase(Piece_board[i][j].getChar());
					else
						board[i][j] = Piece_board[i][j].getChar();
			}
	}
	

	//MOVE METHOD SELECTS THE PIECE YOU WANT TO MOVE
	public boolean MOVE_THIS(char x, int Y){
		//check to see if you have proper coordinates
		int X = x - 97;
		Y = 8 - Y;
		if(X < 0 || X > 8 || Y > 8 || Y < 0){
			System.out.println("Improper Cooridantes");
			return false;
		}
		else if(Piece_board[Y][X] == null){
			System.out.println("There is not a piece there");
			return false;
		}
		else if(G_E.PLAYERONESTATE == true && Piece_board[Y][X].playerOne == false){
			System.out.println("PLAYER TWO CANNOT CONTROL PLAYER ONE's PIECE");
			return false;
		}
		else if(G_E.PLAYERONESTATE == false && Piece_board[Y][X].playerOne == true){
			System.out.println("PLAYER ONE CANNOT CONTROL PLAYER TWO's PIECE");
			return false;
		}
		
		
		Piece_board[Y][X].x = X;
		Piece_board[Y][X].y = Y;
		/**
		System.out.println(Piece_board[Y][X].x);
		System.out.println(Piece_board[Y][X].y);
		**/ 
		return true;
	}
	
	public boolean MOVE_TO(PIECE piece, char x, int Y){
		
		//TO ACCESS THIS METHOD YOU HAVE CHOOSEN THE PIECE THAT IS TO BE MOVED
		//THIS ELIMENATES YOU MOVING AN OPPONENTS PIECE
		
		
		int X = x - 97;
		Y = 8 - Y;
		
		if(X < 0 || X > 8 || Y > 8 || Y < 0){
			System.out.print("Improper Cooridantes");
			return false;
		}
		
		//FORCED WHILE CATCH LOOP, WILL NOT CONTINUE UNTIL YOU MAKE A VALID MOVE CHOICE
		boolean valid = Specific_Piece_Move(piece, Y, X);
		return valid;
		
	}
	
	//refactored created one gaint method with diff vars that determine movement range i.e moving first or flipped
	public boolean Specific_Piece_Move(PIECE piece, int X, int Y){
		boolean validChoice = false;
		
		
		
		int Xcor = piece.x;
		int Ycor = piece.y;
		PIECE P = piece;
		//break down into special methods i.e MOVEPAWN(PIECE , X ,Y)
		if(piece == P)
			validChoice = MovePawn(piece, X , Y);
		
		if(validChoice == true)
			MoveGeneral( piece, X,  Y);
		
		return validChoice;
	}
	
	// make each possible movement  avaible move more modular, no overlapping logic, break down every possible movement 
	private boolean MovePawn(PIECE piece, int X, int Y) {
		boolean valid = false;
		
		int hasMoved;
		if(piece.hasntmoved == true)
			hasMoved = 1;
		else
			hasMoved = 0;
		
		int playerOnesTurn;
		if(piece.playerOne == true)
			playerOnesTurn = 1;
		else
			playerOnesTurn = -1;
		
		ChessCor MovCoor = new ChessCor(X,Y);
		ChessCor listofValidPos[] = new ChessCor[4];
		
		boolean PieceInWay = false;
		int Xcor = piece.x;
		int Ycor = piece.y;
		
		//check to see if anything is in front of you
		if(Piece_board[Ycor + playerOnesTurn*(1)][Xcor] == null){
			listofValidPos[0] = new ChessCor(  (Ycor + playerOnesTurn*(1)) ,Xcor );
		}
		else
			PieceInWay = true;
		//if no piece is in the way you can move a second time if you have already moved, hasmoved is zero so you get the same coor agian 
		if(PieceInWay ==  false){
			if(Piece_board[Ycor + playerOnesTurn*(1 + hasMoved)][Xcor] == null){
				listofValidPos[1] = new ChessCor( (Ycor + playerOnesTurn*(1 + hasMoved)) , Xcor);
			}
		}
		
		
		int newYcor = Ycor + playerOnesTurn*1;
		int newXcor = Xcor + playerOnesTurn*1;
				
		System.out.println("This coor is " +  (newYcor) + " , " + newXcor );
		boolean notNull = false;
		boolean  isplayerNotMatching  = false;
		
		try{
		
		 notNull = Piece_board[Ycor + playerOnesTurn*1][Xcor + playerOnesTurn*1] != null; 
		
		 isplayerNotMatching = Piece_board[Ycor + playerOnesTurn*1][Xcor + playerOnesTurn*1].playerOne != Piece_board[Ycor][Xcor].playerOne; 
		
		System.out.println(" is not NotNull true : " + notNull);
		System.out.println(" Are the pieces not matching  : " + isplayerNotMatching);
		System.out.println("------------------------------------------------------------");
		}
		catch(NullPointerException exp){
			System.out.println(" is not NotNull true : " + notNull);
		}
		catch(ArrayIndexOutOfBoundsException ex ){
			;
		}
		
		
		try{
		//check piece is to the upper right, make sure is not null and the piece is not the same as the current moving  piece 
		if( notNull && isplayerNotMatching)
				listofValidPos[2] = new ChessCor( (Ycor + playerOnesTurn*(1)) , Xcor + playerOnesTurn*1);
		}
		catch(ArrayIndexOutOfBoundsException ex){
			;
			
		}
		
		
		 newYcor = Ycor + playerOnesTurn*1;
		 newXcor = Xcor - playerOnesTurn*1;
				
		System.out.println("This coor is " +  (newYcor) + " , " + newXcor );
		
		notNull = false;
		isplayerNotMatching = false;
		
		try{
	    notNull = Piece_board[Ycor + playerOnesTurn*1][Xcor - playerOnesTurn*1] != null; 
		
	    isplayerNotMatching = Piece_board[Ycor + playerOnesTurn*1][Xcor - playerOnesTurn*1].playerOne != Piece_board[Ycor][Xcor].playerOne; 
		
		System.out.println(" is not NotNull true : " + notNull);
		System.out.println(" Are the pieces not matching  : " + isplayerNotMatching);
		System.out.println("------------------------------------------------------------");}
		catch(NullPointerException exp ){
			System.out.println(" is not NotNull true : " + notNull);
		}
		catch(ArrayIndexOutOfBoundsException ex ){
			;
		}
		
		try{
		//check piece is to the upper right, make sure is not null and the piece is not the same as the current moving  piece 
		if(notNull && isplayerNotMatching)
				listofValidPos[3] = new ChessCor( (Ycor + playerOnesTurn*(1)) , Xcor - playerOnesTurn*1);
		}
		catch(ArrayIndexOutOfBoundsException ex){
			;
		}
		
		//check all positions on board to match up with original coor
		for(ChessCor chsscr : listofValidPos)
			if(MovCoor.compare(chsscr) == true){
				piece.hasntmoved = false;
				return true;
			}
		
		
		
		return false;
	}

	public boolean MoveGeneral(PIECE piece, int X, int Y){
	
		PIECE P = piece;
	    int Xcor = piece.x;
	    int Ycor = piece.y;
		Piece_board[X][Y]  = P;
		Piece_board[X][Y].y = Y;
		Piece_board[X][Y].x = X;
		Piece_board[Ycor][Xcor] = null;
		return true;
		
	}
	
	 
	
	public PIECE Get_Piece(int x , int Y){
		int X = x - 97;
		Y = 8 - Y;
		String piece;
		PIECE p = new Pawn();
		
		if(X < 0 || X > 8 || Y > 8 || Y < 0){
			System.out.println("Improper Cooridantes");
			return null;
		}
		else
			  p = Piece_board[Y][X];
			  piece = " " + Piece_board[Y][X];
			
			 if(piece.contains("Pawn"))
				 return p;
			return null;
	}
	
	
	
	
	public String toString(){
		
		String returnString = "  ABCDEFGH\n";
		 //returnString += "\n";
		int z = 8;
		for(int i = 0; i < 8 ; i++){
		
			for(int j = 0; j < 8; j++){
				
				if(j == 0){
					returnString += z + " ";
					z--;
				}
				returnString += board[i][j];
				
				if(j == 7)
					returnString +=  " " + (z + 1) + "\n";	
				
			}
		}
		
		returnString += "  ABCDEFGH\n";
		
		return returnString;
	}

	
	

}

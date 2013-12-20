
public class Board {
	
	static boolean GameProgress ;
	public GAME_ENGINE G_E;
	public boolean firstRound = true;

	//DECIDED TO USE ENUMS INSTEAD FOR BOARD, 
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
		
	
	
		Pawn p = new Pawn();
		
		p.playerOne = false;
	
	
		
		for(int i = 0; i < 8; i++)
			Piece_board[6][i] = p;
		
		
		
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
	
	
	public boolean Specific_Piece_Move(PIECE piece, int X, int Y){
		boolean validChoice = false;
		int Xcor = 0;
		int Ycor = 0;
		PIECE P = piece;
		//break down into special methods i.e MOVEPAWN(PIECE , X ,Y)
		if(piece == P){
			Xcor = piece.x;
			Ycor = piece.y;
				if(piece.hasntmoved == true){
					piece.hasntmoved = false;
					if(X- Ycor <= 2 &&  X- Ycor >= 0){
						validChoice = MovePawn(P,X,Y);
					}
					else if(Ycor  - X <= 2 && Ycor - X >= 0){
						validChoice = MovePawn(P,X,Y);
					}	
				}
		}
		return validChoice;
	}
	
	public boolean MovePawn(PIECE piece, int X, int Y){
		int Xcor = 0;
		int Ycor = 0;
		PIECE P = piece;
		//break down into special methods i.e MOVEPAWN(PIECE , X ,Y)
	    Xcor = piece.x;
	    Ycor = piece.y;
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

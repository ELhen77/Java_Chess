import java.io.*;
import java.util.Scanner;

public class GAME_ENGINE {
	
	int rounds = 1;
	int counter = 1;
	Scanner scanner = new Scanner(System.in);
	
	char inputX;
	int inputY;
	
	public  boolean PLAYERONESTATE = true;
	
	public boolean LEGIT_MOVE = false;
	
	public Board baord;
	
	public GAME_ENGINE(Board board){
		baord = board;
		baord.G_E = this;
	}
	
	public void STARTGAME(){
	
		baord.CreateBoard();
	
		while(rounds < 5){
			
			System.out.println(baord);
			
			while(!LEGIT_MOVE){
				System.out.println(" PICK A PIECE TO MOVE");
				inputX = scanner.next().charAt(0);
				inputY = scanner.nextInt();
				LEGIT_MOVE = baord.MOVE_THIS(inputX , inputY);
				
				System.out.println("Moving the " + baord.Get_Piece(inputX , inputY) + " at " + inputX +"," +inputY );
			}

			PIECE piece = baord.Get_Piece(inputX, inputY);
			
			LEGIT_MOVE = false;
	
			while(!LEGIT_MOVE){
				System.out.println(" WHERE DO YOU WANT TO MOVE THIS PIECE");
				inputX = scanner.next().charAt(0);
				inputY = scanner.nextInt();
				if(inputX == 'q')
					break;
				LEGIT_MOVE = baord.MOVE_TO(piece, inputX, inputY);	
			}
		
		 if (inputX != 'q'){
				
				baord.CreateBoard();
				System.out.print(baord);
				
				if(counter%2 == 0)
					rounds++;
				
				counter++;
				PLAYERONESTATE = !PLAYERONESTATE;
				LEGIT_MOVE = false;
			}
	}

  }
}

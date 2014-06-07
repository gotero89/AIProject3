package fourinaline;

import java.util.Scanner;

public class MainGame {
	

	public static void main(String[] args) {
		
		
		System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
		System.out.println("     Welcome to  4-In-A-Line     ");
		System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
		
		System.out.println("\n1. Play Game");
		System.out.println("2. Quit");
		
		System.out.print("\nEnter your choice: ");
		Scanner scanner = new Scanner(System.in);
		
		int choice = scanner.nextInt();
		
		switch(choice) {
		//User wants to play a game
		case 1:
			Board board = new Board(); //set up the board
			Player player = new Player(board); //set up the player
		
			
			//TESTING AI
			Computer computer = new Computer(board, false, 10);
						
			Scanner sc = new Scanner(System.in);
			boolean invalidMove = false;
			
			//Start playing game until there is a winner
			while(!board.isWinner()) {
				
				board.printBoard();
				board.checkWinner();
				
				if(board.isWinner())
					break;
				
				System.out.print("\nEnter your move: ");
				invalidMove = player.makeMove(sc.next());
				
				board.checkWinner();
				if(board.isWinner())
					break;
				
				while(!invalidMove) {
					board.printBoard();
					System.out.print("\nEnter your move: ");
					invalidMove = player.makeMove(sc.next());
					board.checkWinner();
					if(board.isWinner())
						break;
				}
				
				board.checkWinner();
				
				if(board.isWinner())
					break;
				
				
				//Computer makes a move now
				computer.makeMove();
				
				board.checkWinner();
				
			
				if(board.isWinner())
					break;
				
			}
			
			board.printBoard();
			
			if(board.isWinnerComputer()) {
				System.out.println("\nComputer won");
			}
			
			else if(board.isWinnerPlayer()) {
				System.out.println("\nPlayer won");
			}
			
			break;
		//Quit the game
		case 2:
			System.exit(0);
			break;
			default:
				System.exit(0);
		}
		
	}

}

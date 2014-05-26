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
						
			Scanner sc = new Scanner(System.in);
			
			boolean invalidMove = true;
			
			while(invalidMove) {
				board.printBoard();
				System.out.print("\nEnter your move: ");
				String s = sc.next();
				if(player.makeMove(s)) {
					invalidMove = false;
				}
			}
				
			invalidMove = true;
			
			while(invalidMove) {
				board.printBoard();
				System.out.print("\nEnter your move: ");
				String s = sc.next();
				if(player.makeMove(s)) {
					invalidMove = false;
				}
			}
			
			
			
		
			board.printBoard();
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

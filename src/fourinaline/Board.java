package fourinaline;

public class Board {
	
	final int BOARD_SIZE = 8; //Size of board is constant
	boolean winner; //To determine if there is winner
	String[][] board; //2-D array to represent the board
	
	
	//Constructor initializes the board
	//winner = false because there is no winner yet
	public Board() {
		
		//Set winner to false initially
		winner = false;
		
		//Initialize the board
		board = new String[BOARD_SIZE][BOARD_SIZE];
		
		//Fill out the board with empty dashes
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j] = "-";
			}
			
		}
			
	}
	
	/*
	 * This method will check if there is winner
	 * yet, if there is, the game will be terminated
	 */
	
	public void checkWinner() {
		
	}
	
	//This method will print the board
	public void printBoard() {
		
		int charValue = 65;
		
		System.out.println("");
		System.out.println("  1 2 3 4 5 6 7 8");
		for(int i = 0; i < board.length; i++) {
			System.out.print((char)charValue++ + " ");
			for(int j = 0; j < board.length; j++) {
				System.out.print("-" + " ");
			}
			
			System.out.print("\n");
		}
		
	}
	

}

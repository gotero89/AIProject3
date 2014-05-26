package fourinaline;

public class Board {
	
	final int BOARD_SIZE = 8; //Size of board is constant
	boolean winner; //To determine if there is winner
	String[][] board; //2-D array to represent the board
	boolean winnerComputer;
	boolean winnerPlayer;
	
	
	//Constructor initializes the board
	//winner = false because there is no winner yet
	public Board() {
		
		//Set winner to false initially
		winner = false;
		winnerComputer = false;
		winnerPlayer = false;
		
		
		//Initialize the board
		board = new String[BOARD_SIZE][BOARD_SIZE];
		
		//Fill out the board with empty dashes
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j] = "-";
			}
			
		}
		
	}
	
	public boolean isWinnerComputer() {
		return winnerComputer;
	}
	
	public boolean isWinnerPlayer() {
		return winnerPlayer;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	/*
	 * This method will check if there is winner
	 * yet, if there is, the game will be terminated
	 */
	
	public void checkWinner() {
		//Set winner = true if there is a winner
		//to stop the game
		int countX = 0;
		int countO = 0;
		
		//Traverse the board and check if there are 4 in a line
		for(int i = 0; i < board.length; i++) {
			
			for(int j = 0; j < board.length; j++) {
				
				//Found placed piece
				if(board[i][j].equals("X")) {
					countX++;
					//Check horizontal win
					for(int k = j; k < (j+4); k++) {
						if(board[i][k].equals("X"))
							countX++;
					}
					
					if(countX == 4) {
						winner = true; //found a winner
						winnerPlayer = true;
					}
					
				}
				
				
			}
			
		}
	}
	
	//Will set a piece on the board
	public boolean setPiece(String p, int r, int c) {
		
		boolean pieceWasSet = false;

		if(board[r][c].equals("-")) {
			board[r][c] = p;
			pieceWasSet = true;
		}
		
		return pieceWasSet;
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
	
	public static void main(String[] args) {
		
		
		
	}
	

}

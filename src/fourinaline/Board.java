package fourinaline;

import java.util.ArrayList;

public class Board {
	
	final int BOARD_SIZE = 8; //Size of board is constant
	private boolean winner; //To determine if there is winner
	String[][] board; //2-D array to represent the board
	private boolean winnerComputer;
	private boolean winnerPlayer;
	private int boardScore;
	final String PLAYER_PIECE = "O";
	final String COMPUTER_PIECE = "X";
	private ArrayList<Board> childrenBoards;
	private Board parentBoard;
	private int[] newMove;
	
	
	//Constructor initializes the board
	//winner = false because there is no winner yet
	public Board() {
		
		//Set winner to false initially
		winner = false;
		winnerComputer = false;
		winnerPlayer = false;
		
		
		//Initialize the board
		board = new String[BOARD_SIZE][BOARD_SIZE];
		
		newMove = new int[2];
		
		//Fill out the board with empty dashes
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j] = "-";
			}
			
		}
		
	}
	
	
	
	/*
	 * *******************
	 * This method creates a deep clone of a board
	 * *********************
	 */
	public Board cloneBoard() {
		
		Board board  = new Board();		
		for(int i = 0; i < this.board.length; i++) {
			for(int j = 0; j < this.board.length; j++) {
				board.board[i][j] = this.board[i][j];
			}
		}
		
		return board;
		
	}
	
	/*
	 * Generate the succesor nodes
	 * for current board
	 */
	
	public ArrayList<Board> getChildrenBoards() {
		return childrenBoards;
	}
	
	
	public void setChildrenBoard(ArrayList<Board> boards) {
		childrenBoards = boards;
	}
	
	public void setParentBoard(Board parentBoard) {
		this.parentBoard = parentBoard;
	}
	
	public Board getParentBoard() {
		return parentBoard;
	}
	
	
	public int getBoardScore() {
		return boardScore;
	}
	
	public void setBoardScore(int boardScore) {
		this.boardScore = boardScore;
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
	
	public ArrayList<Board> generateSuccesors(Board b, String piece) {
		
		ArrayList<Board> temp = new ArrayList<Board>();
		
		for(int i = 0; i < board.length; i++) {
			
			for(int j = 0; j < board.length; j++) {
				
				if(board[i][j].equals("O")) {
					if(isIndexValid(i+1,j)) {
						Board tempBoard = cloneBoard();
						tempBoard.setPiece(piece, i+1, j);
						tempBoard.setNewMove(i+1, j);
						temp.add(tempBoard);
					}
					
					else if(isIndexValid(i, j+1)) {
						
						Board tempBoard = cloneBoard();
						tempBoard.setPiece(piece, i, j+1);
						tempBoard.setNewMove(i, j+1);
						temp.add(tempBoard);
						
						
					}
					
					else if(isIndexValid(i,j-1)) {
						
						Board tempBoard = cloneBoard();
						tempBoard.setPiece(piece, i, j-1);
						tempBoard.setNewMove(i, j-1);
						temp.add(tempBoard);
						
						
					}
					
					else if(isIndexValid(i-1,j)) {
						
						Board tempBoard = cloneBoard();
						tempBoard.setPiece(piece, i-1, j);
						tempBoard.setNewMove(i-1, j);
						temp.add(tempBoard);
						
					}
					
				}
						
			}
			
			
		}
		
		
		return temp;
	}
	
	public void setNewMove(int row, int col) {
		newMove[0] = row;
		newMove[1] = col;
 	}
	
	public int[] getNewMove() {
		return newMove;
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
		
		//CHECK HORIZONTAL WIN
		for(int i = 0; i < board.length; i++) {
			
			for(int j = 0; j <= board.length-4; j++) {
				
				
				if(board[i][j].equals("X")) {
					if(board[i][j+1].equals(COMPUTER_PIECE) 
							&& board[i][j+2].equals(COMPUTER_PIECE) && board[i][j+3].equals(COMPUTER_PIECE)) {
						System.out.println("FOUR IN A LINE DETECTED");
						winnerComputer = true;
						winner = true;
						return;
					}
				}
				
				else if(board[i][j].equals("O")) {
					
					if(board[i][j+1].equals("O") 
							&& board[i][j+2].equals("O") && board[i][j+3].equals("O")) {
						System.out.println("FOUR IN A LINE DETECTED");
						winnerPlayer = true;
						winner = true;
						return;
					}
					
				}
			}
		}
				
				
		//CHECK FOR VERTICAL WIN
		for(int i = 0; i <= board.length-4; i++) {
			
			for(int j = 0; j < board.length; j++) {
				
			  if(board[i][j].equals("O"))
				if(board[i+1][j].equals(PLAYER_PIECE) && board[i+2][j].equals(PLAYER_PIECE) 
						&& board[i+3][j].equals(PLAYER_PIECE)) {
					System.out.println("FOUR IN A LINE DETECTED");
					winnerPlayer = true;
					winner = true;
					return;
				}
				
				else if(board[i][j].equals("X")) {
					if(board[i+1][j].equals(COMPUTER_PIECE) && board[i+2][j].equals(COMPUTER_PIECE) 
							&& board[i+3][j].equals(COMPUTER_PIECE)) {
						System.out.println("FOUR IN A LINE DETECTED");
						winnerComputer = true;
						winner = true;
						return;
					}
				}
				
				
			}
			
			
		}
		
		
			
		
	} //end method
	
	//Will set a piece on the board
	public boolean setPiece(String p, int r, int c) {
		
		if(isIndexValid(r,c)==true)   {board[r][c] = p; return true;}
		
		System.out.println("\nSpot already taken");
		return false;
	}
	
	//will check that the spot is empty
	public boolean isIndexValid(int r, int c)
	{
		if(r < 0 || c < 0 || r > 7 || c > 7) {
			return false;
		}
		
		if(board[r][c].equals("-"))   { return true;}
		
		return false;
	}
	
	//This method will print the board
	public void printBoard() {
		
		int charValue = 65;
		
		System.out.println("");
		System.out.println("  1 2 3 4 5 6 7 8");
		for(int i = 0; i < board.length; i++) {
			System.out.print((char)charValue++ + " ");
			for(int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			
			System.out.print("\n");
		}
		
	}
	
	/*
	 * Returns the current state of the board
	 */
	
	public Board currentBoard() {
		return this;
	}
 	
	public static void main(String[] args) {
		
		
		
	}
	

}

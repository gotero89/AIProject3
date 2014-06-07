package fourinaline;

import java.util.ArrayList;

public class Computer extends Player
{
	private String piece = "X";
	private Board computerB;
	private boolean first;
	private long timer;
	private String move = "";
	private long timeToThink;
	private Board currentBoard;
	private final int DEPTH = 3;

	public Computer(Board b, boolean firstMove, long timeToThink)
	{
		super(b);
		computerB = b;
		first = firstMove;
		this.timeToThink = timeToThink;
	}

	public void makeMove()
	{
		/*
		String s = alphaBeta();
		int row = findIndexLetter(s.charAt(0));
		int col = findIndex(s.charAt(1));
		computerB.setPiece(piece, row, col);
		*/
		
		//TEST CODE
		int alpha = -10000000;
		int beta = 10000000;
		
		
		//Contains array with best score of board, and row of column of board
		int[] bestMove = alphaBetaPrunning(4, computerB, alpha, beta, piece,0,0,0);
		
		computerB.setPiece(piece, bestMove[1], bestMove[2]);
		
		//WILL GET THE BOARD RETURNED FROM ALPHA BETA, AND EXTRACT THE MOVE

	}
	

	/*
	private String alphaBeta()
	{
		String move = "";
		timer = 0;
		v = max(computerB, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		return move;
	}
	*/
	
	
	/*
	 * This version of alpha beta prunning will execute if
	 * the computer goes second, it will be defending
	 */
	
	
	
	/*
	 * ****************************
	 * TEST CODE PERFORM ALPHA BETA
	 * ***************************
	 */
	private int[] alphaBetaPrunning(int depth, Board b, int alpha, int beta,String player, int bestRow, int bestCol, int score) {
		
		
		if(cutoff(b, depth)) {
			//need to get score and row and column of move
			score = evaluate(b);
			return new int[] {score, bestRow, bestCol };
		}
		
		
		//children = all valid moves for this player
		ArrayList<Board> children = b.generateSuccesors(b, player);
		
	
		
		
		
		
		//this is max's turn
		if(player == "X") {
			
			//Iterate through the children board
			for(int i = 0; i < children.size(); i++) {
				
				bestRow = children.get(0).getNewMove()[0];
				bestCol = children.get(0).getNewMove()[1];
			
				int[] bestMove = alphaBetaPrunning(depth-1, children.get(i), alpha, beta, "O", bestRow, bestCol, score);
				
				score = bestMove[0];
						
				//if(score > alpha) alpha  = score
				//if(bestMove[0] > alpha[0]) { alpha = bestMove;  };
				
				if(score > alpha ) {
					alpha = score;
					bestRow = bestMove[1];
					System.out.println("score > alpha executes");
					bestCol = bestMove[2];
				}
					
				
				if(alpha >= beta) break;
				
				
			}
			
		}
		
		//opponent's turn
		else {
		
			//Iterate through the children board
			for(int i = 0; i < children.size(); i++) {
				
				bestRow = children.get(i).getNewMove()[0];
				bestCol = children.get(i).getNewMove()[1];
			
				int[] bestMove = alphaBetaPrunning(depth-1, children.get(i), alpha, beta, "X", bestRow, bestCol, 0);
				
				score = bestMove[0];
						
				//if(score > alpha) alpha  = score
				//if(bestMove[0] > alpha[0]) { alpha = bestMove;  };
				
				if(score < beta ) {
					beta = score;
					bestRow = bestMove[1];
					bestCol = bestMove[2];
				}
					
				
				if(alpha >= beta) break;
			
				
			}
			
		}
		
		
		return new int[] {score, bestRow, bestCol };
	
	}
	
	
	
	
	
	
	
	
	
	
	
	public void setCurrentBoard(Board b) {
		currentBoard = b;
	}
	
	
	
	
	

	private boolean cutoff(Board b, int depth)
	{
		//Uncomment below part when have to set limit time
		//return ((System.currentTimeMillis() - timer) > timeToThink*1000)
				//|| (depth == 0);
		
		//For now just test to depth
		return depth == 0;
	}
	//return the highest points for that board
	private int evaluate(Board b)
	{
		int temp = 0;
		int highestPts = 0;
		for(int i=0;i<b.BOARD_SIZE;i++)
		{
			for(int j=0;j<b.BOARD_SIZE;j++)
			{
				temp += checkCross(i,j,b);
				if(temp>highestPts)   {highestPts=temp;}
				temp = 0;
			}
		}
		return highestPts;
	}
	//given a position and board it will calculate and return points
	private int checkCross(int i, int j, Board b)
	{
		int points = 0;
		//ONLY IF "X" IS FOUND   ASSUMING X IS COMPUTER
		if(b.board[i][j].equals("X"))
		{
			if(i==0)
			{
				if(j==0)
				{
					points+=checkConsecutiveX('r',i,j,b);
					points+=checkConsecutiveX('d',i,j,b);
					points+=checkConsecutiveONextToX('r',i,j,b);
					points+=checkConsecutiveONextToX('d',i,j,b);
				}
				else if(j==7)
				{
					points+=checkConsecutiveX('d',i,j,b);
					points+=checkConsecutiveX('l',i,j,b);
					points+=checkConsecutiveONextToX('d',i,j,b);
					points+=checkConsecutiveONextToX('l',i,j,b);
				}
				else
				{
					points+=checkConsecutiveX('r',i,j,b);
					points+=checkConsecutiveX('d',i,j,b);
					points+=checkConsecutiveX('l',i,j,b);
					points+=checkConsecutiveONextToX('r',i,j,b);
					points+=checkConsecutiveONextToX('d',i,j,b);
					points+=checkConsecutiveONextToX('l',i,j,b);
				}
				//points=0;
			}
			else if(i==7)
			{
				if(j==0)
				{
					points+=checkConsecutiveX('u',i,j,b);
					points+=checkConsecutiveX('r',i,j,b);
					points+=checkConsecutiveONextToX('u',i,j,b);
					points+=checkConsecutiveONextToX('r',i,j,b);
				}
				else if(j==7)
				{
					points+=checkConsecutiveX('u',i,j,b);
					points+=checkConsecutiveX('l',i,j,b);
					points+=checkConsecutiveONextToX('u',i,j,b);
					points+=checkConsecutiveONextToX('l',i,j,b);
				}
				else
				{
					points+=checkConsecutiveX('u',i,j,b);
					points+=checkConsecutiveX('r',i,j,b);
					points+=checkConsecutiveX('l',i,j,b);
					points+=checkConsecutiveONextToX('u',i,j,b);
					points+=checkConsecutiveONextToX('r',i,j,b);
					points+=checkConsecutiveONextToX('l',i,j,b);
				}
				//points=0;
			}
			//i = 1 ---> 6
			else
			{
				if(j==0)
				{
					points+=checkConsecutiveX('u',i,j,b);
					points+=checkConsecutiveX('r',i,j,b);
					points+=checkConsecutiveX('d',i,j,b);
					points+=checkConsecutiveONextToX('u',i,j,b);
					points+=checkConsecutiveONextToX('r',i,j,b);
					points+=checkConsecutiveONextToX('d',i,j,b);
				}
				else if(j==7)
				{
					points+=checkConsecutiveX('u',i,j,b);
					points+=checkConsecutiveX('d',i,j,b);
					points+=checkConsecutiveX('l',i,j,b);
					points+=checkConsecutiveONextToX('u',i,j,b);
					points+=checkConsecutiveONextToX('d',i,j,b);
					points+=checkConsecutiveONextToX('l',i,j,b);
				}
				else
				{
					points+=checkConsecutiveX('u',i,j,b);
					points+=checkConsecutiveX('r',i,j,b);
					points+=checkConsecutiveX('d',i,j,b);
					points+=checkConsecutiveX('l',i,j,b);
					points+=checkConsecutiveONextToX('u',i,j,b);
					points+=checkConsecutiveONextToX('r',i,j,b);
					points+=checkConsecutiveONextToX('d',i,j,b);
					points+=checkConsecutiveONextToX('l',i,j,b);
				}
				//points=0;
			}
		}
		return points;
	}
	//This method should only be used when the given position is X (the computer's piece)
	private int checkConsecutiveONextToX(char direction, int row, int col, Board b)
	{
		int points = 0;
		if(direction=='u')
		{
			for(int i=row-1;i>=row-3;i--)
			{
				if(i>-1 && b.board[i][col].equals("X"))   {break;}
				if(i>-1 && b.board[i][col].equals("O"))
				{
					if(i==row-1)   {points+=100;}
					if(i==row-2)   {points+=100;}
					if(i==row-2 && row+1!=8 && b.board[row+1][col].equals("O"))   {points+=300;}
					//else if(i==row-2 && b.board[row+1][col].equals("O"))   {points+=300;}
					if(i==row-3 && b.board[i-1][col].equals("X"))   {points+=300;}
				}
			}
		}
		if(direction=='r')
		{
			for(int i=col+1;i<=col+3;i++)
			{
				if(i<8 && b.board[row][i].equals("X"))   {break;}
				if(i<8 && b.board[row][i].equals("O"))
				{
					if(i==col+1)   {points+=100;}
					if(i==col+2)   {points+=100;}
					if(i==col+2 && col-1!=-1 && b.board[row][col-1].equals("O"))   {points+=300;}
					//else if(i==col+2 && b.board[row][col-1].equals("O"))   {points+=300;}
					if(i==col+3 && b.board[row][i+1].equals("X"))   {points+=300;}
				}

			}
		}
		if(direction=='d')
		{
			for(int i=row+1;i<=row+3;i++)
			{
				if(i<8 && b.board[i][col].equals("X"))   {break;}
				if(i<8 && b.board[i][col].equals("O"))
				{
					if(i==row+1)   {points+=100;}
					if(i==row+2)   {points+=100;}
					if(i==row+2 && row-1!=-1 && b.board[row-1][col].equals("O"))   {points+=300;}
					//else if(i==row+2 & b.board[row-1][col].equals("0"))   {points+=300;}
					if(i==row+3 && b.board[i+1][col].equals("X"))   {points+=300;}
				}
			}
		}
		if(direction=='l')
		{
			for(int i=col-1;i>=col-3;i--)
			{
				if(i>-1 && b.board[row][i].equals("O"))   {break;}
				if(i>-1 && b.board[row][i].equals("X"))
				{
					if(i==col-1)   {points+=100;}
					if(i==col-2)   {points+=100;}
					if(i==col-2 && col+1!=8 && b.board[row][col+1].equals("O"))   {points+=300;}
					//else if(i==col-2 && b.board[row][col+1].equals("O"))   {points+=300;}
					if(i==col-3 && b.board[row][i-1].equals("X"))   {points+=300;}
				}
			}
		}
		return points;
	}
	//start from a position away from the origin !!! (i+1) however the position given in parameter is the origin position
	//this is for 'X' the computer's piece so only used when origin position is X
	private int checkConsecutiveX(char direction, int row, int col, Board b)
	{
		int points = 0;
		if(direction=='u')
		{
			for(int i=row-1;i>=row-3;i--)
			{
				if(i>-1 && b.board[i][col].equals("O"))   {break;}
				if(i>-1 && b.board[i][col].equals("-"))   {break;}
				if(i>-1 && b.board[i][col].equals("X"))
				{
					if(i==row-1)   {points+=100;}
					if(i==row-2)   {points+=200;}
					if(i==row-3)   {points+=1000;}
				}
			}
		}
		if(direction=='r')
		{
			for(int i=col+1;i<=col+3;i++)
			{
				if(i<8 && b.board[row][i].equals("O"))   {break;}
				if(i<8 && b.board[row][i].equals("-"))   {break;}
				if(i<8 && b.board[row][i].equals("X"))
				{
					if(i==col+1)   {points+=100;}
					if(i==col+2)   {points+=200;}
					if(i==col+3)   {points+=1000;}
				}

			}
		}
		if(direction=='d')
		{
			for(int i=row+1;i<=row+3;i++)
			{
				if(i<8 && b.board[i][col].equals("O"))   {break;}
				if(i<8 && b.board[i][col].equals("-"))   {break;}
				if(i<8 && b.board[i][col].equals("X"))
				{
					if(i==row+1)   {points+=100;}
					if(i==row+2)   {points+=200;}
					if(i==row+3)   {points+=1000;}
				}
			}
		}
		if(direction=='l')
		{
			for(int i=col-1;i>=col-3;i--)
			{
				if(i>-1 && b.board[row][i].equals("O"))   {break;}
				if(i>-1 && b.board[row][i].equals("-"))   {break;}
				if(i>-1 && b.board[row][i].equals("X"))
				{
					if(i==col-1)   {points+=100;}
					if(i==col-2)   {points+=200;}
					if(i==col-3)   {points+=1000;}
				}
			}
		}
		return points;
	}
}
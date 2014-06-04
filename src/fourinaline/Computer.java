package fourinaline;
import java.util.*;

public class Computer extends Player
{
	private String piece = "X";
	private Board computerB;
	private boolean first;
	private long timer;
	private String move = "";
	
	public Computer(Board b, boolean firstMove)
	{
		super(b);
		computerB = b;
		first = firstMove;
	}
	
	public void makeMove()
	{
		/*
		String s = alphaBeta();
		int row = findIndexLetter(s.charAt(0));
		int col = findIndex(s.charAt(1));
		computerB.setPiece(piece, row, col);
		*/
		
		alphaBeta();
		
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
	private void alphaBeta( )
	{
		int best = Integer.MIN_VALUE;
		int v;
		int depth = 3;
		int row = 0, col = 0;
		timer = System.currentTimeMillis();
		for( int i = 0; i < computerB.BOARD_SIZE; i++ )
		{
			for( int j = 0; j < computerB.BOARD_SIZE; j++ )
			{
				if(computerB.setPiece(piece, i, j))
				{
					v = min(computerB, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
					if( v > best )
					{
						row = i;
						col = j;
						best = v;
					}
					computerB.setPiece("-", i, j);
				}
			}
		}
		computerB.setPiece(piece, row, col);
	}
	
	private int max(Board b, int alpha, int beta, int depth)
	{
		if(cutoff(b, depth))
			return evaluate(b);
		int v = Integer.MIN_VALUE;
		for( int i = 0; i < b.BOARD_SIZE; i++ )
		{
			for( int j = 0; j < b.BOARD_SIZE; j++ )
			{
				v = Math.max(v, min(b, alpha, beta, depth-1));
				if( v > beta )
					return v;
				alpha = Math.max(alpha, v);
			}
		}
		return v;
	}
	
	private int min(Board b, int alpha, int beta, int depth)
	{
		if(cutoff(b, depth))
			return evaluate(b);
		int v = Integer.MAX_VALUE;
		for( int i = 0; i < b.BOARD_SIZE; i++ )
		{
			for( int j = 0; j < b.BOARD_SIZE; j++ )
			{
				v = Math.min(v, max(b, alpha, beta, depth-1));
				if( v < beta )
					return v;
				beta = Math.min(beta, v);
			}
		}
		return v;
	}

	private boolean cutoff(Board b, int depth)
	{
		return ((System.currentTimeMillis() - timer) > 5000)
				|| (depth == 0);
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

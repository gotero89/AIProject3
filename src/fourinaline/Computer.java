package fourinaline;

public class Computer extends Player
{
	private String piece = "O";
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
	
	private int evaluate(Board b)
	{
		return 0;
	}
}

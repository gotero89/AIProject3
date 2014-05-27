package fourinaline;

public class Computer extends Player
{
	private String piece = "O";
	private Board computerB;
	
	public Computer(Board b)
	{
		super(b);
		computerB = b;
	}
	
	public void makeMove()
	{
		String s = alphaBeta();
		int row = findIndexLetter(s.charAt(0));
		int col = findIndex(s.charAt(1));
		computerB.setPiece(piece, row, col);
	}
	
	private String alphaBeta()
	{
		String move = "";
		return move;
	}

}

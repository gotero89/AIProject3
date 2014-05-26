package fourinaline;
import java.util.*;

class Player
{
	private char piece = 'O';
	private Board playerB;
	public Player(Board b)   {playerB = b;}
	public void makeMove(String s)
	{
		int row = findIndexLetter(s.charAt(0));
		int col = findIndex(s.charAt(1));
		b.setPiece(piece,row,col);
	}
	//return -1 if invalid input
	public int findIndexLetter(char ch)
	{
		if(Character.toLowerCase(ch)=='a')    {return 0;}
		if(Character.toLowerCase(ch)=='b')    {return 1;}
		if(Character.toLowerCase(ch)=='c')    {return 2;}
		if(Character.toLowerCase(ch)=='d')    {return 3;}
		if(Character.toLowerCase(ch)=='e')    {return 4;}
		if(Character.toLowerCase(ch)=='f')    {return 5;}
		if(Character.toLowerCase(ch)=='g')    {return 6;}
		if(Character.toLowerCase(ch)=='h')    {return 7;}
		return -1;
	}
	//return -1 if invalid input
	public int findIndex(char ch)
	{
		if(Character.getNumericValue(ch)==1)   {return 0;}
		if(Character.getNumericValue(ch)==2)   {return 1;}
		if(Character.getNumericValue(ch)==3)   {return 2;}
		if(Character.getNumericValue(ch)==4)   {return 3;}
		if(Character.getNumericValue(ch)==5)   {return 4;}
		if(Character.getNumericValue(ch)==6)   {return 5;}
		if(Character.getNumericValue(ch)==7)   {return 6;}
		if(Character.getNumericValue(ch)==8)   {return 7;}
		return -1;
	}
}

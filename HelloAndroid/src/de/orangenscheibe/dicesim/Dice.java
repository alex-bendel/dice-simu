package de.orangenscheibe.dicesim;

import java.util.Random;

public class Dice {

	public int Id;
	public int Max;
	public int Min;
	public int Last;

	private Random rnd = new Random();	

	public Dice(int id, int min, int max)
	{
		Id = id;
		Max = max;
		Min = min;
		Last = min;
	}
		
	public int Roll()
	{
		Last = (rnd.nextInt(Max - Min + 1) + Min);
		return Last;
	}
}

package de.orangenscheibe.dicesim.model;

import java.util.Random;

public class Dice {

	public int Id;
	public int Max;
	public int Min;
	public int Last;

	private Random rnd = new Random();	

	public Dice()
	{
		this(0,0,0);
	}
	
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

	public int Roll(int min, int max)
	{
		return (rnd.nextInt(max - min + 1) + min);
	}
}

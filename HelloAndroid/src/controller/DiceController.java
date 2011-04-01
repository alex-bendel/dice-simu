package controller;

import java.util.HashMap;

import android.view.View;
import android.view.View.OnClickListener;
import com.example.helloandroid.Dice;
import com.example.helloandroid.view.DiceView;

public class DiceController {

	private static HashMap<Integer,Dice> dices = new HashMap<Integer,Dice>();
	
	protected static OnClickListener RollListner = new OnClickListener() {
    	public void onClick(View v) { 
    		DiceView diceView = (DiceView) v;         		
    		Dice dice = dices.get(diceView.getId());        		
    		diceView.setCurrent(dice.Roll());
    	}
	};
	
	public static void registerDice(DiceView view, Dice dice)
	{
		dices.put(dice.Id, dice);
		view.setOnClickListener(RollListner);
		view.setMax(dice.Max);
		view.setMin(dice.Min);
		view.setCurrent(dice.Roll());
	}		
}

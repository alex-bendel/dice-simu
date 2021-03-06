package de.orangenscheibe.dicesim.controller;

import java.util.HashMap;
import de.orangenscheibe.dicesim.model.Dice;
import de.orangenscheibe.dicesim.view.AbstractDiceView;
import android.view.View;
import android.view.View.OnClickListener;

public class DiceController {

	private static HashMap<Integer, Dice> dices = new HashMap<Integer, Dice>();
	private static HashMap<Integer, AbstractDiceView> views = new HashMap<Integer, AbstractDiceView>();
	
	protected static OnClickListener RollListner = new OnClickListener() {
    	public void onClick(View v) {     		
    		AbstractDiceView diceView = (AbstractDiceView) v;
    		
    		if(diceView != null && !diceView.isLocked())
    		{
				Dice dice = dices.get(diceView.getId());        		
				DiceAnimator animator = new DiceAnimator(diceView, dice.Min, dice.Max, dice.Roll()); 
				animator.start();
    		}
    	}
	};
		
	public static boolean isRegisterd(int diceId)
	{
		return dices.containsKey(diceId);
	}
	
	public static void registerDice(AbstractDiceView view, Dice dice, boolean registerListner)
	{
		if(registerListner)
			view.setOnClickListener(RollListner);
		
		dices.put(dice.Id, dice);
		views.put(dice.Id, view);
	}

	public static Dice unregisterDice(int diceId)
	{
		if(views.containsKey(diceId))
			views.remove(diceId).setOnClickListener(null);
		
		if(dices.containsKey(diceId))
			return dices.remove(diceId);
		
		return null;
	}
	
	public static void unregisterAll()
	{
		for (AbstractDiceView dv : views.values()) 
			dv.setOnClickListener(null);
		
		dices.clear();
		views.clear();
	}
}

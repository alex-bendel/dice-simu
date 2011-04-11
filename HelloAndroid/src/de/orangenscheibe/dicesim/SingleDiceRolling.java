package de.orangenscheibe.dicesim;

import de.orangenscheibe.dicesim.controller.DiceAnimator;
import de.orangenscheibe.dicesim.controller.DiceController;
import de.orangenscheibe.dicesim.model.Dice;
import de.orangenscheibe.dicesim.view.SingleDiceView;
import android.os.Bundle;
import android.view.View;

public class SingleDiceRolling extends AbstractActivity {
	
	private Dice _dice; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {   	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.singledice_rolling);   
        _dice = new Dice();
    } 
	
	@Override 
	public void onStart() {
		super.onStart();
		
		SingleDiceView dv = (SingleDiceView) findViewById(R.id.singleDiceView);

		if(dv != null)
		{
			Dice dice = null;
	        	
	        if(DiceController.isRegisterd(R.id.singleDiceView))
	        	dice = DiceController.unregisterDice(R.id.singleDiceView); 
	        
	        if(dice == null)
	        	dice = new Dice(R.id.singleDiceView, dv.Min, dv.Max);
	        	
	        DiceController.registerDice(dv, dice, false);
	        dv.setCurrentValue(dice.Last, true);
		}
    }
	
    public void onClickRollD20(View view) {
    	RollDice(1, 20);
    }

    public void onClickRollD12(View view) {
    	RollDice(1, 12);
    }
    
    public void onClickRollD10(View view) {
    	RollDice(1, 10);
    }

    public void onClickRollD8(View view) {
    	RollDice(1, 8);
    }

    public void onClickRollD6(View view) {
    	RollDice(1, 6);
    }

    public void onClickRollD4(View view) {
    	RollDice(1, 4);
    }

    private void RollDice(int min, int max)
    {
    	SingleDiceView diceView = (SingleDiceView) findViewById(R.id.singleDiceView);

    	if(diceView != null && !diceView.isLocked())
    	{        	
    		diceView.Name = String.format("D%d", max);
    		
    		DiceAnimator animator = new DiceAnimator(diceView, min, max, _dice.Roll(min, max)); 
    		animator.start();
    	}
	}
}

package de.orangenscheibe.dicesim;

import android.os.Bundle;
import de.orangenscheibe.dicesim.controller.DiceController;
import de.orangenscheibe.dicesim.model.Dice;
import de.orangenscheibe.dicesim.view.MultiDiceView;

public class MultiDiceRolling extends AbstractActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {   	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.multidice_rolling);        
    }    
	
	@Override 
	public void onStart() {
		super.onStart();

        int[] diceIds = { R.id.D20, R.id.D12, R.id.D10, R.id.D8, R.id.D6, R.id.D4 };        

        // register dices
        for(int id : diceIds)
        {	
        	MultiDiceView dv = (MultiDiceView) findViewById(id);
        	Dice dice = null;
        	
        	if(DiceController.isRegisterd(id))
        		dice = DiceController.unregisterDice(id); 
        
        	if(dice == null)
        		dice = new Dice(id, dv.Min, dv.Max);
        	
        	DiceController.registerDice(dv, dice, true);
        	dv.setCurrentValue(dice.Last, true);
        }
    }
}
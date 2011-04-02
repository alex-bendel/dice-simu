package de.orangenscheibe.dicesim;

import android.app.Activity;
import android.os.Bundle;

import com.example.helloandroid.R;

import de.orangenscheibe.dicesim.controller.DiceController;
import de.orangenscheibe.dicesim.view.DiceView;

public class DiceRolling extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);               
        setContentView(R.layout.main);

        int[] diceIds = { R.id.D20, R.id.D12, R.id.D10, R.id.D8, R.id.D6, R.id.D4 };
        
        for(int id : diceIds)
        {
        	DiceView dv = (DiceView) findViewById(id);
        	DiceController.registerDice(dv, new Dice(id, dv.Min, dv.Max));
        }
    }    
    
    @Override
    public void onDestroy() {
    	DiceController.unregisterAll();
    }
}
package de.orangenscheibe.dicesim;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import de.orangenscheibe.dicesim.R;
import de.orangenscheibe.dicesim.controller.DiceController;
import de.orangenscheibe.dicesim.view.DiceView;

public class DiceRolling extends Activity {
	
	private static int onCreateCounter = 0;
	private static int onStartCounter = 0;
	private static int onRestartCounter = 0;	
	private static int onResumeCounter = 0;
	private static int onPauseCounter = 0;	
	private static int onStopCounter = 0;	
	private static int onDestroyCounter = 0;
	
    private static void PrintStatistics(TextView tv) {
        tv.setText(String.format("oCR=%d/%d, oS=%d, oR=%d oP=%d, oS=%d, oD=%d",
        	onCreateCounter, onRestartCounter, onStartCounter, onResumeCounter, onPauseCounter, onStopCounter, onDestroyCounter));		
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {   	
    	super.onCreate(savedInstanceState);
    	onCreateCounter++;    	
        setContentView(R.layout.main);        
    }    

	@Override 
	public void onRestart() {
		super.onRestart();
		onRestartCounter++;
	}
	
	@Override 
	public void onStart() {
		super.onStart();
		onStartCounter++;

        int[] diceIds = { R.id.D20, R.id.D12, R.id.D10, R.id.D8, R.id.D6, R.id.D4 };        

        // register dices
        for(int id : diceIds)
        {	
        	DiceView dv = (DiceView) findViewById(id);
        	Dice dice = null;
        	
        	if(DiceController.isRegisterd(id))
        		dice = DiceController.unregisterDice(id); 
        
        	if(dice == null)
        		dice = new Dice(id, dv.Min, dv.Max);
        	
        	DiceController.registerDice(dv, dice);
        	dv.setCurrentValue(dice.Last, true);
        }

        TextView tv = (TextView) findViewById(R.id.InfoMessage);
        PrintStatistics(tv);
    }
	
	@Override 
	public void onResume() {
		super.onResume();
		onResumeCounter++;
	}

	@Override 
	public void onPause() {
		super.onPause();
		onPauseCounter++;
	}

	@Override 
	public void onStop() {
		super.onStop();
		onStopCounter++;
	}

	@Override
    public void onDestroy() {
    	super.onDestroy();
    	onDestroyCounter++;
	}
}
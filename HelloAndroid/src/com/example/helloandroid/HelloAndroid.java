package com.example.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import com.example.helloandroid.view.DiceView;
import controller.DiceController;

public class HelloAndroid extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);               
        setContentView(R.layout.main);
        
        DiceView diceView = (DiceView) findViewById(R.id.diceOne);
        DiceController.registerDice(diceView, new Dice(R.id.diceOne, 1, 20));

        diceView = (DiceView) findViewById(R.id.diceTwo);        
        DiceController.registerDice(diceView, new Dice(R.id.diceTwo, 1, 8));
    }    
}
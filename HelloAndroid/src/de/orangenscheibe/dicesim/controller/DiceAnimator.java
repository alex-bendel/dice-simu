package de.orangenscheibe.dicesim.controller;

import java.util.Random;

import de.orangenscheibe.dicesim.view.AbstractDiceView;

public class DiceAnimator extends Thread {
    
	private AbstractDiceView _view;
	private int _min;
	private int _max;
	private int _last;
	private boolean _stop = false;
	
	public DiceAnimator(final AbstractDiceView diceView, final int min, int max, int rolled) {
        _view = diceView;
        _min = min;
        _max = max;
        _last = rolled;
    }
    
    @Override
    public void run() {
        int i = 0;
        int lastRoll = 0;
        int currentRoll;
        
        Random rnd = new Random();
        
        try
        {        
        	while (!_stop && ++i < 15) {
	        	
	        	do {
	        		currentRoll = rnd.nextInt(_max - _min + 1) + _min;
	        	} while(currentRoll == lastRoll); 
	        		
	        	_view.setCurrentValue(currentRoll, false);
	        	_view.postInvalidate();
	        	
	        	lastRoll = currentRoll;
	        	
	        	try { Thread.sleep(50 + (int) (i*i/3)); }
	        	catch (InterruptedException e) {}
	        }
        }
        finally {
        	_view.setCurrentValue(_last, true);
        	_view.postInvalidate();
        }        
    }

    public void drop() {
        _stop = true;
    }
}
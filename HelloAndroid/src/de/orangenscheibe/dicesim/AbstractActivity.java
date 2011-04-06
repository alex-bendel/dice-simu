package de.orangenscheibe.dicesim;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AbstractActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

			case R.id.menu_single_dice:
				startActivity(new Intent(this, SingleDiceRolling.class));
				return true;

	    	case R.id.menu_multi_dice:
	    		startActivity(new Intent(this, MultiDiceRolling.class));
	    		return true;
	        
	    	case R.id.menu_dice_set:
	    		startActivity(new Intent(this, DiceSetRolling.class));
	    		return true;

	    	case R.id.menu_dice_set_setup:
	    		startActivity(new Intent(this, DiceSetSetup.class));
	    		return true;
	    	
	    	default:
	    		return super.onOptionsItemSelected(item);
	    }
	}
	
//	public void startMultiDiceRolling() {        
//		startActivity(new Intent(this, MultiDiceRolling.class));
//	}
//
//	public void startDiceSetRolling() {        
//		startActivity(new Intent(this, DiceSetRolling.class));
//	}
	
//	public void startDiceSetConfig() {        
//		startActivity(new Intent(this, DiceSetSetup.class));
//	}	
}

package de.orangenscheibe.dicesim.view;

import de.orangenscheibe.dicesim.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class AbstractDiceView extends View {

	public String Name;
    public int Min;
    public int Max;	   
    
    protected boolean _locked;
    protected String _diceText;
    
    protected Paint _paint;
    protected int _ascent;    
    
	public AbstractDiceView(Context context) {
		super(context);			
	}

    public AbstractDiceView(Context context, AttributeSet attrs) {
        super(context, attrs);  
        
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultiDiceView);

        String name = a.getString(R.styleable.MultiDiceView_name);
        int min = a.getColor(R.styleable.MultiDiceView_min, 1);
        int max = a.getColor(R.styleable.MultiDiceView_max, 6);
        int color = a.getColor(R.styleable.MultiDiceView_color, 0xFFFFFFFF);
                        
        initDefaults(name, color, min, max);          
        a.recycle();
    }
	
    private void initDefaults(String name, int color, int min, int max) {		
		
		Name = name != null ? name : "Dxx";
		Min = min;
		Max = max > min ? max : min + 1;
		
		_paint = new Paint();
		_paint.setAntiAlias(true);
		_paint.setTextSize(48);
		_paint.setColor(color);
		_paint.setStyle(Style.FILL);
       
        setCurrentValue(1, true);		
        setPadding(5, 5, 5, 5);                
	}	
	
	public boolean isLocked()
	{
		return _locked;
	}
		
	public void setCurrentValue(int current, boolean finalValue)
	{
		_diceText = String.format("%s: %s", Name, Integer.toString(current));			
		_locked = !finalValue;		
	}
}

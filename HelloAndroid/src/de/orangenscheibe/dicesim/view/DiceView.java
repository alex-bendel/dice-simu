package de.orangenscheibe.dicesim.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.helloandroid.R;

public class DiceView extends View {

	public String Name;
    public int Min;
    public int Max;	   
    
    private boolean _locked;
    private String _diceText;
    
    private Paint _paint;
    private int _ascent;    
    private RectF _buttonRect = new RectF();
    
	public DiceView(Context context, String name, int color, int min, int max) {
		super(context);				
		initDefaults(name, color, min, max); 
	}
	
    public DiceView(Context context, AttributeSet attrs) {
        super(context, attrs);                   
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DiceView);

        String name = a.getString(R.styleable.DiceView_name);
        int min = a.getColor(R.styleable.DiceView_min, 1);
        int max = a.getColor(R.styleable.DiceView_max, 6);
        int color = a.getColor(R.styleable.DiceView_color, 0xFFFFFFFF);
                        
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
	
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));       
        prepareCoordinates();
    }

    private void prepareCoordinates() {
    	
    	float width = getWidth() - (getPaddingLeft() + getPaddingRight());
    	float height = getHeight() - (getPaddingTop() + getPaddingBottom());
    	    	
    	_buttonRect.top = getPaddingTop();
    	_buttonRect.bottom = _buttonRect.top + height;
    	_buttonRect.right = width;
    	_buttonRect.left = _buttonRect.right - height;
    }
    
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            result = (int) _paint.measureText(_diceText) + getPaddingLeft() + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        _ascent = (int) _paint.ascent();
        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = (int) (-_ascent + _paint.descent()) + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }
               
        return result;
    }
	
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);        
        canvas.drawText(_diceText, getPaddingLeft(), getPaddingTop() - _ascent, _paint);        
        canvas.drawRoundRect(_buttonRect, 5, 5, _paint); 
    }   
}

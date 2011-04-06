package de.orangenscheibe.dicesim.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

public class MultiDiceView extends AbstractDiceView {

    private RectF _buttonRect = new RectF();
    
	public MultiDiceView(Context context, String name, int color, int min, int max) {
		super(context);				
	}
	
    public MultiDiceView(Context context, AttributeSet attrs) {
        super(context, attrs);                   
    }

    private void prepareCoordinates() {
    	
    	float width = getWidth() - (getPaddingLeft() + getPaddingRight());
    	float height = getHeight() - (2*getPaddingTop() + 2*getPaddingBottom());
    	    	
    	_buttonRect.top = getPaddingTop() * 2;
    	_buttonRect.bottom = _buttonRect.top + height;
    	_buttonRect.right = width;
    	_buttonRect.left = _buttonRect.right - height;
    }    
	
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);       
        prepareCoordinates();
        
        canvas.drawText(_diceText, getPaddingLeft(), getPaddingTop() - _ascent, _paint);        
        canvas.drawRoundRect(_buttonRect, 5, 5, _paint); 
    }   
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
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
}

package de.orangenscheibe.dicesim.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class SingleDiceView extends AbstractDiceView {
	
	public SingleDiceView(Context context) {
		super(context);
	}
    
    public SingleDiceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
	
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);          
        
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        
        int textWidth = (int) _paint.measureText(_diceText);
        canvas.drawText(_diceText, getPaddingLeft() + width/2 - textWidth/2, getPaddingTop() + height/2, _paint);
        
//        Style previousStyle = _paint.getStyle();
//        _paint.setStyle(Style.STROKE);
//        
//        canvas.drawLine(0, 0, getWidth(), getHeight(), _paint);
//        canvas.drawLine(0, getHeight(), getWidth(), 0, _paint);
//        canvas.drawRect(0, 0, getWidth(), getHeight(), _paint);
//        
//        _paint.setStyle(previousStyle);
    } 
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {    	
    	_ascent = (int) _paint.ascent();
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
            result = 2 * (int) _paint.measureText(_diceText) + getPaddingLeft() + getPaddingRight();
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
            result = 3 * (int) (-_ascent + _paint.descent()) + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }
        
        return result;        
    }
}

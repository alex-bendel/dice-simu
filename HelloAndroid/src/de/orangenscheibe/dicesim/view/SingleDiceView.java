package de.orangenscheibe.dicesim.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;

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
        canvas.drawText(_diceText, getPaddingLeft(), getPaddingTop() - _ascent, _paint);        
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

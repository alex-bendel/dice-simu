package com.example.helloandroid.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.helloandroid.R;

public class DiceView extends View {

	private String mMin;
	private String mMax;
	private String mCurrent;
    private Paint mPaint;
    private int mAscent;    
    private String mDiceText;
	
	public DiceView(Context context, int min, int max, int current) {
		super(context);				
		initMe(min,max, current); 
	}
	
    public DiceView(Context context, AttributeSet attrs) {
        super(context, attrs);                   
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DiceView);

        int min = a.getInt(R.styleable.DiceView_min, 1);
        int max = a.getInt(R.styleable.DiceView_max, 6);
        int current = a.getInt(R.styleable.DiceView_value, 1);               
        initMe(min,max, current);  
        
        a.recycle();
    }
      
	private void initMe(int min, int max, int current) {
		
        setMin(min);
        setMax(max);
        setCurrent(current);
		
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(48);
		mPaint.setColor(0xFFFFFF00);
        setPadding(3, 3, 3, 3);       
	}
	
	public void setMin(int min)
	{
		mMin = Integer.toString(min);
        invalidate();
	}
	
	public void setMax(int max)
	{
		mMax = Integer.toString(max);
        invalidate();
	}
		
	public void setCurrent(int current)
	{
		mCurrent = Integer.toString(current);
		mDiceText = String.format("D%s: %s", mMax, mCurrent);
        invalidate();
	}	
	
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
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
            result = (int) mPaint.measureText(mDiceText) + getPaddingLeft()
                    + getPaddingRight();
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

        mAscent = (int) mPaint.ascent();
        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = (int) (-mAscent + mPaint.descent()) + getPaddingTop()
                    + getPaddingBottom();
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
        canvas.drawText(mDiceText, getPaddingLeft(), getPaddingTop() - mAscent, mPaint);
    }
}

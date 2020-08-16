package com.scorp.loftmoney;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class BalanceView extends View {

    private float expenses = 5400;
    private float incomes = 74000;

    private Paint expensePaint = new Paint();
    private Paint incomePaint = new Paint();

    public BalanceView(Context context) {
        super(context);
        init(null);
    }

    public BalanceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BalanceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public BalanceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void update(float expenses, float incomes){
        this.expenses = expenses;
        this.incomes = incomes;

        invalidate();
    }

    private void init(@Nullable AttributeSet attrs){

        if(attrs == null){
            expensePaint.setColor(ContextCompat.getColor(getContext(), R.color.expenseColor));
            incomePaint.setColor(ContextCompat.getColor(getContext(), R.color.incomeColor));
        }
        else{
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BalanceView);

            int expenseColor = typedArray.getColor(R.styleable.BalanceView_bv_expenseColor, 0);
            int incomeColor = typedArray.getColor(R.styleable.BalanceView_bv_incomeColor, 0);

            expensePaint.setColor(expenseColor);
            incomePaint.setColor(incomeColor);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float total = incomes + expenses;

        float expenseAngle = 360f*expenses/total;
        float incomeAngle = 360f*incomes/total;

        int space = 15;
        int size = Math.min(getWidth(), getHeight()) - space*2;
        int xMargin = (getWidth() - size)/2;
        int yMargin = (getHeight() - size)/2;

        canvas.drawArc(xMargin - space, yMargin,
                getWidth() - xMargin - space,
                getHeight() - yMargin, 180 - expenseAngle / 2,
                expenseAngle, true, expensePaint);

        canvas.drawArc(xMargin + space, yMargin,
                getWidth() - xMargin + space,
                getHeight() - yMargin, 360 - incomeAngle / 2,
                incomeAngle, true, incomePaint);
    }
}

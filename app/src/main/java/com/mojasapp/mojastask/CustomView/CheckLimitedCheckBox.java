package com.mojasapp.mojastask.CustomView;

import android.content.Context;
import android.util.AttributeSet;

import android.support.v7.widget.AppCompatCheckBox;

public class CheckLimitedCheckBox extends AppCompatCheckBox {

    private CheckLimit checkLimit;

    public CheckLimitedCheckBox(Context context) {
        super(context);
    }

    public CheckLimitedCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckLimitedCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCheckLimit(CheckLimit limit) {
        this.checkLimit = limit;
    }

    @Override
    public boolean performClick() {
        if (checkLimit != null) {
            if (isChecked()) {
                if (checkLimit.canNotUnCheck()) {
                    return false;
                }
            } else if (checkLimit.canNotCheck()){
                return false;
            }
        }
        return super.performClick();
    }
}

class CheckLimit {

    private final int limit;
    private int count;

    public CheckLimit(int limit) {
        this.limit = limit;
        count = 0;
    }

    void increment() {
        if (++count > limit) {
            throw new RuntimeException("Count is corrupter : "+count);
        }
    }

    void decrement() {
        if (--count < 0){
            throw new RuntimeException("Count is corrupter : "+count);
        }
    }

    boolean canNotCheck() {
        return count > limit;
    }

    boolean canNotUnCheck() {
        return count < 0;
    }
}

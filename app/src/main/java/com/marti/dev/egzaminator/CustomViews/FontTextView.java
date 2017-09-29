package com.marti.dev.egzaminator.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.marti.dev.egzaminator.R;

public class FontTextView extends android.support.v7.widget.AppCompatTextView {
    public FontTextView(Context context) {
        super(context);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context,attrs);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context,attrs);
    }

    private void setFont(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FontTextView);

        String fontName = typedArray.getString(R.styleable.FontTextView_text_font);

        try {
            Typeface font = Typeface.createFromAsset(context.getAssets(), fontName);
            setTypeface(font);
        }
        finally {
            typedArray.recycle();
        }
    }
}

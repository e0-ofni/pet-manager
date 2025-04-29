package com.example.petmanager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class DiaryListLayout extends GridLayout {
    TextView diarytitle, diarydate;

    public DiaryListLayout(Context context) {
        super(context);
        initView();
    }

    public DiaryListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public DiaryListLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);

    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.diarylist, this, false);
        addView(v);

        diarytitle = (TextView) findViewById(R.id.diarytitle);
        diarydate = (TextView) findViewById(R.id.diarydate);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DiaryListLayout);
        setTypeArray(typedArray);
    }


    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DiaryListLayout, defStyle, 0);
        setTypeArray(typedArray);
    }


    private void setTypeArray(TypedArray typedArray) {
        String diarytitle_resID = typedArray.getString(R.styleable.DiaryListLayout_diarytitle);
        diarytitle.setText(diarytitle_resID);

        String petname_resID = typedArray.getString(R.styleable.DiaryListLayout_diarydate);
        diarydate.setText(petname_resID);

        typedArray.recycle();
    }


    void setDiarytitle(String text_diarytitle) { diarytitle.setText(text_diarytitle); }

    void setDiarydate(String text_diarydate) {
        diarydate.setText(text_diarydate);
    }

}

package com.example.petmanager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class PetListLayout extends GridLayout {
    ImageView petimage;
    TextView petid, petname, petage, petspecies;

    public PetListLayout(Context context) {
        super(context);
        initView();
    }

    public PetListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public PetListLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);

    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.petlist, this, false);
        addView(v);

        petid = (TextView) findViewById(R.id.petid);
        petimage = (ImageView) findViewById(R.id.petimage);
        petname = (TextView) findViewById(R.id.petname);
        petage = (TextView) findViewById(R.id.petage);
        petspecies = (TextView) findViewById(R.id.petspecies);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PetListLayout);
        setTypeArray(typedArray);
    }


    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PetListLayout, defStyle, 0);
        setTypeArray(typedArray);
    }


    private void setTypeArray(TypedArray typedArray) {
        String petid_resID = typedArray.getString(R.styleable.PetListLayout_petid);
        petage.setText(petid_resID);

        int petimage_resID = typedArray.getResourceId(R.styleable.PetListLayout_petimage, R.drawable.dog);
        petimage.setImageResource(petimage_resID);

        String petname_resID = typedArray.getString(R.styleable.PetListLayout_petname);
        petname.setText(petname_resID);

        String petage_resID = typedArray.getString(R.styleable.PetListLayout_petage);
        petage.setText(petage_resID);

        String petspecies_resID = typedArray.getString(R.styleable.PetListLayout_petspecies);
        petspecies.setText(petspecies_resID);

        typedArray.recycle();
    }
    void setPetid(String text_petid) {
        petname.setText(text_petid);
    }

    void setPetimage(int menuimage_resID) {
        petimage.setImageResource(menuimage_resID);
    }

    void setPetname(String text_petname) {
        petname.setText(text_petname);
    }

    void setPetage(String text_petage) {
        petage.setText(text_petage);
    }

    void setPetspecies(String text_petspecies) {
        petspecies.setText(text_petspecies);
    }

}

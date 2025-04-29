package com.example.petmanager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EditPetLayout extends GridLayout {
    ImageButton editpetimage;
    EditText editpetname, editpetage, editpetspecies,
            editpetillness, editpetsurgery, editpetetc;
    Button editpetbtn, delpetbtn;

    public EditPetLayout(Context context) {
        super(context);
        initView();
    }

    public EditPetLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public EditPetLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);

    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.editpetlist, this, false);
        addView(v);

        editpetimage = (ImageButton) findViewById(R.id.editpetimage);
        editpetname = (EditText) findViewById(R.id.editpetname);
        editpetage = (EditText) findViewById(R.id.editpetage);
        editpetspecies = (EditText) findViewById(R.id.editpetspecies);
        editpetillness = (EditText) findViewById(R.id.editpetillness);
        editpetsurgery = (EditText) findViewById(R.id.editpetsurgery);
        editpetetc = (EditText) findViewById(R.id.editpetetc);
        editpetbtn = (Button) findViewById(R.id.editpetbtn);
        delpetbtn = (Button) findViewById(R.id.delpetbtn);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.EditPetLayout);
        setTypeArray(typedArray);
    }


    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.EditPetLayout, defStyle, 0);
        setTypeArray(typedArray);
    }


    private void setTypeArray(TypedArray typedArray) {
        String petid_resID = typedArray.getString(R.styleable.EditPetLayout_editpetid);
        editpetage.setText(petid_resID);

        int petimage_resID = typedArray.getResourceId(R.styleable.EditPetLayout_editpetimage, R.drawable.dog);
        editpetimage.setImageResource(petimage_resID);

        String petname_resID = typedArray.getString(R.styleable.EditPetLayout_editpetname);
        editpetname.setText(petname_resID);

        String petage_resID = typedArray.getString(R.styleable.EditPetLayout_editpetage);
        editpetage.setText(petage_resID);

        String petspecies_resID = typedArray.getString(R.styleable.EditPetLayout_editpetspecies);
        editpetspecies.setText(petspecies_resID);

        typedArray.recycle();
    }
    void setEditPetid(String text_petid) {
        editpetname.setText(text_petid);
    }

    void setEditPetimage(int petimage_resID) {
        editpetimage.setImageResource(petimage_resID);
    }

    void setEditPetname(String text_petname) {
        editpetname.setText(text_petname);
    }

    void setEditPetage(String text_petage) {
        editpetage.setText(text_petage);
    }

    void setEditPetspecies(String text_petspecies) {
        editpetspecies.setText(text_petspecies);
    }

    void setEditPetillness(String text_petillness) {
        editpetillness.setText(text_petillness);
    }

    void setEditPetsurgery(String text_petsurgery) {
        editpetsurgery.setText(text_petsurgery);
    }

    void setEditPetetc(String text_petetc) {
        editpetetc.setText(text_petetc);
    }

    Button getEditButton(){
        return editpetbtn;
    }

    Button getDelpetbtn() { return delpetbtn; }

    public ImageButton getEditpetimage() {
        return editpetimage;
    }

    String getEditpetname() {
        return editpetname.getText().toString();
    }

    String getEditpetage() {
        return editpetage.getText().toString();
    }

    String getEditpetspecies() {
        return editpetspecies.getText().toString();
    }

    String getEditpetillness() {
        return editpetillness.getText().toString();
    }

    String getEditpetsurgery() {
        return editpetsurgery.getText().toString();
    }

    String getEditpetetc() {
        return editpetetc.getText().toString();
    }
}

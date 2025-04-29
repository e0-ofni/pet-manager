package com.example.petmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditPetActivity extends AppCompatActivity {
    private DatabaseHelperMain dbHelper;

    ImageButton editpetimage;
    String editpetname, editpetage, editpetspecies,
            editpetillness, editpetsurgery, editpetetc, petname;
    Button editbtn, delbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpet);
        setTitle("반려동물 편집");

        dbHelper = new DatabaseHelperMain(this);
        final Cursor cursor = dbHelper.readRecordOrderByID();
        LinearLayout con = (LinearLayout) findViewById(R.id.editTab);

        while (cursor.moveToNext()) {
            final int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMain.PetList._ID));
            String itemimage = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETIMAGE));
            final String itemname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETNAME));
            final int itemage = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETAGE));
            String itemspecies = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETSPECIES));
            String itemillness = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETILLNESS));
            String itemsurgery = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETSURGERY));
            String iteminfo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETINFO));

            final EditPetLayout mLayout = new EditPetLayout(getApplicationContext());

            mLayout.setEditPetid(String.valueOf(itemId));
            mLayout.setEditPetimage(getResources().getIdentifier(itemimage, "drawable", this.getPackageName()));
            mLayout.setEditPetname(itemname);
            mLayout.setEditPetage(String.valueOf(itemage));
            mLayout.setEditPetspecies(itemspecies);
            mLayout.setEditPetillness(itemillness);
            mLayout.setEditPetsurgery(itemsurgery);
            mLayout.setEditPetetc(iteminfo);


            editpetname = mLayout.getEditpetname();
            editpetage = mLayout.getEditpetage();
            editpetspecies = mLayout.getEditpetspecies();
            editpetillness = mLayout.getEditpetillness();
            editpetsurgery = mLayout.getEditpetsurgery();
            editpetetc = mLayout.getEditpetetc();

            editbtn = mLayout.getEditButton();
            editbtn.setText("수정");
            editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editpetname = mLayout.getEditpetname();
                    editpetage = mLayout.getEditpetage();
                    editpetspecies = mLayout.getEditpetspecies();
                    editpetillness = mLayout.getEditpetillness();
                    editpetsurgery = mLayout.getEditpetsurgery();
                    editpetetc = mLayout.getEditpetetc();

                    dbHelper.updateRecord(itemId, "@drawable/dog", editpetname, Integer.parseInt(editpetage), editpetspecies,
                            editpetillness, editpetsurgery, editpetetc);
                    finish();
                    startActivity(getIntent());
                }
            });

            delbtn = mLayout.getDelpetbtn();
            delbtn.setText("삭제");
            delbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHelper.deleteRecord(itemId);
                    finish();
                    startActivity(getIntent());
                }
            });
            con.addView(mLayout);
        }
        {
            final EditPetLayout mLayout = new EditPetLayout(getApplicationContext());

            editbtn = mLayout.getEditButton();
            editbtn.setText("추가");
            editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    editpetname = mLayout.getEditpetname();
                    editpetage = mLayout.getEditpetage();
                    editpetspecies = mLayout.getEditpetspecies();
                    editpetillness = mLayout.getEditpetillness();
                    editpetsurgery = mLayout.getEditpetsurgery();
                    editpetetc = mLayout.getEditpetetc();

                    dbHelper.insertRecord("@drawable/dog", editpetname, Integer.parseInt(editpetage), editpetspecies,
                            editpetillness, editpetsurgery, editpetetc);
                    finish();
                    startActivity(getIntent());
                }
            });

            delbtn = mLayout.getDelpetbtn();
            delbtn.setEnabled(false);
            con.addView(mLayout);
        }
    }
}


package com.example.petmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    private DatabaseHelperMain dbHelperMain;
    Intent intentProfile, intentEditPet;
    private int REQUEST_CODE = 1;

    Button addpet,other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout con = (LinearLayout) findViewById(R.id.mainTab);

        intentProfile = new Intent(getApplicationContext(), ProfileActivity.class);
        intentEditPet = new Intent(getApplicationContext(), EditPetActivity.class);

        dbHelperMain = new DatabaseHelperMain(this);
        Cursor cursor = dbHelperMain.readRecordOrderByID();
        if (cursor.getCount() == 0) {
            dbHelperMain.insertRecord("@drawable/dog", "코코", 3, "푸들", "없음", "없음", "없음");
        }


        while (cursor.moveToNext()) {
            final int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMain.PetList._ID));
            String itemimage = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETIMAGE));
            final String itemname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETNAME));
            final int itemage = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETAGE));
            String itemspecies = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETSPECIES));

            final PetListLayout mLayout = new PetListLayout(getApplicationContext());

            mLayout.setPetid(String.valueOf(itemId));
            mLayout.setPetimage(getResources().getIdentifier(itemimage, "drawable", this.getPackageName()));
            mLayout.setPetname(itemname);
            mLayout.setPetage(String.valueOf(itemage));
            mLayout.setPetspecies(itemspecies);

            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intentProfile.putExtra("petId", itemId);
                    startActivityForResult(intentProfile, REQUEST_CODE);
                }
            });
            con.addView(mLayout);
        }

        addpet = (Button) findViewById(R.id.addPet);
        addpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intentEditPet, REQUEST_CODE);
            }
        });

        other = (Button)findViewById(R.id.other);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),maptest.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE) {
            finish();
            startActivity(getIntent());
        }
    }
}

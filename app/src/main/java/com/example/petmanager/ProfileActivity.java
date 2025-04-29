package com.example.petmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class ProfileActivity extends AppCompatActivity {
    private DatabaseHelperMain dbHelper;

    Intent intentDiary;
    private int REQUEST_CODE = 1;

    ImageView profileimage;
    TextView profilename, profileage, profilespecies,
            profileillness, profilesurgery, profileetc, petname;
    Button walk, medicine, diary;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("반려동물 프로필");

        intentDiary = new Intent(getApplicationContext(), DiaryActivity.class);

        dbHelper = new DatabaseHelperMain(this);
        final Cursor cursor = dbHelper.readRecordOrderByID();

        Intent inIntent = getIntent();
        final int itemId = inIntent.getIntExtra("petId", 0);
        while(cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMain.PetList._ID))==itemId) {
                profileimage = (ImageView) findViewById(R.id.profileimage);
                profilename = (TextView) findViewById(R.id.profilename);
                profileage = (TextView) findViewById(R.id.profileage);
                profilespecies = (TextView) findViewById(R.id.profilespecies);
                profileillness= (TextView) findViewById(R.id.profileillness);
                profilesurgery= (TextView) findViewById(R.id.profilesurgery);
                profileetc= (TextView) findViewById(R.id.profileetc);

                //profileimage = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETIMAGE));
                profilename.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETNAME)));
                profileage.setText(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETAGE))));
                profilespecies.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETSPECIES)));
                profileillness.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETILLNESS)));
                profilesurgery.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETSURGERY)));
                profileetc.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMain.PetList.PETINFO)));
            }
        }

        walk = (Button) findViewById(R.id.walk);
        medicine = (Button) findViewById(R.id.medicine);
        diary = (Button) findViewById(R.id.diary);

        walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Walkpet.class);
                startActivity(intent);
            }
        });
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Alaramsevice.class);
                startActivity(intent);
            }
        });
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petname = (TextView) findViewById(R.id.profilename);
                String intentname = petname.getText().toString();
                intentDiary.putExtra("petname", intentname);
                startActivityForResult(intentDiary, REQUEST_CODE);
            }
        });

    }
}

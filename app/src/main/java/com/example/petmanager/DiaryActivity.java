package com.example.petmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DiaryActivity extends AppCompatActivity {
    private DatabaseHelperDiary dbHelperDiary;
    Intent intentDiaryDetail, intentEditDiary;
    private int REQUEST_CODE = 1;

    Button writediary;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        setTitle("일기장");

        LinearLayout con = (LinearLayout) findViewById(R.id.diarylist);

        Intent inIntent = getIntent();
        intentDiaryDetail = new Intent(getApplicationContext(), DiaryPageActivity.class);
        intentEditDiary = new Intent(getApplicationContext(), EditDiaryActivity.class);

        dbHelperDiary = new DatabaseHelperDiary(this);
        final Cursor cursor = dbHelperDiary.readRecordOrderByID();
        if (cursor.getCount() == 0) {
            dbHelperDiary.insertRecord("코코", 2021, 6, 6,
                    "제목", "@drawable/dog", "내용");
        }

        final String petname = inIntent.getStringExtra("petname");
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYNAME)).equals(petname)) {
                final int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary._ID));
                final String itemname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYNAME));
                String itemtitle = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYTITLE));
                final int itemyear = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYYEAR));
                final int itemmonth = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYMONTH));
                final int itemday = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYDAY));


                final DiaryListLayout mLayout = new DiaryListLayout(getApplicationContext());

                mLayout.setDiarytitle(itemtitle);
                mLayout.setDiarydate(String.valueOf(itemmonth) + "/" + String.valueOf(itemday));

                mLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intentDiaryDetail.putExtra("diaryId", itemId);
                        startActivityForResult(intentDiaryDetail, REQUEST_CODE);
                    }
                });
                con.addView(mLayout);
            }
            writediary = findViewById(R.id.writediary);
            writediary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Cursor cursor2 = dbHelperDiary.readRecordOrderByID();

                    while (cursor2.moveToNext()) {
                        if (cursor2.getString(cursor2.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYNAME)).equals(petname)) {
                            final int itemId = cursor2.getInt(cursor2.getColumnIndexOrThrow(DatabaseDiary.Diary._ID));
                            final String itemname = cursor2.getString(cursor2.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYNAME));
                            intentEditDiary.putExtra("petname", itemname);

                        }
                    }
                    startActivityForResult(intentEditDiary, REQUEST_CODE);
                }
            });

        }
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

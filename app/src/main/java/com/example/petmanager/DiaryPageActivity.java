package com.example.petmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class DiaryPageActivity extends AppCompatActivity {
    private DatabaseHelperDiary dbHelper;

    ImageView pageimage;
    TextView pagetitle, pagedate, pagecontents;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.diarypage);
        setTitle("반려동물 일기");


        dbHelper = new DatabaseHelperDiary(this);
        final Cursor cursor = dbHelper.readRecordOrderByID();

        Intent inIntent = getIntent();
        final int itemId = inIntent.getIntExtra("diaryId", 0);
        while (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary._ID)) == itemId) {
                final String itemname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYNAME));
                String itemtitle = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYTITLE));
                final int itemyear = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYYEAR));
                final int itemmonth = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYMONTH));
                final int itemday = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYDAY));
                String itemcontecnt = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYCONTENTS));

                pageimage = (ImageView) findViewById(R.id.pageimage);
                pagetitle = (TextView) findViewById(R.id.pagetitle);
                pagedate = (TextView) findViewById(R.id.pagedate);
                pagecontents = (TextView) findViewById(R.id.pagecontents);

                //pageimage = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseDiary.Diary.DIARYIMAGE));
                pagetitle.setText(itemtitle);
                pagedate.setText(itemmonth + "/" + itemday);
                pagecontents.setText(itemcontecnt);
            }
        }
    }
}
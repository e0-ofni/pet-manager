package com.example.petmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class EditDiaryActivity extends AppCompatActivity {
    private DatabaseHelperDiary dbHelperDiary;

    EditText edittitle, editcontents;
    CalendarView editdate;
    Button adddiary;
    ImageButton editimage;
    int edityear,editmonth,editday;

    private int REQUEST_CODE = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editdiary);
        setTitle("일기장 작성");

        Intent inIntent = getIntent();
        final String itemname = inIntent.getStringExtra("petname");
        dbHelperDiary = new DatabaseHelperDiary(this);

        adddiary = (Button) findViewById(R.id.adddairy);

        adddiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                edittitle = (EditText) findViewById(R.id.edittitle);
                editcontents = (EditText) findViewById(R.id.editcontents);
                editdate = (CalendarView) findViewById(R.id.editdate);

                Calendar calendar = Calendar.getInstance();
                Date curDate = new Date(editdate.getDate());
                calendar.setTime(curDate);

                edityear = calendar.get(Calendar.YEAR);
                editmonth = calendar.get(Calendar.MONTH)+1;
                editday = calendar.get(Calendar.DATE);

                dbHelperDiary.insertRecord(itemname,  edityear, editmonth, editday, edittitle.getText().toString(),"@drawalbe/dog", editcontents.getText().toString());
                finish();
                startActivity(getIntent());
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

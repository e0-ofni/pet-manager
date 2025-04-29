package com.example.petmanager;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Alaramsevice extends AppCompatActivity {

    //알람부분들
    private AlarmManager alarmManager;
    private GregorianCalendar mCalender;
    private TimePicker tp;
    private Calendar c;
    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        mCalender = new GregorianCalendar();


        Log.v("HelloAlarmActivity", mCalender.getTime().toString());

        setContentView(R.layout.activity_alarm);


        //접수일 알람 버튼
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });


    }

    private void setAlarm() {
        //AlarmReceiver에 값 전달
        Intent receiverIntent = new Intent(Alaramsevice.this, AlarmRecevier.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Alaramsevice.this, 0, receiverIntent, 0);
        tp = (TimePicker) findViewById(R.id.tp_timepicker);

        int hour = tp.getCurrentHour();
        int minute = tp.getCurrentMinute();

        String from = hour+":"+minute; //임의로 날짜와 시간을 지정

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date datetime = null;
        try {
            datetime = dateFormat.parse(from);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);

        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);

        Toast.makeText(getApplicationContext(),"알람이 등록되었습니다",Toast.LENGTH_SHORT).show();



    }


}
package com.cso.rutina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView back, memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendar);
        back = findViewById(R.id.back_tv);
        memo = findViewById(R.id.memo_tv);

        if (getIntent().hasExtra("date"))
            calendarView.setDate(getIntent().getLongExtra("date", 0));

        back.setOnClickListener(view -> {
            finish();
        });
    }
}
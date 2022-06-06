package com.cso.rutina.daily;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
//import android.widget.ScrollView;
import android.widget.TextView;

import com.cso.rutina.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DailyCare extends AppCompatActivity {

    //스크롤뷰 사용시 필요
    //ScrollView scrollView;

    //오늘 날짜 불러오기 소스
    //Date todayDate = Calendar.getInstance().getTime(); //해당 년도, 월, 일을 받아온다.
    //SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault()); //Format을 통해 각각 넣어준다.
    //SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
    //SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());

    // Format으로 받아온 값을 String 형식으로 넣어준다.
    //String year = yearFormat.format(todayDate);
    //String month = monthFormat.format(todayDate);
    //String day = dayFormat.format(todayDate);

    //TextView를 받아온다. xml에 있는 textView 아이디 가져옴.
    TextView todayTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_care);

        // 스크롤뷰 소스코드
        //scrollView = findViewById(R.id.scrollView); // 레이아웃에 정의된 뷰 객체 참조
        //scrollView.setVerticalScrollBarEnabled(true); // 수직 스크롤바 사용가능 설정

        Log.i("DATE", "Date Format finish");

        // textView에 원하는 내용을 넣어준다.
        //todayTextView.setText(year + " /  " + month + " / " + day);

        Date currentTime = Calendar.getInstance().getTime();
        String date_text = new SimpleDateFormat("yyyy / MM / dd", Locale.getDefault()).format(currentTime);

        todayTextView = (TextView) findViewById(R.id.today_date);

        todayTextView.setText(date_text);


    }
}
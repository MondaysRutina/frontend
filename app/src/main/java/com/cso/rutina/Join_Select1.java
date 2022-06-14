package com.cso.rutina;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Join_Select1 extends AppCompatActivity {
    Button buttonEvent1, buttonEvent2, buttonEvent3, buttonEvent4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_select1);

        buttonEvent1 = (Button) findViewById(R.id.dry);
        buttonEvent1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonEvent1.setBackgroundColor(Color.TRANSPARENT);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    buttonEvent1.setBackgroundColor(Color.LTGRAY);
                }

                return false;
            }
        });

        buttonEvent2 = (Button) findViewById(R.id.Complexity);
        buttonEvent2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonEvent2.setBackgroundColor(Color.TRANSPARENT);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    buttonEvent2.setBackgroundColor(Color.LTGRAY);
                }

                return false;
            }
        });

        buttonEvent3 = (Button) findViewById(R.id.oily);
        buttonEvent3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonEvent3.setBackgroundColor(Color.TRANSPARENT);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    buttonEvent3.setBackgroundColor(Color.LTGRAY);
                }

                return false;
            }
        });

        buttonEvent4 = (Button) findViewById(R.id.unknown);
        buttonEvent4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonEvent4.setBackgroundColor(Color.TRANSPARENT);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    buttonEvent4.setBackgroundColor(Color.LTGRAY);
                }

                return false;
            }
        });


        Button Next_Button;
        Next_Button = findViewById(R.id.Next_Button);

        //
        Next_Button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Join_Select2.class);
            startActivity(intent);
        });

        // 회원정보 수정 화면 처리를 위한 back
//        ImageView back;
//        back = findViewById(R.id.back);
//
//        //뒤로가기 버튼 클릭시, 회원가입 페이지로 이동
//        back.setOnClickListener(v -> {
//            Intent intent1 = new Intent(this, Join.class);
//            startActivity(intent1);
//        });
    }
}
package com.cso.rutina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Join_Select1 extends AppCompatActivity {
    Button buttonEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_select1);

        buttonEvent = (Button) findViewById(R.id.dry);
//        buttonEvent.setOnTouchListener(new View.OnTouchListener() {
//                                           @Override
//                                           public boolean onTouch(View view, MotionEvent motionEvent) {
//                                               if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                                                   buttonEvent.setBackgroundColor(Color.TRANSPARENT);
//                                               } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                                                   buttonEvent.setBackgroundColor(Color.LTGRAY);
//                                               }
//
//                                               return false;
//                                           }
//                                       });

        //
        Button Next_Button;
        Next_Button = findViewById(R.id.Next_Button);

        //
        Next_Button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Join_Select2.class);
            startActivity(intent);
        });

        // 회원정보 수정 화면 처리를 위한 back
        ImageView back;
        back = findViewById(R.id.back);

        //뒤로가기 버튼 클릭시, 회원가입 페이지로 이동
        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, Join.class);
            startActivity(intent);
        });
    }
}
package com.cso.rutina.powder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.cso.rutina.R;

public class Powder_Room extends AppCompatActivity {
    ImageView add_routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powder_room);

        // 루틴 추가 버튼
        add_routine = findViewById(R.id.add_rutin);

        // 루틴 추가 버튼 클릭 시, 루틴 추가 페이지로 이동
    }
}
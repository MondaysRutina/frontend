package com.cso.rutina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Monthly_Activity extends AppCompatActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly);

        name = findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("id") + "님의 월간보고서 입니다.");
    }
}
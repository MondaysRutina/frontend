package com.cso.rutina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    Button start_btn, register_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_main);

        start_btn = findViewById(R.id.start_btn);
        register_btn = findViewById(R.id.Btn2);

        start_btn.setOnClickListener(view -> {
            startActivity(new Intent(this, Login.class));
        });
        register_btn.setOnClickListener(view -> {
            startActivity(new Intent(this, Join.class));
        });
    }
}

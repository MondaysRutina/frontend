package com.cso.rutina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Join_Select2 extends AppCompatActivity {
    Button Next_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_select2);

        Next_Button = findViewById(R.id.Next_Button);

        Next_Button.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });
    }
}
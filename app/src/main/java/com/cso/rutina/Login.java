package com.cso.rutina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    Button login;
    TextView sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText EmailAddress, Password;
        Button Login;

        // 로그인 버튼
        login = findViewById(R.id.Loginbutton);

        // 로그인 버튼 클릭시, 메인 페이지로 이동
        // test 용으로 join_select1페이지로 이동동
        login.setOnClickListener(v -> {
            Intent intent = new Intent(this, Join_Select1.class);
            startActivity(intent);
        });


       //회원가입 버튼
        sign = findViewById(R.id.signin);

        //회원가입 버튼 클릭시, 회원가입 페이지로 이동
        sign.setOnClickListener(v -> {
            Intent intent = new Intent(this, Join.class);
            startActivity(intent);
        });
    }
}
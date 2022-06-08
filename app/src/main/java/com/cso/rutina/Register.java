package com.cso.rutina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    private Button Register;

    private EditText Email, Password, Name, Age;

    private static String userID, userPassword, userName, userAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 값 가져오기
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Name = findViewById(R.id.Name);
        Age = findViewById(R.id.Age);
        Register = findViewById(R.id.RegisterBtn);

        // 회원가입 버튼이 눌려졌을 때
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 현재 입력된 정보를 string으로 가져오기
                userID = Email.getText().toString();
                userPassword = Password.getText().toString();
                userName = Name.getText().toString();
                userAge = Age.getText().toString();

                // 회원가입 시작
                Response.Listener<String> responseLister = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // String으ㅗㄹ 그냥 못 보내고 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 발송
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("suceess");

                            if (success) { //회원가입이 가능하다면
                                Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), "회원가입이 실패했습니다. 다시 한 번 확인해 주세요.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, userAge, responseLister);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(registerRequest);
            }
        });
    }
}
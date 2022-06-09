package com.cso.rutina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Login extends AppCompatActivity {
    Button Login;
    TextView sign;
    ImageView back;
    private EditText EmailAddress, Password; // 잘못 선언되어 있어 수정함.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 로그인 버튼
        //login = findViewById(R.id.Loginbutton);

        // 로그인 버튼 클릭 - 영주
        EmailAddress = (EditText) findViewById(R.id.EmailAddress);
        Password = (EditText) findViewById(R.id.Password);

        Login = (Button) findViewById(R.id.Loginbutton);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = EmailAddress.getText().toString();
                String pwd = Password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();

                                String id = jsonResponse.getString("id");
                                String pwd = jsonResponse.getString("pwd");
                                //Intent intent = new Intent(getApplicationContext(), Powder_Room.class);

                                // 로그인 화면서 사용자 정보 넘기기
                                //intent.putExtra("id", id);
                                //intent.putExtra("pwd", pwd);
                                //startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(id, pwd, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);
            }
        });


        // back 버튼 클릭시, 메인 페이지로 이동
        back = findViewById(R.id.back);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, TitleActivity.class);
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
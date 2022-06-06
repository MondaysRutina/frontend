package com.cso.rutina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Join extends AppCompatActivity {
    private EditText Join_Email, Password, Check_Password, EmailAddress, Name, Year, Month, Day;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        // 비밀번호 힌트 선택 스피너에 데이터 출력

        // xml에서 지정
        // android:prompt : 스피너에 타이틀메시지 넣기
        // android:entries : 스피너에 사용할 아이템(데이터)를 지정

        // 지역 스피너에 데이터 출력

        // arrays.xml에 정의한 String 배열 (question)을 ArrayAdapter로 바인딩하고 스피너의 모양을 지정
        ArrayAdapter<CharSequence> adapterQuestion = ArrayAdapter
                .createFromResource(this, R.array.question,
                        android.R.layout.simple_spinner_item);

        // ArrayAdapter객체에 할당된 데이터들을 스피너가 클릭될때 보여줄 스피너 아이템의 출력형식을 지정함
        adapterQuestion
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // xml에서 정의한 question 스피너 객체 얻어오기
        final Spinner spinnerQuestion = (Spinner) findViewById(R.id.Pwd_hint_select);
        // 스피너에 타이틀메시지 넣기
        spinnerQuestion.setPrompt("선택");
        // 스피너에 ArrayAdapter를 연결함
        spinnerQuestion.setAdapter(adapterQuestion);

        Button buttonEnter = (Button) findViewById(R.id.JoinButton);
        buttonEnter.setOnClickListener(new enterClickListener());

        back = findViewById(R.id.back);

        //뒤로가기 버튼 클릭시, 로그인 페이지로 이동
        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });

        //회원가입 버튼 클릭시, 다음 회원가입 페이지로 이동
        buttonEnter.setOnClickListener(v -> {
            Intent intent = new Intent(this, Join_Select1.class);
            startActivity(intent);
        });
    }

    class enterClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
                // Toast.makeText(getApplicationContext(), "onclick",
                // Toast.LENGTH_SHORT).show();
                EditText Join_Email = (EditText) findViewById(R.id.Join_Email);
                Spinner Pwd_hint_select = (Spinner) findViewById(R.id.Pwd_hint_select);

                if (Join_Email.getText().toString().trim().equals("")) {
                    showMsg("이메일을 입력해주세요!");
                    Join_Email.requestFocus();
                } else if (Password.getText().toString().trim().equals("")) {
                    showMsg("비밀번호를 입력해주세요!");
                    Password.requestFocus();
                } else if (Check_Password.getText().toString().trim().equals("")) {
                    showMsg("비밀번호를 입력해주세요!");
                    Check_Password.requestFocus();
                } else {
                    showMsg("회원가입이 완료 되었습니다:)");
                }
        }
    }// end 이너클래스 enterClickListener

    public void showMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
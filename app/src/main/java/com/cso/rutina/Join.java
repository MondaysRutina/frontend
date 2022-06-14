package com.cso.rutina;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Join extends AppCompatActivity {
    // 회원가입 버튼
    private Button JoinButton;

    // 출생년도
    TextView ymBtn;
    

    // xml에 있는 사용자로부터 값을 입력 받는edittext
    private EditText Join_Email, Password, Name, Nickname, Pwd_hint_write;

    // php 연동을 위한 string
    private static String id, pwd, name, nickname, question_answer;
    //private static int age;

    // 생년월일 버튼 클릭 시
    public void onClick(View v){
        switch(v.getId()){
            case R.id.ymBtn:
                Intent picker = new Intent(getApplicationContext(),Picker.class);
                startActivityForResult(picker,1000);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // 로그인 화면 처리를 위한 back
        ImageView back;
        back = findViewById(R.id.back);

        //뒤로가기 버튼 클릭시, 로그인 페이지로 이동
        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });
        
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


        // 값 가져오기
        Join_Email = findViewById(R.id.Join_Email);
        Password = findViewById(R.id.Password);
        Name = findViewById(R.id.Name);
        Nickname = findViewById(R.id.Nickname);
        Pwd_hint_write = findViewById(R.id.Pwd_hint_write);
        JoinButton = findViewById(R.id.JoinButton);

        // 생년월일 datepicker 가져오기
        if(Picker.check){
            Intent pickerData = getIntent(); /*데이터 수신*/
            if(pickerData.getExtras().getString("yy")!=null && pickerData.getExtras().getString("mm") != null && pickerData.getExtras().getString("dd") != null){
                String year = pickerData.getExtras().getString("yy");
                String month = pickerData.getExtras().getString("mm");
                String day = pickerData.getExtras().getString("dd");
                ymBtn.setText(year+"년 "+month+"월 "+day+"일");
            }
            else{
                Toast.makeText(getApplicationContext(),"년월일을 다시 입력해주세요",Toast.LENGTH_SHORT).show();
            }
        }

        // 회원가입 버튼이 눌려졌을 때
        JoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 현재 입력된 정보를 string으로 가져오기
                id = Join_Email.getText().toString();
                pwd = Password.getText().toString();
                name = Name.getText().toString();
                // = Integer.parseInt(Age.getText().toString());
                nickname = Nickname.getText().toString();
                question_answer = Pwd_hint_write.getText().toString();
                Log.d("Button", "button click");

                // 회원가입 시작
                Response.Listener<String> responseLister = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("Volley","Volley Response");
                            // String으로 그냥 못 보내고 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 발송
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) { //회원가입이 가능하다면
//                                Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Join.this, Join_Select1.class);
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
                RegisterRequest registerRequest = new RegisterRequest(id, pwd, name, nickname, question_answer, responseLister);
                RequestQueue queue = Volley.newRequestQueue(Join.this);
                queue.add(registerRequest);

            }
        });
    }
}

        // spinner
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_join);




        //Button buttonEnter = (Button) findViewById(R.id.JoinButton);
        //buttonEnter.setOnClickListener(new enterClickListener());


//
//        //회원가입 버튼 클릭시, 다음 회원가입 페이지로 이동
//        Button buttonEnter;
//        buttonEnter.setOnClickListener(v -> {
//            Intent intent = new Intent(this, Join_Select1.class);
//            startActivity(intent);
//        });
//    }
//
//        /*class enterClickListener implements OnClickListener {
//
//            @Override
//            public void onClick(View v) {
//                // Toast.makeText(getApplicationContext(), "onclick",
//                // Toast.LENGTH_SHORT).show();
//                EditText Join_Email = (EditText) findViewById(R.id.Join_Email);
//                Spinner Pwd_hint_select = (Spinner) findViewById(R.id.Pwd_hint_select);
//
//                if (Join_Email.getText().toString().trim().equals("")) {
//                    showMsg("이메일을 입력해주세요!");
//                    Join_Email.requestFocus();
//                } else if (Password.getText().toString().trim().equals("")) {
//                    showMsg("비밀번호를 입력해주세요!");
//                    Password.requestFocus();
//                } else if (Check_Password.getText().toString().trim().equals("")) {
//                    showMsg("비밀번호를 입력해주세요!");
//                    Check_Password.requestFocus();
//                } else {
//                    showMsg("회원가입이 완료 되었습니다:)");
//                }
//            }
//        }// end 이너클래스 enterClickListener*/
//
//        public void showMsg (String msg){
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
//        }
//    }


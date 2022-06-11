package com.cso.rutina;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    // 서버 url 설정 (php 파일 연동)
    final static private String URL= "http://52.78.222.77:8080/Register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String id, String pwd, String name, String nickname, String question_answer,

                           Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("pwd", pwd);
        parameters.put("name",name);
        parameters.put("nickname", nickname);
        //put("age", Integer.toString(age));
        parameters.put("question_answer",question_answer);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        return parameters;
    }
}

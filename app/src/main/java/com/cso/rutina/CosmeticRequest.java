package com.cso.rutina;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CosmeticRequest extends StringRequest {

    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://52.78.222.77:8080/Register.php";
    private Map<String, String> parameters;

    public CosmeticRequest(String api_cosmetic_type, String api_cosmetic_name, String api_cosmetic_ph,
                           Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("api_cosmetic_type", api_cosmetic_type);
        parameters.put("api_cosmetic_name", api_cosmetic_name);
        parameters.put("api_cosmetic_ph", api_cosmetic_ph);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}

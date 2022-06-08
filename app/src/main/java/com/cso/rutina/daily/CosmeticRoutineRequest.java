package com.cso.rutina.daily;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CosmeticRoutineRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://52.78.222.77:8080/routine_list.php";
    private Map<String, String> parameters;

    public CosmeticRoutineRequest(String routine_name, String routine_cosmetic_type, String routine_cosmetic_name, String routine_cosmetic_brand, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("routine_name", routine_name);
        parameters.put("routine_cosmetic_type", routine_cosmetic_type);
        parameters.put("routine_cosmetic_name", routine_cosmetic_name);
        parameters.put("routine_cosmetic_brand", routine_cosmetic_brand);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> parameters = new HashMap<String, String>();
        return parameters;
    }
}

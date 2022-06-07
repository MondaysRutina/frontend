package com.cso.rutina.daily;

import android.graphics.Bitmap;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DailyCareRequest extends StringRequest {
    private final static String URL = "http://52.78.222.77/";
    private Map<String, String> parameters;

    public DailyCareRequest(String id, String daily_report_date, String front_image_path, String left_image_path, String right_image_path, String daily_routine_name, Integer daily_sleep, Integer daily_stress, Integer daily_drink, Integer daily_menstruation, Integer daily_makeup, Integer daily_skin_condition1, Integer daily_skin_condition2, Integer daily_skin_condition3, String daily_memo, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("daily_report_date", daily_report_date);
        parameters.put("front_image_path", front_image_path);
        parameters.put("left_image_path", left_image_path);
        parameters.put("right_image_path", right_image_path);
        parameters.put("daily_routione_name", daily_routine_name);
        parameters.put("daily_sleep", Integer.toString(daily_sleep));
        parameters.put("daily_stress", Integer.toString(daily_stress));
        parameters.put("daily_drink", Integer.toString(daily_drink));
        parameters.put("daily_menstruation", Integer.toString(daily_menstruation));
        parameters.put("daily_makeup", Integer.toString(daily_makeup));
        parameters.put("daily_skin_condition1", Integer.toString(daily_skin_condition1));
        parameters.put("daily_skin_condition2", Integer.toString(daily_skin_condition2));
        parameters.put("daily_skin_condition3", Integer.toString(daily_skin_condition3));
        parameters.put("daily_memo", daily_memo);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}

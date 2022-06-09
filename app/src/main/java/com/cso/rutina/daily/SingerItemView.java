package com.cso.rutina.daily;



import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cso.rutina.R;

public class SingerItemView extends LinearLayout {
    TextView routine_name, cosmetics;

    public SingerItemView(Context context){
        super(context);
        init(context); //인플레이션해서 붙여주는 역
    }

    public SingerItemView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    // 지금 만든 객체(xml 레이아웃)를 인플레이션화(메모리 객체화)해서 붙여줌
    // LayoutInflater를 써서 시스템 서비스를 참조할 수 있음
    // 단말이 켜졌을 때 기본적으로 백그라운드에서 실행시키는 것을 시스템 서비스라고 함.

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_routine, this, true);

        routine_name = findViewById(R.id.routine_name);
        cosmetics = findViewById(R.id.cosmetics);
    }

    public void setRoutine(String routine){
        routine_name.setText(routine);
    }

    public void setCosmetics(String cosmetic){
        cosmetics.setText(cosmetic);
    }



}

package com.cso.rutina;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;


public class HomeFragment extends Fragment {

    TextView random, name;
    CalendarView calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        random = view.findViewById(R.id.random_tv);
        name = view.findViewById(R.id.name_tv);
        calendar = view.findViewById(R.id.calendarView);

        random.setText(random());
        if(requireActivity().getIntent().hasExtra("id")){
            name.setText(requireActivity().getIntent().getStringExtra("id") + "님 환영합니다.");
        }
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(i, i1, i2);
                Long eventOccursOn = calendar.getTimeInMillis();
                moveToCalendar(eventOccursOn);
            }
        });
    }

    private String random() {
        String[] str = {"유통기한이 다 되어가는 화장품이 있어요!", "[공지]서버 점검으로 인한 저장 지연", "오늘의 날씨는 화창해요!", "올영세일 오늘이 마지막 !", "먹지마세요 피부에 양보하세요"};
        Random random = new Random();
        int randomNum = random.nextInt(5);
        return str[randomNum];
    }

    private void moveToCalendar(Long date){
        startActivity(new Intent(requireActivity(), CalendarActivity.class)
                .putExtra("date", date));
    }
}
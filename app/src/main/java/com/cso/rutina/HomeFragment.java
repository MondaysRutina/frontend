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
<<<<<<< HEAD
        String[] str = {"오늘의 날씨가 좋아요", "강아지는 멍멍", "고양이는 야옹야옹", "소는 우우", "병아리는 삐약삐약"};
=======
        String[] str = {"고양이는 귀엽다.", "졸업 시켜주세요 ^^", "졸업은 곧 행복길", "멍멍아 야옹해봐", "강아지는 멍멍"};
>>>>>>> 8e6de77236f9346d4f2380c34647e055eb6c9135
        Random random = new Random();
        int randomNum = random.nextInt(5);
        return str[randomNum];
    }

    private void moveToCalendar(Long date){
        startActivity(new Intent(requireActivity(), CalendarActivity.class)
                .putExtra("date", date));
    }
}
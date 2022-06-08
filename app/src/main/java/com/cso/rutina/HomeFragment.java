package com.cso.rutina;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends Fragment {

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
        String[] str = {"오늘의 날씨가 좋아요", "강아지는 멍멍", "고양이는 야옹야옹", "소는 우우", "병아리는 삐약삐약"};
        Random random = new Random();
        int randomNum = random.nextInt(5);
        return str[randomNum];
    }

    private void moveToCalendar(Long date){
        startActivity(new Intent(requireActivity(), CalendarActivity.class)
                .putExtra("date", date));
    }
}
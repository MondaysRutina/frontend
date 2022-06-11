package com.cso.rutina.daily;

import static android.widget.AdapterView.*;
import static com.cso.rutina.Login.id_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
//import android.widget.ScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cso.rutina.Login;
import com.cso.rutina.LoginRequest;
import com.cso.rutina.MainActivity;
import com.cso.rutina.R;
import com.cso.rutina.Register;
import com.cso.rutina.RegisterRequest;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.Slider;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DailyCare extends AppCompatActivity {

    // 이미지 뷰 선언
    private ImageView leftImageView, centerImageView, rightImageView;

    private File leftFile = null;
    private File centerFile = null;
    private File rightFile = null;
    private Button leftBtn, centerBtn, rightBtn, save;
    private Slider slider;
    private int sliderValue;
    private RadioButton menses_before, menses_start, menses_end, yes, no, yes2, no2, sleep_less, sleep_normal, sleep_many;
    private ChipGroup chipGroup; // chipGroup 선언
    private Chip chip;
    private EditText memo;
    private Button routine_select_btn;
    private TextView routine;

    private final ArrayList<String> chipChoice = new ArrayList<String>();

    // 화장품들 리스트 선언
//    List<Map<String, String>> routineItemList;
//    List<Map<String, String>> cosmeticItemList;
    // 리스트 뷰 선언
//    ListView routine_list = findViewById(R.id.routine_list);
//    SingerAdapter adapter = new SingerAdapter();

    private static final String ROUTINE_TAG_TEXT = "text";
    private static final String COSMETIC_TAG_TEXT = "text";

    List<Map<String, Object>> ItemList;

    String[] cosmetic_text = {"크림","썸바이미레드티트리시카소사이드더마솔루션크림"};
    String[] cosmetic_routine = {"수분루틴", "여드름 루틴", "밤샘 루틴", "진정 루틴"};

    // 서버에 보낼 변수들 선언
    private static String id;
    private static String daily_report_date;
    private static String front_image_path;
    private static String right_image_path;
    private static String daily_routine_name;
    private static String daily_memo;
    private static int camera_button;
    private static String left_image_path;
    private static int daily_sleep;
    private static int daily_stress;
    private static int daily_drink;
    private static int daily_menstruation;
    private static int daily_makeup;
    private static String daily_skin_condition1;
    private static String daily_skin_condition2;
    private static String daily_skin_condition3;

    // 추가 값
    private int menses, yes_no, yes_no2, sleep;

    //TextView를 받아온다. xml에 있는 textView 아이디 가져옴.
    private TextView todayTextView;


    // 카메라
    String TAG = "DailyCare";
    static final int REQUEST_IMAGE_CATURE = 1;

    String leftPhotoPath, centerPhotoPath, rightPhotoPath;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_care);

        // save 버튼 선언
        save = findViewById(R.id.save);

        // 루틴 선택 버튼 선언
        routine_select_btn = findViewById(R.id.routine_select_btn);
        routine = findViewById(R.id.routine);

       // 카메라 시작
        leftImageView = findViewById(R.id.left_camera);
        centerImageView = findViewById(R.id.center_camera);
        rightImageView = findViewById(R.id.right_camera);

        leftBtn = findViewById(R.id.left_camera_button);
        centerBtn = findViewById(R.id.center_camera_button);
        rightBtn = findViewById(R.id.right_camera_button);

        // Slider 선언
        slider = findViewById(R.id.slider);

        // 생리 라디오 버튼 선언
        menses_before = findViewById(R.id.menses_before);
        menses_start = findViewById(R.id.menses_start);
        menses_end = findViewById(R.id.menses_end);

        // 음주 버튼 선언
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);

        // 화장 버튼 선언
        yes2 = findViewById(R.id.yes2);
        no2 = findViewById(R.id.no2);

        // 수면 버튼 선언
        sleep_less = findViewById(R.id.sleep_less);
        sleep_normal = findViewById(R.id.sleep_normal);
        sleep_many = findViewById(R.id.sleep_many);

        // 메모장
        memo = findViewById(R.id.memo);

        // 칩그룹 선언
        chipGroup = findViewById(R.id.chipGroup);

        // Camera
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

        leftBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //takeleftPicture();
                if(ActivityCompat.checkSelfPermission(DailyCare.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    camera_button = 0;
                    takeleftPicture();

                }
            }
        });

        centerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(DailyCare.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    camera_button = 1;
                    takecenterPicture();

                }
            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(DailyCare.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    camera_button = 2;
                    takerightPicture();

                }
            }
        });

        // 루틴 - 맞게 수정 필요
        routine_select_btn.setOnClickListener(new View.OnClickListener() {
            String routine_name, routine_cosmetic_type, routine_cosmetic_name, routine_cosmetic_brand;
            @Override
            public void onClick(View view) {
                showAlertRoutineDialog();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Toast.makeText(getApplicationContext(), "루틴 불러오기 성공했습니다.", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "응답:" + response, Toast.LENGTH_SHORT).show();

                                String routine_name = jsonResponse.getString("routine_name");
                                String routine_cosmetic_type = jsonResponse.getString("routine_cosmetic_type");
                                String routine_cosmetic_name = jsonResponse.getString("routine_cosmetic_name");
                                String routine_cosmetic_brand = jsonResponse.getString("routine_cosmetic_brand");

                                Intent intent = new Intent(getApplicationContext(), DailyCare.class);

                                // 로그인 화면서 사용자 정보 넘기기
                                intent.putExtra("routine_name", routine_name);
                                intent.putExtra("routine_cosmetic_type", routine_cosmetic_type);
                                intent.putExtra("routine_cosmetic_name", routine_cosmetic_name);
                                intent.putExtra("routine_cosmetic_brand", routine_cosmetic_brand);

                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                CosmeticRoutineRequest cosmeticRoutineRequest = new CosmeticRoutineRequest(routine_name, routine_cosmetic_type, routine_cosmetic_name, routine_cosmetic_brand, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(cosmeticRoutineRequest);

            }
        });

        // 루틴
//        routineItemList = new ArrayList<>();
//        cosmeticItemList = new ArrayList<>();
        ItemList = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put(ROUTINE_TAG_TEXT, cosmetic_routine[i]);
            //itemMap.put(COSMETIC_TAG_TEXT, cosmetic_text[i]);

            ItemList.add(itemMap);
        }


        Log.i("DATE", "Date Format finish");

        // textView에 원하는 내용을 넣어준다.
        Date currentTime = Calendar.getInstance().getTime();
        String date_text = new SimpleDateFormat("yyyy / MM / dd", Locale.getDefault()).format(currentTime);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentTime);

        todayTextView = (TextView) findViewById(R.id.today_date);

        todayTextView.setText(date_text);

        // 슬라이더 바 값 가져오기
        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                sliderValue = (int) slider.getValue();
                Log.d("Value of Slider : ", "" + sliderValue);
            }
        });

        menses_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menses_before.isChecked()){
                    menses = 0; // 생리 전
                }
            }
        });

        menses_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 생리 라디오 버튼 값 가져오기
                 if(menses_start.isChecked()){
                    menses = 1; // 생리 시작
                }
            }
        });

        menses_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menses_end.isChecked()){
                    menses = 2;
                }
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yes.isChecked()){
                    Log.d("Drink Log","drink");
                    yes_no = 0;
                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(no.isChecked()){
                    Log.d("Drink Log","no drink");
                    yes_no = 1;
                }
            }
        });

        yes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 화장 확인
                if(yes2.isChecked()){
                    yes_no2 = 0;
                }
            }
        });

        no2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(no2.isChecked()){
                    yes_no2 = 1;
                }
            }
        });

        sleep_less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 수면 확인
                if(sleep_less.isChecked()){
                    sleep = 0;
                }
            }
        });

        sleep_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sleep_normal.isChecked()) {
                    sleep = 1;
                }
            }

        });

        sleep_many.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sleep_many.isChecked()){
                    sleep = 2;
                }
            }
        });

        //Chip
        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            chip = group.findViewById(checkedId);
            if(chip != null){
                Log.d("Chip Group", "check : " + chip.getText());
            }
        });

        getMultipleCheckedFilterChips();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // JSON 값 보내기
                id = "hi";
                //id = id_login;
                daily_report_date = date.toString();
                front_image_path = String.valueOf(centerFile);
                left_image_path = String.valueOf(leftFile);
                right_image_path = String.valueOf(rightFile);
                //daily_routine_name = String.valueOf(routine);
                daily_stress = sliderValue;
                daily_menstruation = menses;
                daily_drink = yes_no;
                daily_makeup = yes_no2;
                daily_sleep= sleep;
                daily_skin_condition1 = chipChoice.get(0);
                daily_skin_condition2 = chipChoice.get(1);
                daily_skin_condition3 = chipChoice.get(2);
                daily_memo = memo.getText().toString();

                Log.d("Result", "ID : " + id + "\n Date :" + daily_report_date);
                Log.d("Result", "left_image_path : " + left_image_path);
                Log.d("Result", "center_image_path : " + front_image_path);
                Log.d("Result", "right_image_path : " + right_image_path);
                Log.d("Result","daily_routine_name : " + daily_routine_name);
                Log.d("Result", "I want sleep : " + sleep);
                Log.d("Result", "daily_stress : " + daily_stress + ", daily_menstruation : " + daily_menstruation);
                Log.d("Result", "daily_drink : " + daily_drink + ", daily_makeup : " + daily_makeup);
                Log.d("Result", "daily memo : " + daily_memo);
                Log.d("Result", "daily_skin_condition1 : " + daily_skin_condition1 + ", daily_skin_condition2 : " + daily_skin_condition2 + ", daily_skin_condition3 : " + daily_skin_condition3);

               //+ daily_drink + daily_makeup + daily_sleep + daily_skin_condition1 + daily_skin_condition2 + daily_skin_condition3 + daily_memo);

                // 데일리 정보 서버 전송 시작
                Response.Listener<String> responseLister = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // String으로 그냥 못 보내고 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 발송
                            Log.d("Daily Care", "통신 성공");
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean successAll = jsonResponse.getBoolean("successAll");

                            if (successAll) { // 오류는 국내산... 코드는 스택 오버플로우산...
                                Log.d("Daily Care", "저장 성공");
                                Toast.makeText(getApplicationContext(), "데일리 케어 저장이 완료되었습니다.", Toast.LENGTH_SHORT).show();


//                                Intent intent = new Intent(DailyCare.this, MainActivity.class);
//                                startActivity(intent);
//                                finish();

                            } else {
                                Log.d("Daily Care", "저장 실패");
                                Toast.makeText(getApplicationContext(), "저장이 실패했습니다. 다시 한 번 확인해 주세요.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                Log.d("Daily Care", "볼리 통신 성공");
                DailyCareRequest dailyCareRequest = new DailyCareRequest(id, daily_report_date, front_image_path, left_image_path, right_image_path, daily_routine_name, daily_sleep, daily_stress, daily_drink, daily_menstruation, daily_makeup, daily_skin_condition1, daily_skin_condition2, daily_skin_condition3, daily_memo, responseLister);
                RequestQueue queue = Volley.newRequestQueue(DailyCare.this);
                queue.add(dailyCareRequest);
            }
        });
    }

//    abstract class SingerAdapter extends BaseAdapter{
//        // 데이터가 들어가 있지 않고, 어떻게 담을지만 정의해뒀다.
//        ArrayList<SingleItem> items = new ArrayList<SingleItem>();
//
//        public int getCount(){
//            return items.size();
//        }
//
//        public void additem(SingleItem item){
//            items.add(item);
//        }
//
//        public Object getItem(int position){
//            return items.get(position);
//        }
//
//        public long getitemID(int position){
//            return position;
//        }
//
//        // 어뎁터가 데이터를 관리하고 뷰도 만듬
//        public View getView(int position, View convertView, ViewGroup parent){
//            SingerItemView singerItemView = null;
//            // 코드를 재사용할 수 있도록
//            if(convertView == null){
//                singerItemView = new SingerItemView(getApplicationContext());
//            } else {
//                singerItemView = (SingerItemView) convertView;
//             }
//
//            SingleItem item = items.get(position);
//            singerItemView.setRoutine(item.getRoutine());
//            singerItemView.setCosmetics(item.getCosmetic());
//            return singerItemView;
//        }
//    }

    // 루틴
    private void showAlertRoutineDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DailyCare.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_routine, null);
        builder.setView(view);

        final ListView listView = (ListView) view.findViewById(R.id.routine_list);
        final AlertDialog dialog = builder.create();

        SimpleAdapter simpleAdapter = new SimpleAdapter(DailyCare.this, ItemList, R.layout.list_routine, new String[]{ROUTINE_TAG_TEXT,COSMETIC_TAG_TEXT}, new int[]{R.id.routine_name, R.id.cosmetics});

        //SimpleAdapter simpleAdapter = new SimpleAdapter(DailyCare.this, routineItemList, R.layout.list_routine, new String[]{ROUTINE_TAG_TEXT, COSMETIC_TAG_TEXT}, new int[]{R.id.routine_name, R.id.cosmetics});


        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                routine.setText("오늘의 루틴 : " + cosmetic_routine[position]);
                dialog.dismiss();
                daily_routine_name = String.valueOf(cosmetic_routine[position]);

            }
        });

        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void getMultipleCheckedFilterChips(){
        for(int i = 0; i < chipGroup.getChildCount(); i++){
            Chip chip = (Chip) chipGroup.getChildAt(i);

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        chipChoice.add(buttonView.getText().toString());
                    } else{
                        chipChoice.remove(buttonView.getText().toString());
                    }

                    if(!chipChoice.isEmpty()){
                        Log.d("chip","Chip :" + chipChoice.toString());
                        //Toast.makeText(DailyCare.this, chipChoice.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void takeleftPicture(){
        Intent takeleftPictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takeleftPictureIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // 사진 여러개 헝ㅅㅇ
        if(takeleftPictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takeleftPictureIntent, REQUEST_IMAGE_CATURE);
        }
    }

    private void takecenterPicture(){
        Intent takecenterPictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takecenterPictureIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // 사진 여러개 허용
        if(takecenterPictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takecenterPictureIntent, REQUEST_IMAGE_CATURE);
        }
    }

    private void takerightPicture(){
        Intent takerightPictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takerightPictureIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // 사진 여러개 허용
        if(takerightPictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takerightPictureIntent, REQUEST_IMAGE_CATURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // intent에서 반환된 이미지 객체 가져오기
        if(requestCode == REQUEST_IMAGE_CATURE && resultCode == RESULT_OK) {//좌측
            //Uri uri = data.getData();
            Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");

            if(camera_button == 0){
                leftImageView.setImageBitmap(imageBitmap);
            } else if (camera_button == 1){
                centerImageView.setImageBitmap(imageBitmap);
            } else if(camera_button == 2){
                rightImageView.setImageBitmap(imageBitmap);
            }

            try {
                if(camera_button == 0) {
                    leftFile = leftImageFile();
                } else if(camera_button == 1){
                    centerFile = centerImageFile();
                } else if (camera_button == 2) {
                    rightFile = rightImageFile();
                }
                Log.d("Success", "Image Success" + leftFile);
            } catch (IOException e) {
                Log.d("Error", "File Path Error");
            }

        }

    }

    private File leftImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat") String leftTimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String leftImageFileName = "JPEG_" + leftTimeStamp + "_";
        File leftImage = File.createTempFile(
                leftImageFileName, /* prefix */
                ".jpg" /* suffix */
        );

        // Save a file : path for use with ACTION_VIEW intents
        leftPhotoPath = leftImage.getAbsolutePath();
        return leftImage;

    }

    private File centerImageFile() throws IOException{
        @SuppressLint("SimpleDateFormat") String centerTimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String centerImageFileName = "JPEG_" + centerTimeStamp + "_";
        File centerImage = File.createTempFile(
                centerImageFileName, /* prefix */
                ".jpg" /* suffix */
        );

        // Save a file : path for use with ACTION_VIEW intents
        centerPhotoPath = centerImage.getAbsolutePath();
        return centerImage;
    }

    private File rightImageFile() throws IOException{
        @SuppressLint("SimpleDateFormat") String rightTimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String rightImageFileName = "JPEG_" + rightTimeStamp + "_";
        File rightImage = File.createTempFile(
                rightImageFileName, /* prefix */
                ".jpg" /* suffix */
        );

        // Save a file : path for use with ACTION_VIEW intents
        rightPhotoPath = rightImage.getAbsolutePath();
        return rightImage;
    }

}
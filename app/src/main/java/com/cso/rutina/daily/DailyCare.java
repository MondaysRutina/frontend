package com.cso.rutina.daily;

import static com.cso.rutina.Login.id_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
//import android.widget.ScrollView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cso.rutina.R;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.Slider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    private ChipGroup chipGroup;
    private EditText memo;

    private final ArrayList<Integer> chipChoice = new ArrayList<>();

    // 서버에 보낼 변수들 선언
    private static String id;
    private static String daily_report_date;
    private static File front_image_path;
    private static File right_image_path;
    private static String daily_routine_name;
    private static String daily_memo;
    private static int camera_button;
    private static File left_image_path;
    private static int daily_sleep, daily_stress, daily_drink, daily_menstruation, daily_makeup, daily_skin_condition1, daily_skin_condition2, daily_skin_condition3;

    // 추가 값
    private int menses, yes_no, yes_no2, sleep;

    //TextView를 받아온다. xml에 있는 textView 아이디 가져옴.
    private TextView todayTextView;

//    Uri leftUri, centerUri, ruightUri;

    // 카메라
    String TAG = "DailyCare";
    //ProcessCameraProvider processCameraProvider;
    //int lensFacing = CameraSelector.LENS_FACING_BACK;
    static final int REQUEST_IMAGE_CATURE = 1;


    String leftPhotoPath, centerPhotoPath, rightPhotoPath;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_care);

        // save 버튼 선언
        save = findViewById(R.id.save);

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


        //getMultipleCheckedFilterChips();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // JSON 값 보내기
                id = id_login;
                daily_report_date = date.toString();
                front_image_path = centerFile;
                left_image_path = leftFile;
                right_image_path = rightFile;
                daily_routine_name = null;
                daily_stress = sliderValue;
                daily_menstruation = menses;
                daily_drink = yes_no;
                daily_makeup = yes_no2;
                daily_sleep= sleep;
                //daily_skin_condition1 = chipChoice.get(0);
                //daily_skin_condition2 = chipChoice.get(1);
                //daily_skin_condition3 = chipChoice.get(2);
                daily_memo = memo.getText().toString();

                Log.d("Result", "ID : " + id + "\n Date :" + daily_report_date);
                Log.d("Result", "left_image_path : " + left_image_path);
                Log.d("Result", "center_image_path : " + front_image_path);
                Log.d("Result", "right_image_path : " + right_image_path);
                Log.d("Result", "daily_stress : " + daily_stress + ", daily_menstruation : " + daily_menstruation);
                Log.d("Result", "daily_drink : " + daily_drink + ", daily_makeup : " + daily_makeup);
                Log.d("Result", "daily memo : " + daily_memo);
               //+ daily_drink + daily_makeup + daily_sleep + daily_skin_condition1 + daily_skin_condition2 + daily_skin_condition3 + daily_memo);
            }
        });




    }

//    private void getMultipleCheckedFilterChips(){
//        for(int i = 0; i < chipGroup.getChildCount(); i++){
//            Chip chip = (Chip) chipGroup.getChildAt(i);
//
//            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    if(b) {
//                        chipChoice.add(Integer.valueOf(compoundButton.getText().toString()));
//                    } else{
//                        chipChoice.remove(Integer.valueOf(compoundButton.getText().toString()));
//                    }
////
////                    if(chipChoice.isEmpty()){
//                        Toast.makeText(DailyCare.this, chipChoice.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }

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
//        } else if(requestCode == REQUEST_IMAGE_CATURE2 && resultCode == RESULT_OK){
//            Bundle centerextras = data.getExtras();
//            Bitmap centerimageBitmap = (Bitmap) centerextras.get("data");
//
//            centerImageView.setImageBitmap(centerimageBitmap);
//        } else if(requestCode == REQUEST_IMAGE_CATURE3 && resultCode == RESULT_OK){
//            Bundle rightextras = data.getExtras();
//            Bitmap rightImageBitmap = (Bitmap) rightextras.get("data");
//
//            rightImageView.setImageBitmap(rightImageBitmap);
//        }

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
//    void takeleftPicture(){
//        leftImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        CameraSelector cameraSelector = new CameraSelector.Builder()
//                .requireLensFacing(lensFacing)
//                .build();
//
//        ImageView imageView = new ImageView(this)
//                .setTargetAspectRatio(AsepectRatio.RATIO_4_3)
//                .
//    }

//    public void takeleftPicture(){
//        try{
//            leftFile = createleftFile();
//
//            if(leftFile.exists()){
//                leftFile.delete();
//            }
//
//            leftFile.createNewFile();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        // leftFile 객체로부터 Uri 객체 만들기
//        if(Build.VERSION.SDK_INT >= 24){
//            leftUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, leftFile);
//        } else {
//            leftUri = Uri.fromFile(leftFile);
//        }
//
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, leftUri);
//        startActivityForResult(intent, 101); //사진 찍기 화면 띄우기
//
//    }

//    public void takecenterPicture(){
//        try{
//            centerFile = createcenterFile();
//        } catch (Exception e){
//
//        }
//
//    }

//    public void takerightPicture(){
//        try{
//            rightFile = createrightFile();
//        } catch (Exception e){
//
//        }
//
//    }

//    private File createleftFile(){
//        String filename = "capture.jpg";
//        File outFile = new File(getFilesDir(), filename);
//        Log.d("Main", "File path : " + outFile.getAbsolutePath());
//
//        return outFile;
//
//    }

    //private File createcenterFile(){

    //}

    //private File createrightFile(){

    //}

//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == 101 && resultCode ==RESULT_OK){
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inSampleSize = 8;
//            // 이미지 파일을 Bitmap 객체로 만들기
//            Bitmap bitmap = BitmapFactory.decodeFile(leftFile.getAbsolutePath(), options);
//
//            // 이미지 뷰에 Bitmap 설정하기
//            leftImageView.setImageBitmap(bitmap);
//        }
//    }


}
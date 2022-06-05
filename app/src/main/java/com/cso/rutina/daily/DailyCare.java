package com.cso.rutina.daily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
//import android.widget.ScrollView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.BuildConfig;
import com.cso.rutina.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class DailyCare extends AppCompatActivity {
    //CameraSurfaceView cameraView;

    // 이미지 뷰 선언
    ImageView leftImageView, centerImageView, rightImageView;
    File leftFile, centerFile, rightFile;
    Button leftBtn, centerBtn, rightBtn;

    //TextView를 받아온다. xml에 있는 textView 아이디 가져옴.
    TextView todayTextView;
//    Uri leftUri, centerUri, ruightUri;

    // 카메라
    String TAG = "DailyCare";
    //ProcessCameraProvider processCameraProvider;
    //int lensFacing = CameraSelector.LENS_FACING_BACK;
    static final int REQUEST_IMAGE_CATURE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_care);

//        // 카메라 시작
        leftImageView = findViewById(R.id.left_camera);
//        centerImageView = findViewById(R.id.center_camera);
//        rightImageView = findViewById(R.id.right_camera);
//
        leftBtn = findViewById(R.id.left_camera_button);
//        centerBtn = findViewById(R.id.center_camera_button);
//        rightBtn = findViewById(R.id.right_camera_button);
//

        // Camera
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);



//        centerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                takecenterPicture();
//            }
//        });
//
//        rightBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                takerightPicture();
//            }
//        });

//        try{
//            processCameraProvider = ProcessCameraProvider.getInstance(this).get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        leftBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //takeleftPicture();
                if(ActivityCompat.checkSelfPermission(DailyCare.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    takeleftPicture();
                }
            }
        });


        Log.i("DATE", "Date Format finish");

        // textView에 원하는 내용을 넣어준다.
        Date currentTime = Calendar.getInstance().getTime();
        String date_text = new SimpleDateFormat("yyyy / MM / dd", Locale.getDefault()).format(currentTime);

        todayTextView = (TextView) findViewById(R.id.today_date);

        todayTextView.setText(date_text);


    }

    private void takeleftPicture(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CATURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // intent에서 반환된 이미지 객체 가져오기
        if(requestCode == REQUEST_IMAGE_CATURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap)extras.get("data");

            //leftImageView = new ImageView(this);
            leftImageView.setImageBitmap(imageBitmap);
            //leftImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        }
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
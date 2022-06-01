package com.cso.rutina.daily;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.cso.rutina.R;

import java.io.File;

public class SliderItems extends AppCompatActivity {
    ImageView imageView;
    File file;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_item_container);

        imageView = findViewById(R.id.left_camera);

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                takePicture();
            }
        });
    }

    public void takePicture(){
        // File 객체로부터 Uri 객체 만들기기
        if(Build.VERSION.SDK_INT >= 24){
            //uri = FileProvider(this)
        }
    }




    //아마 카메라 기능 추가해야할 듯
    private String image;
    SliderItems(int image){
        this.image = "@drawable/camera_select.png";
    }
    public String getImage(){
        return image;
    }
}

package com.cso.rutina.daily;

public class SliderItems {
    //아마 카메라 기능 추가해야할 듯
    private String image;
    SliderItems(int image){
        this.image = "@drawable/camera_select.png";
    }
    public String getImage(){
        return image;
    }
}

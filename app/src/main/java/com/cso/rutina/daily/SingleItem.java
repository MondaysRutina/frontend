package com.cso.rutina.daily;

public class SingleItem {
    String routine;
    String cosmetic;


    // 생성
    public SingleItem(String routine, String cosmetic){
        this.routine = routine;
        this.cosmetic = cosmetic;
    }

    // 변수에 접근할 때 접근하기보다는 안전하게 getter, setter를 이용합니다.
    public String getRoutine(){
        return routine;
    }

    public void setRoutine(String routine){
        this.routine = routine;
    }

    public String getCosmetic(){
        return cosmetic;
    }

    public void setCosmetic(String cosmetic){
        this.cosmetic = cosmetic;
    }


    public String toString(){
        return "SingleItem{" +
                "routine = '" + routine + '\'' +
                ", cosmetic = '" + cosmetic + '\'' +
                '}';
    }
}

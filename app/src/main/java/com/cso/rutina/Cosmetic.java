package com.cso.rutina;

public class Cosmetic {
    String api_cosmetic_type;
    String api_cosmetic_name;
    String api_cosmetic_ph;

    public String getApi_cosmetic_type() {
        return api_cosmetic_type;
    }

    public void setApi_cosmetic_type(String api_cosmetic_type) {
        this.api_cosmetic_type = api_cosmetic_type;
    }

    public String getApi_cosmetic_name() {
        return api_cosmetic_name;
    }

    public void setApi_cosmetic_name(String api_cosmetic_name) {
        this.api_cosmetic_name = api_cosmetic_name;
    }

    public String getApi_cosmetic_ph() {
        return api_cosmetic_ph;
    }

    public void setApi_cosmetic_ph(String api_cosmetic_ph) {
        this.api_cosmetic_ph = api_cosmetic_ph;
    }

    public Cosmetic(String api_cosmetic_type, String api_cosmetic_name, String api_cosmetic_ph) {
        this.api_cosmetic_type = api_cosmetic_type;
        this.api_cosmetic_name = api_cosmetic_name;
        this.api_cosmetic_ph = api_cosmetic_ph;
    }

    public static class CosmeticRequest {
//        CosmeticRequest
    }
}
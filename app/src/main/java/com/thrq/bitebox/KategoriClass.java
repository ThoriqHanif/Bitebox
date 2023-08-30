package com.thrq.bitebox;

public class KategoriClass {


    private String dataNama;
    private String key;


    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }


//    private String dataImage;


    public String getDataNama()
    {
        return dataNama;
    }


//    public String getDataImage() {
//        return dataImage;
//    }


    public KategoriClass(String dataNama){
        this.dataNama = dataNama;
//        this.dataImage = dataImage;
    }

    public KategoriClass(){

    }
}

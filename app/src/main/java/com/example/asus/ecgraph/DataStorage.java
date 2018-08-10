package com.example.asus.ecgraph;



public class DataStorage {

    private static final DataStorage instance = new DataStorage() ;
    private String myData;

    private  DataStorage(){
        myData = "";
    }

    public static DataStorage getInstance(){ return instance; }
    public void setMyData(String data){
        myData = data;
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + data);
    }

    public String getMyData(){
        return myData;
    }
}

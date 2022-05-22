package com.example.turi_main;

public class UserInfo {
    private String photoUrl;
    private String name;
    private String birthday;
    private String hpNumber;

    public UserInfo(String name, String birthday, String hpNumber) {
        this.name = name;
        this.birthday = birthday;
        this.hpNumber = hpNumber;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getBirthday(){
        return this.birthday;
    }
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    public String getHpNumber(){
        return this.hpNumber;
    }
    public void setHpNumber(String hpNumber){
        this.hpNumber = hpNumber;
    }

    public  UserInfo(String photoUrl){
        this.photoUrl = photoUrl;
    }
    public String getPhotoUrl(){
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }

}

package com.example.phonebook.model;

import java.io.Serializable;

public class Member implements Serializable {
    private String name;
    private String tel;
    private Integer imgRes;
    private int phoneType; //전화종류: 핸드폰, 집전화, 회사전화

    public Member() {}

    public Member(String name, String tel, int phoneType) {
        this.name = name;
        this.tel = tel;
        this.phoneType = phoneType;
    }

    public Member(String name, String tel, Integer imgRes, int phoneType) {
        this.name = name;
        this.tel = tel;
        this.imgRes = imgRes;
        this.phoneType = phoneType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getImgRes() {
        return imgRes;
    }

    public void setImgRes(Integer imgRes) {
        this.imgRes = imgRes;
    }

    public int getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(int phoneType) {
        this.phoneType = phoneType;
    }

    @Override
    public String toString() {
        return
                "이름:" + name + "    "+
                " 전화번호:" + tel + "    ";
    }
}

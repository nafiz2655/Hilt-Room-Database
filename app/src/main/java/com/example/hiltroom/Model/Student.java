package com.example.hiltroom.Model;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String roll;
    String reg;
    String subject;
    String phone;
    String address;
    String image;


    public Student() {
    }

    public Student(String name, String roll, String reg, String subject, String phone, String address, String image) {
        this.name = name;
        this.roll = roll;
        this.reg = reg;
        this.subject = subject;
        this.phone = phone;
        this.address = address;
        this.image = image;
    }

    public Student(int id, String name, String roll, String reg, String subject, String phone, String address, String image) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.reg = reg;
        this.subject = subject;
        this.phone = phone;
        this.address = address;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

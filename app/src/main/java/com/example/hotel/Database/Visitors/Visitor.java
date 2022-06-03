package com.example.hotel.Database.Visitors;


import androidx.core.util.Pair;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "visitor")
public class Visitor {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String surname;
    private String countDay;
    private int phone;

    public Visitor(String name, String surname, String countDay, int phone) {
        this.name = name;
        this.surname = surname;
        this.countDay = countDay;
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getCountDay() {
        return countDay;
    }

    public void setCountDay(String countDay) {
        this.countDay = countDay;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}

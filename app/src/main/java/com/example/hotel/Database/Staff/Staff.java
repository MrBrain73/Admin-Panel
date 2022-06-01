package com.example.hotel.Database.Staff;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "staff")
public class Staff {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String surname;

    public Staff(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

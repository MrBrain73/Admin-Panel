package com.example.hotel.Database.Rooms;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "room")
public class Room {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int numberRoom;
    private String classRoom;
    private int capacityRoom;
    private int priceRoom;
    private boolean isTaken;

    public Room(int numberRoom, String classRoom,
                int capacityRoom, int priceRoom, boolean isTaken) {
        this.capacityRoom = capacityRoom;
        this.numberRoom = numberRoom;
        this.classRoom = classRoom;
        this.priceRoom = priceRoom;
        this.isTaken = isTaken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public int getCapacityRoom() {
        return capacityRoom;
    }

    public void setCapacityRoom(int capacityRoom) {
        this.capacityRoom = capacityRoom;
    }

    public int getPriceRoom() {
        return priceRoom;
    }

    public void setPriceRoom(int priceRoom) {
        this.priceRoom = priceRoom;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}

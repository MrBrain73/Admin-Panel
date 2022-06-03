package com.example.hotel.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hotel.Database.Rooms.Room;
import com.example.hotel.Database.Staff.Staff;
import com.example.hotel.Database.Visitors.Visitor;

import java.util.List;

public class AppDbViewModel extends AndroidViewModel {

    private AppRepository roomRepository;
    private final LiveData<List<Room>> allRooms;
    private final LiveData<List<Staff>> allStaffs;
    private final LiveData<List<Visitor>> allVisitors;

    public AppDbViewModel(@NonNull Application application) {
        super(application);
        roomRepository = new AppRepository(application);
        allRooms = roomRepository.getAllRooms();
        allStaffs = roomRepository.getAllStaffs();
        allVisitors = roomRepository.getAllVisitors();
    }

    public LiveData<List<Room>> getAllRooms() { return allRooms; }
    public LiveData<List<Staff>> getAllStaffs() {return  allStaffs;}
    public LiveData<List<Visitor>> getAllVisitors() {return  allVisitors;}

    public LiveData<Room> getRoomByNum(int number) { return roomRepository.getRoomByNum(number); }

    public void updateItem(Room room) {roomRepository.updateItem(room);}

    public void insert(Room room) { roomRepository.insert(room); }
    public void insert(Staff staff) { roomRepository.insert(staff);}
    public void insert(Visitor visitor) { roomRepository.insert(visitor); }

    public void deleteAllRoom() { roomRepository.deleteAllRoom(); }
    public void deleteAllStaff() { roomRepository.deleteAllStaff();}
    public void deleteAllVisitor() {roomRepository.deleteAllVisitor();}
}

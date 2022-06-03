package com.example.hotel.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.hotel.Database.Rooms.Room;
import com.example.hotel.Database.Rooms.RoomDao;
import com.example.hotel.Database.Staff.Staff;
import com.example.hotel.Database.Staff.StaffDao;
import com.example.hotel.Database.Visitors.Visitor;
import com.example.hotel.Database.Visitors.VisitorDao;

import java.util.List;

class AppRepository {

    private RoomDao roomDao;
    private LiveData<List<Room>> allRooms;

    private StaffDao staffDao;
    private LiveData<List<Staff>> allStaffs;

    private VisitorDao visitorDao;
    private LiveData<List<Visitor>> allVisitors;

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        roomDao = db.roomDao();
        allRooms = roomDao.getAll();

        staffDao = db.staffDao();
        allStaffs = staffDao.getAll();

        visitorDao = db.visitorDao();
        allVisitors = visitorDao.getAll();
    }

    LiveData<List<Room>> getAllRooms() {
        return allRooms;
    }
    LiveData<List<Staff>> getAllStaffs() {return allStaffs;}
    LiveData<List<Visitor>> getAllVisitors() {return allVisitors;}

    LiveData<Room> getRoomByNum(int number) { return roomDao.getRoomByNumber(number); }

    void updateItem(Room room) {
        AppDatabase.databaseWriteExecutor.execute(() -> roomDao.updateItem(room));
    }

    void insert(Room room) {
        AppDatabase.databaseWriteExecutor.execute(() -> roomDao.insert(room));
    }

    void insert(Staff staff) {
        AppDatabase.databaseWriteExecutor.execute(() -> staffDao.insert(staff));
    }

    void insert(Visitor visitor) {
        AppDatabase.databaseWriteExecutor.execute(() -> visitorDao.insert(visitor));
    }

    void deleteAllRoom() {
        AppDatabase.databaseWriteExecutor.execute(() -> roomDao.deleteAllRoom());
    }

    void deleteAllStaff() {
        AppDatabase.databaseWriteExecutor.execute(() -> staffDao.deleteAllStaff());
    }

    void deleteAllVisitor() {
        AppDatabase.databaseWriteExecutor.execute(() -> visitorDao.deleteAllVisitor());
    }
}

package com.example.hotel.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hotel.Database.Rooms.RoomDao;
import com.example.hotel.Database.Staff.Staff;
import com.example.hotel.Database.Staff.StaffDao;
import com.example.hotel.Database.Visitors.Visitor;
import com.example.hotel.Database.Visitors.VisitorDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {com.example.hotel.Database.Rooms.Room.class, Staff.class, Visitor.class},
        version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoomDao roomDao();
    public abstract StaffDao staffDao();
    public abstract VisitorDao visitorDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "rooms_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

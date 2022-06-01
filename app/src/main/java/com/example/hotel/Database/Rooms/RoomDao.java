package com.example.hotel.Database.Rooms;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Room room);

    @Query("DELETE FROM room")
    void deleteAllRoom();

    @Query("SELECT * FROM room")
    LiveData<List<Room>> getAll();
}

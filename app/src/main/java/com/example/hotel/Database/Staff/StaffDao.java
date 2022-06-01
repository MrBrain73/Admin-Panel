package com.example.hotel.Database.Staff;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StaffDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Staff staff);

    @Query("DELETE FROM staff")
    void deleteAllStaff();

    @Query("SELECT * FROM staff")
    LiveData<List<Staff>> getAll();

}

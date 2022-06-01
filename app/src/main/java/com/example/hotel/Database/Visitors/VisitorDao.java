package com.example.hotel.Database.Visitors;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VisitorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Visitor visitor);

    @Query("DELETE FROM visitor")
    void deleteAllVisitor();

    @Query("SELECT * FROM visitor")
    LiveData<List<Visitor>> getAll();
}

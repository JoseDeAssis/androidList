package com.example.exemplocrudtenis;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TennisDao {

    @Insert
    long insert(Tennis tennis);

    @Update
    void update(Tennis... tennis);

    @Delete
    void delete(Tennis... tennis);

    @Query("SELECT * FROM tennis_table")
    LiveData<List<Tennis>> getAllTennis();
}
package com.studen.bestfood;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CafeteriaInfoDao {
    @Query("SELECT * FROM cafeteriaInfo WHERE id = :id")
    CafeteriaInfo findById(int id);

    @Query("SELECT * FROM cafeteriaInfo")
    List<CafeteriaInfo> getAll();

    @Insert
    void insert(CafeteriaInfo cafeteriaInfo);

    @Update
    void update(CafeteriaInfo cafeteriaInfo);

    @Delete
    void delete(CafeteriaInfo cafeteriaInfo);

}

package com.example.navdrawer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.navdrawer.model.Coche;

import java.util.List;

@Dao
public interface CocheDao {
    @Query("SELECT * FROM coche")
    List<Coche> getAllCoches();

    @Query("SELECT * FROM coche WHERE id LIKE :uuid")
    Coche getCoche(String uuid);

    @Insert
    void addCoche(Coche c);

    @Delete
    void deleteCoche(Coche c);

    @Update
    void updateCoche(Coche c);
}

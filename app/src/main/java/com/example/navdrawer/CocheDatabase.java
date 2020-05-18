package com.example.navdrawer;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.navdrawer.model.Coche;


@Database(entities = {Coche.class}, version = 1)
public abstract class CocheDatabase extends RoomDatabase {
    public abstract CocheDao getCocheDaoDB();
}


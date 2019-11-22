package com.example.periodictable.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.periodictable.Element;
// Establishing the database
@Database(entities = {Element.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ElementDao elementDao();

    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "elementDb").build();
        }
        return instance;
    }
}

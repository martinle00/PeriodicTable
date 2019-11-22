package com.example.periodictable.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.periodictable.Element;

import java.util.List;
// Queries to obtain data
@Dao
public interface ElementDao {

    @Query("SELECT * FROM element ORDER BY atomicNumber ASC")
    List<Element> getAll();

    @Query("SELECT * FROM element WHERE atomicNumber = :atomicNumber")
    Element findElementByAtomicNumber(int atomicNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Element> books);

    @Delete
    void delete(Element element);
}

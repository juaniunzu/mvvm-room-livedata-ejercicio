package com.example.mvvm_livedata_room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//se suele crear un dao por cada entity (o sea, un dao por cada pojo hecho tabla)
@Dao
public interface NoteDao {

    //solo le aclaramos q es insert, Room se encarga de hacer por atras el proceso
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    //room no tiene una anotacion para "borrar t0do, asique se crea manual
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority_column DESC")
    LiveData<List<Note>> getAllNotes();



}

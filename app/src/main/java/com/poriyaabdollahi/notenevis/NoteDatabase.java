package com.poriyaabdollahi.notenevis;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;
    public abstract NoteDAO noteDAO();


    public static synchronized NoteDatabase getInstance(Context context){
        if (instance== null){
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note-database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

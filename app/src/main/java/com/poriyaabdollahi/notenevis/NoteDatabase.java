package com.poriyaabdollahi.notenevis;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;
    public abstract NoteDAO noteDAO();


    public static synchronized NoteDatabase getInstance(Context context){

        if (instance== null){
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note-database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new  RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull  SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };
    public static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDAO noteDAO;

        public PopulateDBAsyncTask(NoteDatabase database) {
            noteDAO = database.noteDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.insert(new Note("title 1 ","salam chetori",1));
            noteDAO.insert(new Note("title 2 ","salam chetori",1));
            noteDAO.insert(new Note("title 3 ","salam chetori",1));
            return null;
        }
    }
}

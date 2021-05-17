package com.poriyaabdollahi.notenevis;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDAO noteDAO;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDAO = database.noteDAO();
        allNotes= noteDAO.getAllNotes();
    }

    public void insert(Note note){
        new InsertNoteAsyncTask(noteDAO).execute(note);
    }
    public void update(Note note){
        new UpdateNoteAsyncTask(noteDAO).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDAO).execute(note);
    }
    public void  deleteAllNotes(){
        new DeleteAllNoteAsyncTask(noteDAO).execute();
    }
//live data doesn't  need thread handling (already handled)
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }





    public static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDAO noteDAO;

        private InsertNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO=noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.insert(notes[0]);
            return null;
        }
    }
    public static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDAO noteDAO;

        private DeleteNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO=noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.delete(notes[0]);
            return null;
        }
    }
    public static class DeleteAllNoteAsyncTask extends AsyncTask<Void,Void,Void> {
        private NoteDAO noteDAO;

        private DeleteAllNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO=noteDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.deleteAllNotes();
            return null;
        }
    }
    public static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDAO noteDAO;

        private UpdateNoteAsyncTask(NoteDAO noteDAO){
            this.noteDAO=noteDAO;
        }

        @Override
        protected Void doInBackground(Note... Notes) {
            noteDAO.update(Notes[0]);
            return null;
        }
    }
}

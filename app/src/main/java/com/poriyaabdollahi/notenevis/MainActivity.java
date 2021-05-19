package com.poriyaabdollahi.notenevis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST= 1;
    private NoteViewModel noteViewModel;
    FloatingActionButton buttonAddNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
                startActivityForResult(intent,ADD_NOTE_REQUEST);
            }

        });


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager ly = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(ly);
        recyclerView.setHasFixedSize(true);

        NoteAdapter noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);



        noteViewModel= new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(NoteViewModel.class);



        noteViewModel.getAllNotes().observe(this, notes -> {
            //RecyclerView
            noteAdapter.setNotes(notes);
            Toast.makeText(MainActivity.this, "changed", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== ADD_NOTE_REQUEST && resultCode== RESULT_OK){
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY,1);
            Note note = new Note(title,description,priority);
            noteViewModel.insert(note);
            Toast.makeText(this, "note Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "note note saved", Toast.LENGTH_SHORT).show();
            
        }
    }
}
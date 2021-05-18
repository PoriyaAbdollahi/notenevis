package com.poriyaabdollahi.notenevis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
package com.poriyaabdollahi.notenevis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteViewModel= new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(NoteViewModel.class);



        noteViewModel.getAllNotes().observe(this, notes -> {
            //RecyclerView
            Toast.makeText(MainActivity.this, "changed", Toast.LENGTH_SHORT).show();
        });
    }
}
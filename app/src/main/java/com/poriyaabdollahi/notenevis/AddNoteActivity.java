package com.poriyaabdollahi.notenevis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    private EditText title , description ;
    private NumberPicker priority;
    public static final String EXTRA_TITLE = "com.poriyaabdollahi.notenevis.EXTRA_title";
    public static final String EXTRA_DESCRIPTION = "com.poriyaabdollahi.notenevis.EXTRA_description";
    public static final String EXTRA_PRIORITY= "com.poriyaabdollahi.notenevis.EXTRA_priority";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        title=findViewById(R.id.edit_text_title);
        description=findViewById(R.id.edit_text_description);
        priority=findViewById(R.id.number_picker_priority);

        priority.setMinValue(1);
        priority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
            default:return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = this.title.getText().toString();
        String description = this.description.getText().toString();
        int priority = this.priority.getValue();
        if (title.trim().isEmpty()||description.trim().isEmpty()){
            Toast.makeText(this, "please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        setResult(RESULT_OK,data);
        finish();
    }
}
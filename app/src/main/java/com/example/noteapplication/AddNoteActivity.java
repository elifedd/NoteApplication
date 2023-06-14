package com.example.noteapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class AddNoteActivity extends AppCompatActivity {

    EditText title, details;
    Button addNoteBtn;
    String todayDate, currentTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        getSupportActionBar().setTitle("Add New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.addNote);
        details = findViewById(R.id.addNoteDetails);
        addNoteBtn = findViewById(R.id.addNoteBtn);

        calendar = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getDefault();
        calendar.setTimeZone(timeZone);

        todayDate = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + pad(calendar.get(Calendar.MINUTE));
        Log.d("Calendar", "Date and Time: " + todayDate + " and " + currentTime);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteModel noteModel = new NoteModel(title.getText().toString(), details.getText().toString(), todayDate, currentTime);
                NoteDatabase db = new NoteDatabase(AddNoteActivity.this);
                db.AddNote(noteModel);

                Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(AddNoteActivity.this, "Note Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String pad(int i) {
        if (i < 10)
            return "0" + i;
        return String.valueOf(i);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.kachalkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kachalkaapp.data.DBManager;
import com.example.kachalkaapp.data.Note;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditNoteActivity extends AppCompatActivity {

    EditText action, count, time;
    TextView currentDate;
    ImageView calendar;
    Button save;
    Calendar date = Calendar.getInstance();
    DBManager dbManager;
    Note editNote = new Note();
    String addDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        calendar = findViewById(R.id.imageCalendar);
        currentDate = findViewById(R.id.showDate);
        action = findViewById(R.id.editAction);
        count = findViewById(R.id.editCountAction);
        time = findViewById(R.id.editTimeActoin);
        save = findViewById(R.id.buttonSaveEditNote);
        dbManager = new DBManager(this);
        Bundle items = getIntent().getExtras();
        if (items != null){
            editNote = (Note) items.getSerializable(Note.class.getSimpleName());
            currentDate.setText(editNote.getDate());
            action.setText(editNote.getAction());
            count.setText(editNote.getCountAction());
            time.setText(editNote.getTime());
        }
        dbManager.openDB();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.openDB();
                editNote.setAction(action.getText().toString());
                editNote.setCountAction(count.getText().toString());
                editNote.setTime(time.getText().toString());
                editNote.setDate(addDate);
                dbManager.updateNote(editNote);
                Toast.makeText(EditNoteActivity.this, "Данные обновлены", Toast.LENGTH_SHORT).show();
                dbManager.closeDB();
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditNoteActivity.this, d,
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        addDate = sdf.format(date.getTime());
        currentDate.setText(addDate);
    }

    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, monthOfYear);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}
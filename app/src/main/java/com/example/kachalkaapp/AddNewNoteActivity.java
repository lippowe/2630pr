package com.example.kachalkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

public class AddNewNoteActivity extends AppCompatActivity {
    EditText action, count, time;
    TextView currentDate;
    ImageView calendar;
    Calendar date = Calendar.getInstance();
    Button save;
    DBManager dbManager;
    Note newNote = new Note();
    String addDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        calendar = findViewById(R.id.imageCalendarNew);
        currentDate = findViewById(R.id.showDateNew);
        action = findViewById(R.id.editActionNew);
        count = findViewById(R.id.editCountActionNew);
        time = findViewById(R.id.editTimeActoinNew);
        save = findViewById(R.id.buttonSaveNewNote);
        setInitialDateTime();
        dbManager = new DBManager(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.openDB();
                newNote.setAction(action.getText().toString());
                newNote.setCountAction(count.getText().toString());
                newNote.setTime(time.getText().toString());
                newNote.setDate(addDate);
                dbManager.addNewNote(newNote);
                Toast.makeText(AddNewNoteActivity.this, "Данные записаны!", Toast.LENGTH_SHORT).show();
                Log.d("QWEQWEQWE", newNote.getAction() + " | " +
                        newNote.getCountAction() + " | " + newNote.getTime() + " | " +
                        newNote.getDate());
                dbManager.closeDB();
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddNewNoteActivity.this, d,
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
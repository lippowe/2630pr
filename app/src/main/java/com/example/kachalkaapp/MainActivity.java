package com.example.kachalkaapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kachalkaapp.data.DBManager;
import com.example.kachalkaapp.data.Note;
import com.example.kachalkaapp.data.NotesAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBManager dbManager;
    List<Note> notes;
    RecyclerView recyclerView;
    Button addNote;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNote = findViewById(R.id.buttonAddNote);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this, AddNewNoteActivity.class);
                startActivity(addIntent);
            }
        });
        dbManager = new DBManager(this);
        dbManager.openDB();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        notes = dbManager.getAllNotes();
        if (notes.isEmpty()){
            Toast.makeText(MainActivity.this, "Пока что записей нет!", Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
        }
        else{
            recyclerView.setVisibility(View.VISIBLE);
            notesAdapter = new NotesAdapter(notes, this, dbManager);
            recyclerView.setAdapter(notesAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}
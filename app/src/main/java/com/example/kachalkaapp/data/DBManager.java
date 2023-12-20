package com.example.kachalkaapp.data;

import static com.example.kachalkaapp.data.DBConst.NOTES_TABLE_NAME;
import static com.example.kachalkaapp.data.DBConst.NOTE_ACTION;
import static com.example.kachalkaapp.data.DBConst.NOTE_ACTION_TIME;
import static com.example.kachalkaapp.data.DBConst.NOTE_COUNT_ACTION;
import static com.example.kachalkaapp.data.DBConst.NOTE_DATE;
import static com.example.kachalkaapp.data.DBConst.NOTE_ID;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBManager(Context context){
        this.context = context;
        dbHelper = new DBHelper(this.context);
    }

    public void openDB(){
        db = dbHelper.getWritableDatabase();
    }

    public void closeDB(){
        db.close();
    }

    @SuppressLint("Range")
    public List<Note> getAllNotes(){
        List<Note> notes = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NOTES_TABLE_NAME, null);
        while (cursor.moveToNext()){
            Note note = new Note();
            note.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(NOTE_ID))));
            note.setDate(cursor.getString(cursor.getColumnIndex(NOTE_DATE)));
            note.setAction(cursor.getString(cursor.getColumnIndex(NOTE_ACTION)));
            note.setCountAction(cursor.getString(cursor.getColumnIndex(NOTE_COUNT_ACTION)));
            note.setTime(cursor.getString(cursor.getColumnIndex(NOTE_ACTION_TIME)));
            notes.add(note);
        }
        cursor.close();
        return notes;
    }

    public void addNewNote(Note note){
        ContentValues cv = new ContentValues();
        cv.put(NOTE_DATE, note.getDate());
        cv.put(NOTE_ACTION, note.getAction());
        cv.put(NOTE_COUNT_ACTION, note.getCountAction());
        cv.put(NOTE_ACTION_TIME, note.getTime());
        db.insert(NOTES_TABLE_NAME, null, cv);
    }

    public void updateNote(Note note){
        ContentValues cv = new ContentValues();
        cv.put(NOTE_DATE, note.getDate());
        cv.put(NOTE_ACTION, note.getAction());
        cv.put(NOTE_COUNT_ACTION, note.getCountAction());
        cv.put(NOTE_ACTION_TIME, note.getTime());
        db.update(NOTES_TABLE_NAME, cv, NOTE_ID + " = " + note.getId(), null);
    }

    public void deleteNote(Note note){
        db.delete(NOTES_TABLE_NAME, NOTE_ID + " = " + note.getId(), null);
    }
}

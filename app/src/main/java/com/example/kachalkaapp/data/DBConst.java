package com.example.kachalkaapp.data;

public class DBConst {
    public static final String DATA_BASE_NAME = "kachalka.db";
    public static final int DATA_BASE_VERSION = 1;
    public static final String NOTES_TABLE_NAME = "Notes";
    public static final String NOTE_ID = "id";
    public static final String NOTE_DATE = "date";
    public static final String NOTE_ACTION = "action";
    public static final String NOTE_COUNT_ACTION = "count";
    public static final String NOTE_ACTION_TIME = "time";

    public static final String CREATE_TABLE_NOTES = "CREATE TABLE IF NOT EXISTS " +
            "" + NOTES_TABLE_NAME + " ( " + NOTE_ID + " INTEGER PRIMARY KEY, " + NOTE_DATE +
            " TEXT, " + NOTE_ACTION + " TEXT, " + NOTE_COUNT_ACTION + " TEXT, " + NOTE_ACTION_TIME + " TEXT);";
    public static final String DELETE_TABLE_NOTES = "DROP TABLE IF EXISTS " + NOTES_TABLE_NAME;
}

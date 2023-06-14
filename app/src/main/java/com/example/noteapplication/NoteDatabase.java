package com.example.noteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "NotesDb.db";
    public static final String DATABASE_TABLE = "NotesTable";

    public static String COLUMN_ID = "NotesId";
    public static String COLUMN_TITLE = "NotesTitle";
    public static String COLUMN_DETAILS = "NotesDetails";
    public static String COLUMN_DATE = "NotesDate";
    public static String COLUMN_TIME = "NotesTime";

    public NoteDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + DATABASE_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DETAILS + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i >= i1)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long AddNote(NoteModel noteModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, noteModel.getNoteTitle());
        cv.put(COLUMN_DETAILS, noteModel.getNoteDetails());
        cv.put(COLUMN_DATE, noteModel.getNoteDate());
        cv.put(COLUMN_TIME, noteModel.getNoteTime());

        long ID = db.insert(DATABASE_TABLE, null, cv);
        Log.d("Inserted", "ID -> " + ID);
        return ID;
    }

    public List<NoteModel> getNotes(){
        SQLiteDatabase db = getReadableDatabase();
        List<NoteModel> allNotes = new ArrayList<>();

        String query = "SELECT * FROM " + DATABASE_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(cursor.getInt(0));
                noteModel.setNoteTitle(cursor.getString(1));
                noteModel.setNoteDetails(cursor.getString(2));
                noteModel.setNoteDate(cursor.getString(3));
                noteModel.setNoteTime(cursor.getString(4));

                allNotes.add(noteModel);
            } while(cursor.moveToNext());
        }
        return allNotes;
    }
}

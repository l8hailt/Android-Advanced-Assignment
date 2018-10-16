package vn.poly.hailt.learningsupport.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.poly.hailt.learningsupport.Constant;
import vn.poly.hailt.learningsupport.database.DatabaseHelper;
import vn.poly.hailt.learningsupport.model.Subject;

public class SubjectDAO implements Constant {
    private SQLiteDatabase db;
    private final DatabaseHelper dbHelper;

    public SubjectDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void insertSubject(Subject subject) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SU_SUBJECT_ID, subject.subjectID);
        values.put(SU_SUBJECT_NAME, subject.subjectName);
        values.put(SU_TEACHER, subject.teacher);

        long id = db.insert(SUBJECT_TABLE, null, values);

        if (isDEBUG) Log.e("insertSubject", "insertSubject ID: " + id);

        db.close();
    }

    public void deleteSubject(Subject subject) {
        db = dbHelper.getWritableDatabase();

        long id = db.delete(SUBJECT_TABLE,
                SU_SUBJECT_ID + " = ?",
                new String[]{subject.subjectID});

        if (isDEBUG) Log.e("deleteSubject", "deleteSubject ID: " + id);

        db.close();
    }

    public List<Subject> getAllSubject() {
        db = dbHelper.getReadableDatabase();
        List<Subject> subjects = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SUBJECT_TABLE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Subject subject = new Subject();
                subject.subjectID = (cursor.getString(cursor.getColumnIndex(SU_SUBJECT_ID)));
                subject.subjectName = (cursor.getString(cursor.getColumnIndex(SU_SUBJECT_NAME)));
                subject.teacher = (cursor.getString(cursor.getColumnIndex(SU_TEACHER)));

                subjects.add(subject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return subjects;
    }

    public boolean checkSubject(String subjectID) {

        String[] columns = {
                SU_SUBJECT_ID
        };
        db = dbHelper.getReadableDatabase();

        String selection = SU_SUBJECT_ID + " = ?";

        String[] selectionArgs = {subjectID};

        Cursor cursor = db.query(SUBJECT_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;

    }

    public Subject getSubject(String subjectID) {
        Subject subject = null;
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(SUBJECT_TABLE,
                new String[]{SU_SUBJECT_ID, SU_SUBJECT_NAME, SU_TEACHER},
                SU_SUBJECT_ID + " = ?",
                new String[]{subjectID}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            subject = new Subject(cursor.getString(cursor.getColumnIndex(SU_SUBJECT_ID)),
                    cursor.getString(cursor.getColumnIndex(SU_SUBJECT_NAME)),
                    cursor.getString(cursor.getColumnIndex(SU_TEACHER)));

        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return subject;

    }

}

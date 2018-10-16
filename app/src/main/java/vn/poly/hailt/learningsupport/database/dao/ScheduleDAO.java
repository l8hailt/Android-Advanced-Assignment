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
import vn.poly.hailt.learningsupport.model.Schedule;
import vn.poly.hailt.learningsupport.model.TestSchedule;

public class ScheduleDAO implements Constant {

    private SQLiteDatabase db;
    private final DatabaseHelper dbHelper;

    public ScheduleDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void insertSchedule(Schedule schedule) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SCH_SUBJECT_ID, schedule.subjectID);
        values.put(SCH_DATE, schedule.date * 1000);

        long id = db.insert(SCHEDULE_TABLE, null, values);

        if (isDEBUG) Log.e("insertSchedule", "insertSchedule ID: " + id);

        db.close();
    }

    public List<Schedule> getAllSchedule(String scheduleID) {
        db = dbHelper.getReadableDatabase();
        List<Schedule> schedules = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SCHEDULE_TABLE + " WHERE " + SCH_SUBJECT_ID + " = ?";

        String[] selectArgs = {scheduleID};

        Cursor cursor = db.rawQuery(selectQuery, selectArgs);

        if (cursor.moveToFirst()) {
            do {
                Schedule schedule = new Schedule();
                schedule.scheduleID = (cursor.getInt(cursor.getColumnIndex(SCH_SCHEDULE_ID)));
                schedule.subjectID = (cursor.getString(cursor.getColumnIndex(SCH_SUBJECT_ID)));
                schedule.date = (cursor.getLong(cursor.getColumnIndex(SCH_DATE)));

                schedules.add(schedule);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return schedules;
    }

    public void insertTestSchedule(TestSchedule testSchedule) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TSCH_SUBJECT_ID, testSchedule.testSubjectID);
        values.put(TSCH_DATE, testSchedule.date * 1000);

        long id = db.insert(TEST_SCHEDULE_TABLE, null, values);

        if (isDEBUG) Log.e("insertTestSchedule", "insertTestSchedule ID: " + id);

        db.close();
    }

    public List<TestSchedule> getAllTestSchedule(String testScheduleID) {
        db = dbHelper.getReadableDatabase();
        List<TestSchedule> testSchedules = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TEST_SCHEDULE_TABLE + " WHERE " + TSCH_SUBJECT_ID + " = ?";

        String[] selectArgs = {testScheduleID};

        Cursor cursor = db.rawQuery(selectQuery, selectArgs);

        if (cursor.moveToFirst()) {
            do {
                TestSchedule testSchedule = new TestSchedule();
                testSchedule.testScheduleID = (cursor.getInt(cursor.getColumnIndex(TSCH_SCHEDULE_ID)));
                testSchedule.testSubjectID = (cursor.getString(cursor.getColumnIndex(TSCH_SUBJECT_ID)));
                testSchedule.date = (cursor.getLong(cursor.getColumnIndex(TSCH_DATE)));

                testSchedules.add(testSchedule);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return testSchedules;
    }

}

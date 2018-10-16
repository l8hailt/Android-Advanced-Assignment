package vn.poly.hailt.learningsupport.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import vn.poly.hailt.learningsupport.Constant;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant {

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SUBJECT_TABLE);
        db.execSQL(CREATE_SCHEDULE_TABLE);
        db.execSQL(CREATE_TEST_SCHEDULE_TABLE);
        if (isDEBUG) Log.e("CREATE_CATEGORY_TABLE", CREATE_SUBJECT_TABLE);
        if (isDEBUG) Log.e("CREATE_BOOK_TABLE", CREATE_SCHEDULE_TABLE);
        if (isDEBUG) Log.e("CREATE_BILL_TABLE", CREATE_TEST_SCHEDULE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SUBJECT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TEST_SCHEDULE_TABLE);

        onCreate(db);
    }
}

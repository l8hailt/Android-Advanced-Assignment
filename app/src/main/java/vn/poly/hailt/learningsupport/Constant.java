package vn.poly.hailt.learningsupport;

public interface Constant {

    boolean isDEBUG = true;

    String DATABASE_NAME = "LearningSupport";
    int DATABASE_VERSION = 1;


    String SUBJECT_TABLE = "subjects";
    String SU_SUBJECT_ID = "subjectID";
    String SU_SUBJECT_NAME = "subjectName";
    String SU_TEACHER = "teacher";

    String CREATE_SUBJECT_TABLE =
            "CREATE TABLE " + SUBJECT_TABLE + "("
                    + SU_SUBJECT_ID + " NVARCHAR(10) PRIMARY KEY, "
                    + SU_SUBJECT_NAME + " NVARCHAR(100), "
                    + SU_TEACHER + " NVARCHAR(50)"
                    + ")";


    String SCHEDULE_TABLE = "schedule";
    String SCH_SCHEDULE_ID = "scheduleID";
    String SCH_SUBJECT_ID = "subjectID";
    String SCH_DATE = "date";

    String CREATE_SCHEDULE_TABLE =
            "CREATE TABLE " + SCHEDULE_TABLE + "("
                    + SCH_SCHEDULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + SCH_SUBJECT_ID + " NVARCHAR(10), "
                    + SCH_DATE + " LONG"
                    + ")";


    String TEST_SCHEDULE_TABLE = "testSchedule";
    String TSCH_SCHEDULE_ID = "testScheduleID";
    String TSCH_SUBJECT_ID = "subjectID";
    String TSCH_DATE = "date";

    String CREATE_TEST_SCHEDULE_TABLE =
            "CREATE TABLE " + TEST_SCHEDULE_TABLE + "("
                    + TSCH_SCHEDULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TSCH_SUBJECT_ID + " NVARCHAR(10), "
                    + TSCH_DATE + " LONG"
                    + ")";

}

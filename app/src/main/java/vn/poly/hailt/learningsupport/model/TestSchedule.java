package vn.poly.hailt.learningsupport.model;

public class TestSchedule {
    public int testScheduleID;
    public String testSubjectID;
    public long date;

    public TestSchedule() {
    }

    public TestSchedule(String testSubjectID, long date) {
        this.testSubjectID = testSubjectID;
        this.date = date;
    }
}

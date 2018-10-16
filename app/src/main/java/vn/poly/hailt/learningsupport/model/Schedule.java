package vn.poly.hailt.learningsupport.model;

public class Schedule {
    public int scheduleID;
    public String subjectID;
    public long date;

    public Schedule() {
    }

    public Schedule(String subjectID, long date) {
        this.subjectID = subjectID;
        this.date = date;
    }
}

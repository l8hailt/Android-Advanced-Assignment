package vn.poly.hailt.learningsupport.model;

public class Subject {
    public String subjectID;
    public String subjectName;
    public String teacher;

    public Subject() {
    }

    public Subject(String subjectID, String subjectName, String teacher) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.teacher = teacher;
    }
}

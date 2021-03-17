package sample;

public class StudentRecord {
    private String studentID;
    private float midterm;
    private float assignments;
    private float finalExam;

    public StudentRecord(String studentID, float midterm, float assignments, float finalExam){
        this.studentID = studentID;
        this.midterm = midterm;
        this.assignments = assignments;
        this.finalExam = finalExam;

    }

    public String getStudentID() {
        return studentID;
    }

    public float getMidterm() {
        return midterm;
    }

    public float getAssignments() {
        return assignments;
    }

    public float getFinalExam() {
        return finalExam;
    }
}

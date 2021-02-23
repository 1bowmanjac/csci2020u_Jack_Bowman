package sample;

public class StudentRecord {
    private String studentID;
    private float midterm;
    private float assignments;
    private float finalExam;
    private float finalMark;
    private String letterGrade;

    public StudentRecord(String studentID, float midterm, float assignments, float finalExam){
        this.studentID = studentID;
        this.midterm = midterm;
        this.assignments = assignments;
        this.finalExam = finalExam;
        this.finalMark = calcFinalMark();
        this.letterGrade = calcLetterGrade();
    }
    private float calcFinalMark(){
        return (assignments * 0.2f) + (midterm * 0.3f) + (finalExam * 0.5f);
    }
    private String calcLetterGrade(){
        String letterGrade = "";
        System.out.printf("finalMark = %f\n",finalMark);
        if(finalMark < 50.0f){
            letterGrade = "F";
        } else if(finalMark < 60.0f) {
            letterGrade = "D";
        } else if (finalMark < 70.0f) {
            letterGrade = "C";
        } else if (finalMark < 80.0f) {
            letterGrade = "B";
        } else if (finalMark < 100.1f) {
            letterGrade = "A";
        }
        return letterGrade;
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

    public float getFinalMark() {
        return finalMark;
    }

    public String getLetterGrade() {
        return letterGrade;
    }
    // plus more functions to calc the final grade
}

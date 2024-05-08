
import java.util.List;

public class Student {


    // 고유번호
    private String studentNum;
    // 이름
    private String studentName;
    // 과목
    private List<Integer> Subjects;

    // 상태
    private String status;

    //생성자
    public Student(String studentNum, String studentName, List<Integer> Subjects) {
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.Subjects = Subjects;
    }

    //메서드

    /*
    학번 getter
     */
    public String getStudentNum() {
        return studentNum;
    }
    /*
    이름 getter
     */
    public String getStudentName() {
        return studentName;
    }
    /*

     */
    public List<Integer> getSubjects() {
        return Subjects;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean checkSubject (String subjectId){
     if(Subjects.contains(subjectId)){
         return true;
     }
       return false;
    }



}
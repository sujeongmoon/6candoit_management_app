
import java.util.ArrayList;
import java.util.List;

public class Student{

    static List<Student> studentList = new ArrayList<>();



    List<String> subjects = new ArrayList<>();
    //고유번호
    private static int studentNum;
    // 이름
    private static String studentName;
    // 필수과목
    List<Subject> requiredSubject = new ArrayList<>();
   // 선택과목
    List<String> eletiveSubject;

    public Student () {


    }

    public void setStudentNum (int studentNum) {
        this.studentNum = studentNum;
    }
    public void setStudentName (String studentName) {
        this.studentName = studentName;
    }
    public void setRequiredSubject (List<Subject> requiredSubject) {
        this.requiredSubject = requiredSubject;
    }
    public void setEletiveSubject (List<String> eletiveSubject) {
        this.eletiveSubject = eletiveSubject;
    }
    public List<Subject> getRequiredSubject () {
        return requiredSubject;
    }
    static public List<Student> getStudentList() {
        return List.copyOf(studentList);
    }
//    int studentNum, String studentName, List<String> requiredSubject, List<String> eletiveSubject

}

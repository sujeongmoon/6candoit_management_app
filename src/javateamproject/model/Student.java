package javateamproject.model;

<<<<<<< HEAD
import javateamproject.management.SubjectManagement;
=======
import javateamproject.type.ConditionType;
>>>>>>> 6be57d2f962b7c1b3387796cba3eaf15ba565280

import java.util.List;

public class Student {


    // 고유번호
    private final String studentId;
    // 이름
    private String studentName;
    // 과목
    private final List<String> selectSubjectIds;
    private ConditionType condition;

    //생성자
    public Student(String studentId, String studentName, List<String> selectSubjectIds) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.selectSubjectIds = selectSubjectIds;
        this.condition = ConditionType.GREEN;
    }
    //getter
    public String getStudentId(){ return studentId; }
    public List<String> getSelectSubjectIds() {  return selectSubjectIds; }
    public ConditionType getCondition() { return condition;   }
    public String getStudentName() {
        return studentName;
    }
    //setter
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setCondition(ConditionType condition) {
        this.condition = condition;
    }

    // 수강과목 리스트 띄우기
    public static void inquirySelectSubjectIds (Student student) {
        System.out.println("과목 목록:");
        SubjectManagement.viewSubjectSelected(student.getSelectSubjectIds());
        }
    }







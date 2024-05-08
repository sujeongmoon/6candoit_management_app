package javateamproject.model;

import java.util.List;

public class Student {


    // 고유번호
    private final String studentId;
    // 이름
    private String studentName;
    // 과목
    private final List<String> selectSubjectIds;
    private String condition;

    //생성자
    public Student(String studentId, String studentName, List<String> selectSubjectIds) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.selectSubjectIds = selectSubjectIds;
        this.condition = "Green";
    }
    //getter
    public String getStudentId(){ return studentId; }
    public List<String> getSelectSubjectIds() {  return selectSubjectIds; }
    public String getCondition() { return condition;   }
    public String getStudentName() {
        return studentName;
    }
    //setter
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
}





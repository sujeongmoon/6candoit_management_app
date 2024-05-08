package javateamproject.model;

import javateamproject.type.ConditionType;

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
}





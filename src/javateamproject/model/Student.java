package javateamproject.model;

import javateamproject.management.SubjectManagement;
import javateamproject.store.Store;
import javateamproject.type.ConditionType;
import javateamproject.type.SubjectType;

import java.util.List;

public class Student {



    // 고유번호
    private final String studentId;
    // 이름
    private String studentName;
    // 과목
    private final List<String> selectSubjectIds;
    // 상태
    private ConditionType condition;
    // 필수 과목 목록
    private List<String> requiredSubjects;

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
    // 수강생의 필수 과목을 반환하는 메서드
    public List<String> getRequiredSubjects() {   // 필수 과목 목록 getter
        return this.selectSubjectIds.stream()
                .filter(s -> Store.getSubjectTypeBySubjectId(s)== SubjectType.MUST)
                .toList();
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







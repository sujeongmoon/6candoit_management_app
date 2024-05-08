package javateamproject.store;

import javateamproject.model.Score;
import javateamproject.model.Student;
import javateamproject.model.Subject;
import javateamproject.type.ClassType;
import javateamproject.type.SubjectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//저장소 기능 추가, 검색, 접근, 삭제 오직!//여기올때 오레 체크 완료
public class Store {
    private static int studentIndex;
    private static int scoreIndex;
    private static int subjectIndex ;
    private static List<Student> studentStore ;
    private static List<Subject> subjectStore ;
    private static List<Score> scoreStore ;

    public static void init(){
        studentStore=new ArrayList<>();
        scoreStore=new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "Java",
                        SubjectType.MUST
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "객체지향",
                        SubjectType.MUST
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "Spring",
                        SubjectType.MUST
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "JPA",
                        SubjectType.MUST
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "MySQL",
                        SubjectType.MUST
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "디자인 패턴",
                        SubjectType.CHOICE
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "Spring Security",
                        SubjectType.CHOICE
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "Redis",
                        SubjectType.CHOICE
                ),
                new Subject(
                        sequence(ClassType.SUBJECT),
                        "MongoDB",
                        SubjectType.CHOICE
                )
        );
    }

    private static String sequence(ClassType classtype) {
        switch (classtype){
            case STUDENT -> {
                return classtype.getIdxName()+studentIndex++;
            }
            case SCORE -> {
                return classtype.getIdxName()+scoreIndex++;
            }
            case SUBJECT -> {
                return classtype.getIdxName()+subjectIndex++;
            }
            default -> {
                return null;
            }
        }
    }

    public static List<Score> getScoreStore() {
        return scoreStore;
    }

    public static List<Student> getStudentStore() {
        return studentStore;
    }

    public static List<Subject> getSubjectStore() {
        return subjectStore;
    }

    public static int getScoreIndex() {
        return scoreIndex;
    }

    public static int getStudentIndex() {
        return studentIndex;
    }

    public static int getSubjectIndex() {
        return subjectIndex;
    }

    //학생 추가
    public static void addStudent(String studentName, List<String> selectSubjectIds){
        Student student = new Student(sequence(ClassType.STUDENT),studentName,selectSubjectIds);
        studentStore.add(student);
    }
    //성적 추가
    public static void addScore(String studentId, String subjectId, int round, int score, SubjectType subjectType){
        Score examScore = new Score(sequence(ClassType.SCORE), studentId, subjectId, round, score,subjectType);
        scoreStore.add(examScore);
    }
    //삭제하기
    public static void deleteStudent(String studentId){
        int idx = findStudentIndex(studentId);
        studentStore.remove(idx);
    }
    //학번 맞는 학생 인덱스 찾기
    public static int findStudentIndex(String targetStudentId) {
        for (int i = 0; i < studentStore.size(); i++) {
            if (studentStore.get(i).getStudentId().equals(targetStudentId)) {
                return i; // 일치하는 학번을 가진 학생을 찾으면 해당 인덱스 반환
            }
        }
        return -1; // 일치하는 학번을 가진 학생을 찾지 못한 경우 -1 반환
    }
    //필수/선택 별 과목 이름 가져오기
    public static List<String> getSubjectNamesBySubjectType(SubjectType subjectType){
        List<String> names = new ArrayList<>();
        for(Subject s : subjectStore){
            if(s.getSubjectType().equals(subjectType)) names.add(s.getSubjectId());
        }
        return names;
    }
    //해당 과복번호로 타입 가져오기
    public static SubjectType getSubjectTypeBySubjectId(String subjectId){
        for(Subject s : subjectStore){
            if(s.getSubjectId().equals(subjectId)) return s.getSubjectType();
        }
        return null;
    }
    public static String getSubjectNameBySubjectId(String subjectId){
        for(Subject s : subjectStore){
            if(s.getSubjectId().equals(subjectId)) return s.getSubjectName();
        }
        return null;
    }
    //학생번호, 과목 번호, 회차 번호 받아서 스코어 객체 접근하기
    public static Score getScoreBy(String studentId, String subjectId,int round) {
        Optional<Score> result = scoreStore.stream()
                .filter(score -> score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId) && score.getRound() == round)
                .findFirst();
        if(result.isPresent()) return result.get();
        return null;
    }
    public static Subject getSubjectBySubjectId(String subjectId){
        for(Subject s : subjectStore){
            if(s.getSubjectId().equals(subjectId)) return s;
        }
        return null;
    }
}

package javateamproject.store;

import javateamproject.model.Score;
import javateamproject.model.Student;
import javateamproject.model.Subject;
import javateamproject.type.ClassType;
import javateamproject.type.SubjectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//저장소 기능 추가, 검색, 접근, 삭제 오직!//여기올때 오레 체크 완료
public class Store {
    private static int studentIndex;
    private static int scoreIndex;
    private static int subjectIndex;
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    public static void init() {
        studentStore = new ArrayList<>();
        scoreStore = new ArrayList<>();
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
        dummy();
    }

    private static String sequence(ClassType classtype) {
        switch (classtype) {
            case STUDENT -> {
                return classtype.getIdxName() + studentIndex++;
            }
            case SCORE -> {
                return classtype.getIdxName() + scoreIndex++;
            }
            case SUBJECT -> {
                return classtype.getIdxName() + subjectIndex++;
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
        return scoreIndex - 1;
    }

    public static int getStudentIndex() {
        return studentIndex - 1;
    }

    public static int getSubjectIndex() {
        return subjectIndex - 1;
    }

    //학생 추가
    public static void addStudent(String studentName, List<String> selectSubjectIds) {
        Student student = new Student(sequence(ClassType.STUDENT), studentName, selectSubjectIds);
        studentStore.add(student);
    }

    //성적 추가
    public static void addScore(String studentId, String subjectId, int round, int score, SubjectType subjectType) {
        Score examScore = new Score(sequence(ClassType.SCORE), studentId, subjectId, round, score, subjectType);
        scoreStore.add(examScore);
    }

    //삭제하기
    public static void deleteStudent(String studentId) {
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
    public static List<String> getSubjectNamesBySubjectType(SubjectType subjectType) {
        List<String> names = new ArrayList<>();
        for (Subject s : subjectStore) {
            if (s.getSubjectType().equals(subjectType)) names.add(s.getSubjectId());
        }
        return names;
    }

    //해당 과복번호로 타입 가져오기
    public static SubjectType getSubjectTypeBySubjectId(String subjectId) {
        for (Subject s : subjectStore) {
            if (s.getSubjectId().equals(subjectId)) return s.getSubjectType();
        }
        return null;
    }

    public static String getSubjectNameBySubjectId(String subjectId) {
        for (Subject s : subjectStore) {
            if (s.getSubjectId().equals(subjectId)) return s.getSubjectName();
        }
        return null;
    }

    //학생번호, 과목 번호, 회차 번호 받아서 스코어 객체 접근하기
    public static Score getScoreBy(String studentId, String subjectId, int round) {
        Optional<Score> result = scoreStore.stream()
                .filter(score -> score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId) && score.getRound() == round)
                .findFirst();
        if (result.isPresent()) return result.get();
        return null;
    }

    public static Subject getSubjectBySubjectId(String subjectId) {
        for (Subject s : subjectStore) {
            if (s.getSubjectId().equals(subjectId)) return s;
        }
        return null;
    }

    private static void dummy() {
        addStudent("강호동", Arrays.asList("SJ2", "SJ1", "SJ3", "SJ5", "SJ6"));
        addStudent("유재석", Arrays.asList("SJ2", "SJ1", "SJ3", "SJ5", "SJ6"));
        addStudent("장원영", Arrays.asList("SJ2", "SJ1", "SJ3", "SJ5", "SJ6"));
        addStudent("김지민", Arrays.asList("SJ2", "SJ1", "SJ3", "SJ5", "SJ6"));
        addStudent("함날두", Arrays.asList("SJ2", "SJ1", "SJ3", "SJ5", "SJ6"));
        addStudent("홍길동", Arrays.asList("SJ2", "SJ1", "SJ3", "SJ5", "SJ6"));
        addStudent("문수정", Arrays.asList("SJ0", "SJ1", "SJ2", "SJ3", "SJ4", "SJ5", "SJ6", "SJ7", "SJ8"));
        addScore("ST1", "SJ1", 1, 90, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST1", "SJ1", 2, 70, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST1", "SJ1", 3, 40, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST1", "SJ2", 1, 90, getSubjectTypeBySubjectId("SJ2"));
        addScore("ST1", "SJ3", 1, 60, getSubjectTypeBySubjectId("SJ3"));
        addScore("ST1", "SJ5", 1, 60, getSubjectTypeBySubjectId("SJ5"));
        addScore("ST1", "SJ6", 1, 80, getSubjectTypeBySubjectId("SJ6"));
        addScore("ST2", "SJ1", 1, 90, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST2", "SJ1", 2, 80, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST2", "SJ1", 3, 70, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST2", "SJ1", 4, 60, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST2", "SJ1", 5, 50, getSubjectTypeBySubjectId("SJ1"));
        addScore("ST2", "SJ1", 6, 40, getSubjectTypeBySubjectId("SJ1"));
    }

    public static List<Score> getScoreByStudentId(String studentId) {
        return scoreStore.stream()
                .filter(score -> score.getStudentId().equals(studentId))
                .toList();
    }




    // ㄱㅁ

    // 학생과 과목에 대한 회차 수를 반환하는 메서드
    // 학생의 번호 & 이름을 인자로 전달받고, 해당 학생이 수강한 과목에서 주어진 과목의 회차 수를 계산하는 메서드
    public static int getSubjectRoundsByStudentAndSubject(String studentNum, String studentName, String subjectId) {
        // 주어진 학생 번호에 해당하는 학생을 찾음
        Optional<Student> optionalStudent = studentStore.stream() //Optional을 사용하여 안전하게 처리
                .filter(student -> student.getStudentId().equals(studentNum) && student.getStudentName().equals(studentName))
                .findFirst();

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            int totalRounds = 0;
            // 학생이 수강한 과목에서 해당 과목의 회차 수를 계산하기!
            for (String selectSubjectId : student.getSelectSubjectIds()) {
                // 학생이 수강한 과목이 주어진 과목과 일치하는 경우에만! 회차 수 더하기.
                if (selectSubjectId.equals(subjectId)) {
                    totalRounds += getSubjectRoundsById(subjectId);
                }
            }
            return totalRounds;
        } else { // 예외 처리
            throw new IllegalArgumentException("해당 학번과 이름에 해당하는 학생을 찾을 수 없습니다: " + studentNum + ", " + studentName);
        }
    }


    // 해당 학생과 과목에 대한 점수 목록을 반환하는 메서드
    // 주어진 학생 ID와 과목 ID에 해당하는 점수를 찾아서 리스트로 반환
    // 리스트에서 각 점수를 확인해 서로가 일치하는 경우에만 List에 추가!
    public static List<Score> getScoresByStudentAndSubject(String studentId, String subjectId) {
        List<Score> scores = new ArrayList<>();
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId)) {
                scores.add(score);
            }
        }
        return scores;
    }

    // 해당 과목의 총 회차 수를 반환하는 메서드
    // 주어진 과목 ID에 해당하는 회차 수를 계산하여 반한!
    // scoreStore 리스트에서 과목 id와 일치하는 점수를 찾고, 찾을때마다 회차 수를 증가 => 총 회차 수 반복
    public static int getSubjectRoundsById(String subjectId) {
        int totalRounds = 0;
        for (Score score : scoreStore) {
            if (score.getSubjectId().equals(subjectId)) {
                totalRounds++;
            }
        }
        return totalRounds;
    }

    // 특정 상태의 수강생을 필터링하여 반환하는 메서드
    public static List<Student> getStudentsByCondition(String condition) {
        List<Student> filteredStudents = new ArrayList<>(); // 필터링된 수강생을 저장할 List
        for (Student student : studentStore) { // 모든 수강생을 반복하여 주어진 상태와 일치하는 수강생을 찾아냄
            if (student.getCondition().equals(condition)) { // 수강생의 상태가 주어진 상태와 일치시 리스트에 추가!
                filteredStudents.add(student);
            }
        }
        return filteredStudents; // 필터링된 수강생 리스트를 return
    }



    // 수강생의 필수 과목을 반환하는 메서드
    public List<String> getRequiredSubjects() {
        List<String> requiredSubjects = new ArrayList<>(); // 필수 과목을 저장할 List
        for (Subject subject : subjectStore) { // 모든 과목을 반복하면서 필수 과목인지 확인
            if (subject.getSubjectType() == SubjectType.MUST) { // 과목이 필수 과목인 경우에만 리스트에 추가!
                requiredSubjects.add(subject.getSubjectId());
            }
        }
        return requiredSubjects; // 필수 과목 List return
    }


}

package javateamproject.management;

import javateamproject.display.ScoreDisplayView;
import javateamproject.model.Score;
import javateamproject.model.Student;
import javateamproject.model.Subject;
import javateamproject.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static javateamproject.display.StudentDisplayView.displayInquiryStudent;

public class ScoreManagement {

    Scanner sc = new Scanner(System.in);

    //중원님
    //점수 등록
    public static void addScore() throws InterruptedException {

        //(0) 학생 목록 보여주기
        StudentManagement.inquiryStudent();

        //(1) 해당하는 학번 학생 인스터스 가져오기
        Student student = StudentManagement.searchGetStudent();

        //(2) 선택된 학생 과목정보와 비교해서 과목 입력받기
        String subjectName = getSubjectNameFromUser(student);

        //(3) 회차/점수 입력받기
        int round = getRoundFromUser();
        int score = getScoreFromUser();

        // 주의사항: 회차 (1~10), 점수(0~100) 조건에 맞춰서 입력된 값인지 확인
        if (!isValidRound(round) || !isValidScore(score)) {
            System.out.println("잘못된 입력입니다. 회차는 1에서 10 사이, 점수는 0에서 100 사이의 값을 입력하세요.");
            return; // 잘못된 입력이 있을 경우 메소드 종료
        }

        // (4) 과목정보와 회차가 동일한 경우 (다시 받을지/예외처리 할지)
        if (isScoreExist(student, subjectName, round)) {
            System.out.println("이미 해당 과목의 회차 점수가 등록되어 있습니다.");
            return; // 이미 해당 과목의 회차 점수가 등록되어 있는 경우 메소드 종료
        }
        Store.addScore(student.getStudentId(),subjectName,round,score,Store.getSubjectTypeBySubjectId(subjectName));

        System.out.println("점수가 성공적으로 등록되었습니다.");
        ScoreDisplayView.displayView();

    }

    //점수 수정
    public static void modScore() throws InterruptedException {
        //(0) 학생 목록 보여주기
        StudentManagement.inquiryStudent();

        //(1) 해당하는 학번 학생 인스터스 가져오기
        Student student = StudentManagement.searchGetStudent();

        //(2) 선택된 학생 과목정보와 비교해서 과목 입력받기
        String subjectName = getSubjectNameFromUser(student);

        //(3) 회차/점수 입력받기
        int round = getRoundFromUser();
        int score = getScoreFromUser();
        Score modifyscore = Store.getScoreBy(student.getStudentId(), subjectName, round);
        modifyscore.setScore(score);



    }

    //수강생 과복별 시험 회차 등급 조회
    public static void inqScore() throws InterruptedException {
        //(0) 학생 목록 보여주기
        StudentManagement.inquiryStudent();

        //(1) 해당하는 학번 학생 인스터스 가져오기
        Student student = StudentManagement.searchGetStudent();

        //(2) 선택된 학생 과목정보와 비교해서 과목 입력받기
        String subjectName = getSubjectNameFromUser(student);

        inquirySubjectGrades(student, subjectName);
    }

    //여기서부터 경민님
    //수강생의 과목별 평균 등급을 조회
    public static void inquiryStudentAverageBySubject() {
    }

    //수강생 상태별 평균 등급을 조회
    public static void inquiryStudentAverageByStatus() {
    }

    // 5. 점수 입력 메소드


    // 점수 객체 생성 및 저장




//------- 위 코드에서 사용되는 메소드들 -------


// 학번으로 해당하는 학생 객체를 찾는 메소드


    // 과목명을 입력받는 메소드
    private static String getSubjectNameFromUser(Student student) {
        Scanner sc = new Scanner(System.in);
        Student.inquirySelectSubjectIds(student);
        String subjectNum;
        while(true) {
            System.out.println("과목을 입력하세요 : ");
            subjectNum = sc.nextLine();

            // 입력된 과목이 유효한지 확인
            if (isValidStudentSubjects(student, subjectNum)) {
                return subjectNum;
            } else {
                System.out.println("잘못 입력 하셨습니다.");
            }
        }
    }


    // 회차를 입력받는 메소드
// 입력값이 유효하지 않을 경우 예외 처리 및 입력값이 유효할때 까지 다시 입력 요청.
    private static int getRoundFromUser() {
        Scanner sc = new Scanner(System.in);
        int round;
        while (true) {
            try {
                System.out.print("회차를 입력하세요 (1~10): ");
                round = Integer.parseInt(sc.nextLine());
                if (round < 1 || round > 10) {
                    throw new IllegalArgumentException("유효하지 않은 회차입니다. 1에서 10 사이의 값을 입력하세요.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return round;
    }


    // 점수를 입력받는 메소드
// 입력값이 유효하지 않을 경우 예외 처리 및 입력값이 유효할때 까지 다시 입력 요청
    private static int getScoreFromUser() throws IllegalArgumentException {
        Scanner sc = new Scanner(System.in);
        int score;
        while (true) {
            try {
                System.out.print("점수를 입력하세요 (0~100): ");
                score = Integer.parseInt(sc.nextLine());
                if (score < 0 || score > 100) {
                    throw new IllegalArgumentException("유효하지 않은 점수입니다. 0에서 100 사이의 값을 입력하세요.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return score;
    }

    // 회차의 유효성을 검사하는 메소드
    private static boolean isValidRound(int round) {
        return round >= 1 && round <= 10; // 회차가 1에서 10 사이의 값인지 확인하여 유효성을 반환
    }

    // 점수의 유효성을 검사하는 메소드
    private static boolean isValidScore(int score) {
        return score >= 0 && score <= 100; // 점수가 0에서 100 사이의 값인지 확인하여 유효성을 반환
    }



    // 해당 과목의 해당 회차 점수가 이미 등록되어 있는지 확인하는 메소드
    private static boolean isScoreExist(Student student, String subjectName, int round) {
        if (Store.getScoreBy(student.getStudentId(), subjectName, round) != null) return true;
        return false;
    }

    // 해당 수강생이 해당 과목을 수강하고 있는지 확인하는 메소드
    public static boolean isValidStudentSubjects(Student student, String subjectid) {
        if (student.getSelectSubjectIds().contains(subjectid)) {
            return true;
        }
        return false;
    }

// ------------------------------------------------------------------------------------------


// 점수 수정
// 6. void setScoreAtStudent() {

    public static void setScoreAtStudent() {
        // (1) 해당하는 학번 학생 인스턴스 가져오기
        Student student = StudentManagement.searchGetStudent();

        // (2) 선택된 학생의 과목 정보와 회차 비교해서 과목 회차 입력받기
        Subject subject = getSubjectFromUser(student);
        int round = getRoundFromUser(subject);

        // (3) 수정할 점수 입력 및 점수 수정
        int newScore = getScoreFromUser();
        updateScore(student, subject, round, newScore);
    }

// 학생 객체 찾기


    // 학생의 과목 정보와 비교하여 과목 선택 받기
    private static Subject getSubjectFromUser(Student student) {   // getSubjectFromUser(Student student): 학생이 수강 중인 과목 중에서 과목을 선택받습니다.
        // 학생이 수강 중인 과목 리스트 가져오기
        List<String> subjects = student.getSelectSubjectIds();

        // 과목 목록 출력
        System.out.println("수강 중인 과목 목록:");
        for (String subject : subjects) {
            System.out.println(Store.getSubjectNameBySubjectId(subject));
        }

        // 과목 입력 받기
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 점수를 입력할 과목을 선택하세요: ");
        String subjectName = sc.nextLine();

        // 선택한 과목 찾기
        if (subjects.contains(subjectName)) return Store.getSubjectBySubjectId(subjectName);

        // 선택한 과목이 없는 경우 예외 처리
        throw new IllegalArgumentException("수강 중인 과목 중 입력한 이름과 일치하는 과목이 없습니다.");
    }

    // 학생의 점수 리스트 가져오기
    private static List<Score> getScoreListFromStudent(Student student) {
        List<Score> scores = Store.getScoreStore().stream()
                .filter(a -> a.getStudentId() == student.getStudentId())
                .toList();

        return scores;
    }

    // 회차 입력 받기
    private static int getRoundFromUser(Subject subject) {    // getRoundFromUser(Subject subject): 선택한 과목의 회차를 입력받습니다.
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 점수를 입력할 회차를 선택하세요 (1~10): ");
        int round = Integer.parseInt(sc.nextLine());

        // 회차 유효성 검사
        if (round < 1 || round > 10) {
            throw new IllegalArgumentException("회차는 1부터 10 사이의 숫자여야 합니다.");
        }

        // 해당 과목의 해당 회차의 점수가 이미 있는지 확인하는 로직 추가 가능

        return round;
    }
// 점수 입력 받기

    // 학생의 점수 수정
    private static void updateScore(Student student, Subject subject, int round, int newScore) {
        //학생의 점수를 수정
        // 학생의 점수 리스트 가져오기//지금 구조 바껴서.
        //List<Score> scores = student.getScores();getScoreListFromStudent
        List<Score> scores = getScoreListFromStudent(student);
        // 해당 과목과 회차에 대한 점수 찾기
        for (Score score : scores) {
            if (score.getSubjectId().equals(subject.getSubjectId()) && score.getRound() == round) {
                // 해당 점수를 새로운 점수로 업데이트
                score.setScore(newScore);
                System.out.println("점수가 성공적으로 수정되었습니다.");
                return;
            }
        }

        // 해당 과목과 회차에 대한 점수가 없는 경우 예외 처리
        throw new IllegalArgumentException("해당 과목과 회차에 대한 점수가 없습니다.");
    }

    // ------------------------------------------------------------------------------------------
//7. void inquiryScoreAtStudent()
   public static void inquirySubjectGrades(Student student, String subjectName) {

        List<Score> scores = Store.getScoreStore().stream()
                .filter(a -> a.getSubjectId().equals(subjectName)  && a.getStudentId().equals(student.getStudentId()))
                .toList();

                 //이거 optional 해줘야될거 같은데 이따가 질문
        for (Score score : scores){
            System.out.println(score.getRound() + "회차 : " + score.getGrade() + " 등급");
        }

    }



    // 점수 조회
    private void inquiryScoreAtStudent() {
        // (1).해당하는 학번 학생 인스턴스 가져오기
        Student student = StudentManagement.searchGetStudent(); //searchGetStudent() 메소드를 호출 => 해당하는 학번의 학생 객체 get

        // (2) 해당하는 학생의 회차별 과목 점수 출력
        System.out.println("학생 " + student.getStudentName() + "의 회차별 과목 점수:"); // 해당 학생 이름을 먼저 출력


        // 회차별로 과목 점수 출력
        // => socreStore 에 저장된 점수 정보를 조회, 해당 학생의 학번과 일치하는 점수만 출력!

        for (Score score : Store.getScoreStore()) {
            if (score.getStudentId().equals(student.getStudentId())) {
                System.out.print("회차 " + score.getRound() + " => ");
                for (Subject subject : Store.getSubjectStore()) {      //과목 이름은 subjectStore 에서 조회 후 출력!
                    if (subject.getSubjectId().equals(score.getSubjectId())) {
                        System.out.print(subject.getSubjectName() + " : " + score.getScore() + ", ");
                        break;
                    }
                }
                System.out.println();
            }
        }
    }
}
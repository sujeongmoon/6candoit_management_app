package javateamproject.management;

import javateamproject.model.Score;
import javateamproject.model.Student;
import javateamproject.store.Store;
import javateamproject.display.ScoreDisplayView;
import javateamproject.type.ConditionType;

import java.util.*;
import java.util.List;
import java.util.Scanner;


public class ScoreManagement {

    private static Scanner sc = new Scanner(System.in);

    //중원님
    //점수 등록
    public static void addScore() throws InterruptedException {
        System.out.println("점수를 등록합니다.");
        //(0) 학생 목록 보여주기
        StudentManagement.inquiryStudent();

        //(1) 해당하는 학번 학생 인스턴스 가져오기
        Student student = StudentManagement.searchGetStudent();

        //(2) 선택된 학생 과목정보와 비교해서 과목 입력받기
        String subjectName = SubjectManagement.getSubjectIdFromUserAtAdd(student);

        int round;
        int score;

        //(4) 회차/점수 입력받기
        while (true) {
            round = getRoundFromUser();
            score = getScoreFromUser();

            // 주의사항: 회차 (1~10), 점수(0~100) 조건에 맞춰서 입력된 값인지 확인
//            if (!isValidRound(round) || !isValidScore(score)) {
//                System.out.println("잘못된 입력입니다. 회차는 1에서 10 사이, 점수는 0에서 100 사이의 값을 입력하세요.");
//                return; // 잘못된 입력이 있을 경우 메소드 종료
//            }

            // (4) 과목정보와 회차가 동일한 경우 (다시 받을지/예외처리 할지)
            if (isScoreExist(student, subjectName, round)) {
                System.out.println("이미 해당 과목의 회차 점수가 등록되어 있습니다.");
                System.out.println("");
                // 이미 해당 과목의 회차 점수가 등록되어 있는 경우 메소드 종료
                continue;
            }
            break;
        }
        Store.addScore(student.getStudentId(), subjectName, round, score, Store.getSubjectTypeBySubjectId(subjectName));

        System.out.println("점수가 성공적으로 등록되었습니다.");
        System.out.println("");

    }

    //점수 수정
    public static void modScore() throws InterruptedException {
        System.out.println("이미 등록된 점수를 수정합니다.");

        //(0) 학생 목록 보여주기
        StudentManagement.inquiryStudent();
        Student student;
        //(1) 해당하는 학번 학생 인스턴스 가져오기
        student = StudentManagement.searchGetStudent();

        //(2) 선택된 학생 과목정보와 비교해서 과목 입력받기
        String subjecId;
        do{
             subjecId = SubjectManagement.getSubjectNameFromUser(student);
             if(isScoreExistBySubjectId(subjecId, student.getStudentId())){
                 System.out.println("점수 정보가 존재 하지 않는 과목입니다.");
             }
        }while(isScoreExistBySubjectId(subjecId, student.getStudentId()));


        //(3) 회차/점수 목록 출력
        inquirySubjectGrades(student, subjecId);

        int round;
        int score;

        //(4) 회차/점수 입력받기
        while (true) {
            round = getRoundFromUser();
            score = getScoreFromUser();

            // 주의사항: 회차 (1~10), 점수(0~100) 조건에 맞춰서 입력된 값인지 확인
//            if (!isValidRound(round) || !isValidScore(score)) {
//                System.out.println("잘못된 입력입니다. 회차는 1에서 10 사이, 점수는 0에서 100 사이의 값을 입력하세요.");
//                return; // 잘못된 입력이 있을 경우 메소드 종료
//            }

            // (4) 과목정보와 회차가 동일한 경우 (다시 받을지/예외처리 할지)
            if (!isScoreExist(student, subjecId, round)) {
                System.out.println("해당 과목의 회차 점수가 등록되어 있지 않습니다.");
                System.out.println("");
                // 이미 해당 과목의 회차 점수가 등록되어 있는 경우 메소드 종료
                continue;
            }
            break;
        }



        //(5) 해당 회차 점수 수정
        Score modifyscore = Store.getScoreBy(student.getStudentId(), subjecId, round);

        modifyscore.setScore(score, Store.getSubjectTypeBySubjectId(subjecId));

        System.out.println("점수가 성공적으로 수정 되었습니다.");
        System.out.println("");

        if (modifyscore == null) throw new AssertionError();
        modifyscore.setScore(score, Store.getSubjectTypeBySubjectId(modifyscore.getSubjectId()));

        ScoreDisplayView.displayView();
    }

    public static boolean isScoreExistByStudentId(String studentId) {
        Optional<Score> result = Store.getScoreStore().stream()
                .filter(score -> score.getStudentId().equals(studentId))
                .findFirst();
        return result.isPresent();
    }

    public static boolean isScoreExistBySubjectId(String subjectId,String studentId) {
        Optional<Score> result = Store.getScoreStore().stream()
                .filter(score -> score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId))
                .findFirst();
        return result.isEmpty();
    }

    //수강생 과목별 시험 회차 등급 조회
    public static void inqScore() throws InterruptedException {
        System.out.println("수강생의 과목별 시험 회차 등급을 조회합니다.");
        //(0) 학생 목록 보여주기
        StudentManagement.inquiryStudent();

        //(1) 해당하는 학번 학생 인스턴스 가져오기
        Student student;
        do {
            student = StudentManagement.searchGetStudent();
            if (!isScoreExistByStudentId(student.getStudentId())) {
                System.out.println("점수 정보가 존재 하지 않는 수강생입니다.");
            }
        }while(!isScoreExistByStudentId(student.getStudentId()));

        //(2) 선택된 학생 과목정보와 비교해서 과목 입력받기
        String subjectName = SubjectManagement.getSubjectNameFromUser(student);



        inquirySubjectGrades(student, subjectName);
        // subjectName을 Subject객체가 아닌 String으로 받아와서, 선택한 과목의 name에 접근 불가
        System.out.println("\n" + student.getStudentName() + " 수강생의, 선택한 과목의 시험 회차 등급 조회가 완료되었습니다.");
        Thread.sleep(1000);
    }



    //여기서부터 경민님
    // (1) 수강생의 과목별 평균 등급 조회
    public static void inquiryStudentAverageBySubject() throws InterruptedException {
        System.out.println("수강생의 과목별 평균 등급을 조회합니다.");
        // 수강생 정보를 가져옴
        StudentManagement.inquiryStudent();
        Student student = StudentManagement.searchGetStudent();

        // 해당 수강생의 점수 목록을 가져오기!
        List<Score> scores = Store.getScoreByStudentId(student.getStudentId());

        // 각 과목별로 점수를 합산하기 위한 맵을 생성
        Map<String, Integer> subjectScores = new HashMap<>();
        // 각 과목별로 회차를 저장하기 위한 맵을 생성
        Map<String, Integer> subjectRounds = new HashMap<>();

        // 각 과목별로 점수를 합산 및 회차를 카운트
        for (Score score : scores) {
            String subjectId = score.getSubjectId();
            int scoreValue = score.getScore();

            // 해당 과목의 회차 수를 가져와서 증가시킴
            subjectRounds.put(subjectId, subjectRounds.getOrDefault(subjectId, 0) + 1);

            // 해당 과목의 점수를 합산
            subjectScores.put(subjectId, subjectScores.getOrDefault(subjectId, 0) + scoreValue);
        }

        // 과목별 평균 등급을 출력
        System.out.println("수강생 " + student.getStudentName() + "의 과목별 평균 등급:");
        for (String subjectId : subjectScores.keySet()) {
            // 해당 과목의 총 회차 수를 가져오기!
            int totalRounds = subjectRounds.get(subjectId);
            // 해당 과목의 총 점수를 가져오기!
            int totalScore = subjectScores.get(subjectId);

            // 평균 점수를 계산
            double averageScore = (double) totalScore / totalRounds;

            // 과목명을 가져오기!
            String subjectName = Store.getSubjectNameBySubjectId(subjectId);
            String grade = Score.calculateGrade((int)averageScore,Store.getSubjectTypeBySubjectId(subjectId));
            // 평균 등급을 출력

            System.out.println(subjectName + ": " + averageScore + "(" + grade + ")");
        }
        System.out.println(student.getStudentName() + " 수강생의 과목별 평균 등급 출력이 완료되었습니다.\n");
        Thread.sleep(1000);
    }

    // (2) 특정 상태 수강생들의 필수 과목 평균 등급 조회
    public static void inquiryStudentAverageByStatus() {
        // 특정 상태의 수강생을 필터링하여 가져오기
        ConditionType condition = null;
        System.out.println("조회할 수강생의 상태를 숫자로 입력해주세요.\n1.GREEN 2.YELLOW 3.RED");
        boolean flag = true;
        do {
            String conditionChoose = sc.next();
            switch (conditionChoose) {
                case "1" -> {
                    condition=ConditionType.GREEN;
                    flag = false;
                }
                case "2" -> {
                    condition=ConditionType.YELLOW;
                    flag = false;
                }
                case "3" -> {
                    condition=ConditionType.RED;
                    flag = false;
                }
                default ->{
                    System.out.println("상태값을 잘못 입력하셨습니다. 숫자로 다시 입력해주세요. ");
                    continue;
                }
            }
            if(!StudentManagement.isExistCondition(condition)) {
                System.out.println("현재 상태인 학생이 존재하지 않습니다.");
                flag = true;
            }
        } while (flag);
        List<Student> students = Store.getStudentsByCondition(condition);

        // 각 수강생별로 필수 과목별 점수 합산을 저장할 맵을 생성
        Map<String, Integer> totalScores = new HashMap<>();
        // 각 수강생별로 필수 과목별 회차 합산을 저장할 맵을 생성
        Map<String, Integer> totalRounds = new HashMap<>();

        // 각 수강생에 대해 반복
        for (Student student : students) {
            // 해당 수강생의 필수 과목 정보를 가져오기
            List<String> requiredSubjects = student.getRequiredSubjects();

            // 각 과목별로 점수를 합산 및 회차를 카운트
            for (String subjectId : requiredSubjects) {
                // 해당 수강생의 해당 과목에 대한 점수를 가져오기
                List<Score> scores = Store.getScoresByStudentAndSubject(student.getStudentId(), subjectId);

                // 해당 과목의 총 회차 수를 가져오기!
                int subjectRounds = Store.getSubjectRoundsById(subjectId);

                // 해당 과목의 총 점수를 합산
                int subjectTotalScore = 0;
                for (Score score : scores) {
                    subjectTotalScore += score.getScore();
                }

                // 해당 과목의 점수를 누적하여 맵에 저장
                totalScores.put(subjectId, totalScores.getOrDefault(subjectId, 0) + subjectTotalScore);
                // 해당 과목의 회차를 누적하여 맵에 저장
                totalRounds.put(subjectId, totalRounds.getOrDefault(subjectId, 0) + subjectRounds);
            }
        }

        // 각 수강생의 필수 과목별 평균 등급을 출력
        System.out.println("특정 상태 수강생들의 필수 과목 평균 등급:");
        for (String subjectId : totalScores.keySet()) {
            // 해당 과목의 총 회차 수를 가져오기!
            int subjectTotalRounds = totalRounds.get(subjectId);
            // 해당 과목의 총 점수를 가져오기!
            int subjectTotalScore = totalScores.get(subjectId);

            // 평균 점수를 계산
            double averageScore = (double) subjectTotalScore / subjectTotalRounds;

            // 과목명을 가져오기!
            String subjectName = Store.getSubjectNameBySubjectId(subjectId);
            String grade = Score.calculateGrade((int)averageScore,Store.getSubjectTypeBySubjectId(subjectId));
            // 평균 등급을 출력
            System.out.println(subjectName + ": " + grade);
        }
        System.out.println("\n" + condition + " 상태 수강생들의 필수 과목 평균 등급 출력이 완료됐습니다.");
    }


    // 5. 점수 입력 메소드
    // 점수 객체 생성 및 저장
//------- 위 코드에서 사용되는 메소드들 -------
// 학번으로 해당하는 학생 객체를 찾는 메소드
    // 과목명을 입력받는 메소드
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

    // 해당 과목의 해당 회차 점수가 이미 등록되어 있는지 확인하는 메소드
    private static boolean isScoreExist(Student student, String subjectName, int round) {
        if (Store.getScoreBy(student.getStudentId(), subjectName, round) != null) return true;
        return false;
    }

    // ------------------------------------------------------------------------------------------

// 학생 객체 찾기


//7. void inquiryScoreAtStudent()
    public static void inquirySubjectGrades(Student student, String subjectName) throws InterruptedException {

        List<Score> scores = Store.getScoreStore().stream()
                .filter(a -> a.getSubjectId().equals(subjectName) && a.getStudentId().equals(student.getStudentId()))
                .toList();

        //이거 optional 해줘야될거 같은데 이따가 질문
        for (Score score : scores) {
            System.out.print("[" + score.getRound() + "회차 : " + score.getScore() + " 점 " + score.getGrade() + " 등급]  ");
        }
        System.out.println();
        System.out.println();
        Thread.sleep(500);
    }


    // 학생의 과목 정보와 비교하여 과목 선택 받기

}




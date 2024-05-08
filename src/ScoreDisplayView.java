import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class ScoreDisplayView extends DisplayView {
    static Scanner sc = new Scanner(System.in);


    @Override
    public void displayView() {
        boolean flag = true;

        do {
            System.out.println("1. 점수 등록\n2.점수 수정\n3. 점수 조회\n4. 메인 화면 이동");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    addScore();
                case "2":
                    modScore();
                case "3":
                    displayInquiryScore();
                case "4":
                    flag = false;
                    break;
                default:
                    System.out.println("1~4까지의 정숫를 입력해주세요");
            }
        } while (flag);

    }

    public static void displayInquiryScore() {
        boolean flag = true;
        do {
            System.out.println("1. 수강생 과복별 시험 회차 등급 조회\n2. 수강생의 과목별 평균 등급을 조회\n3. 수강생 상태별 평균 등급을 조회\n4. 메인 화면 이동\n");
            String choose = sc.next();//nextLine 하려면 앞에 정수 입력하고 남은 엔터키 받아주는거 필요
            switch (choose) {
                case "1" -> inqScore();
                case "2" -> inquiryStudentAverageBySubject();
                case "3" -> inquiryStudentAverageByStatus();
                case "4" -> {
                    flag = false;
                }
                default -> {
                    System.out.println("1~4까지의 정숫를 입력해주세요\n");
                }
            }
        } while (flag);
    }

    //경민님
    private static void inquiryStudentAverageByStatus() {
    }

    private static void inquiryStudentAverageBySubject() {
    }

    private static Student searchGetStudent() {

        String studentNum;

        while (true) {
            System.out.println("학번을 입력하세요 : ");
            studentNum = sc.next();
            String finalStudentNum = studentNum;
            Optional<Student> result = students.stream()
                    .filter(student -> Objects.equals(student.getStudentNum(), finalStudentNum))
                    .findFirst();
            // 입력된 학번이 유효한지 ? 확인하기!
            if (result.isPresent()) {
                return result.get();
            } else {
                System.out.println("유효하지 않은 학번입니다. 다시 입력하세요. ");
            }
        }
    }





    // 종원님
    private static void addScore() {
        Student student = searchGetStudent();
        String subjectId = sc.next();
        if(student.checkSubject(subjectId)){
            System.out.println("회차를 입력해주세요.");
            int round = sc.nextInt();
            System.out.println("점수를 입력해주세요.");
            int score = sc.nextInt();
            scoreList.add(new Score(student.getStudentNum(),subjectId, round, score, getSubjectType()));
        };


    }

    private static void modScore() {
    }

    private static void inqScore() {
    }

}

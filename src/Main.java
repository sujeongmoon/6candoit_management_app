import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Student> students;
    private static List<Subject> subjects;
    private static List<Score> scores;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            displayMain();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayMain() {
        boolean flag = true;
        System.out.println("메인페이지입니다\n숫자를 입력해주세요! \n1.학생관리 2. 성적관리  3.나가기");
        String choose = sc.nextLine();
        do {
            switch (choose) {
                case "1": //학생관리
                    displayStudent();
                case "2": //성적관리
                    displayScore();
                case "3":
                    flag = false;
                    break;
            }
        } while (flag);
    }

    public static void displayStudent() {
        boolean flag = true;
        do {
            System.out.println("1. 수강생 등록\n2. 수강생 목록 조회\n3.메인 화면 이동");

            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    createStudent();
                case "2":
                    inquiryStudent();
                case "3":
                    flag = false;
                    displayMain();
            }
        } while (flag);
    }

    public static void displayScore() {
        boolean flag = true;
        do {
            System.out.println("1. 성적 등록\n2.점수 수정\n3. 점수 조회\n4. 종료");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    createScore();
                case "2":
                    setScoreAtStudent();
                case "3":
                    inquiryScoreAtStudent();
                case "4":
                    flag = false;
                    displayMain();
            }
        } while (flag);
    }
}
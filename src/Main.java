import java.util.*;

public class Main {
    private static final StudentDisplayView studentDisplayView = new StudentDisplayView();
    private static final ScoreDisplayView scoreDisplayView = new ScoreDisplayView();


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
        do {
            System.out.println("메인페이지입니다\n숫자를 입력해주세요! \n1.학생관리 2.성적관리  3.나가기");
            String choose = sc.next();
            switch (choose) {
                case "1": //학생관리
                    studentDisplayView.displayView();
                    break;
                case "2": //성적관리
                    scoreDisplayView.displayView();
                    break;
                case "3":
                    flag = false;
                    break;
                default:
                    System.out.println("1~3까지의 정숫를 입력해주세요\n");
            }
        } while (flag);
        System.out.println("프로그램을 종료합니다!");
    }
}

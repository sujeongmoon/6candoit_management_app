package javateamproject.display;

import java.util.Scanner;

public class MainDisplay extends DisplayView{
    public static void displayMain() throws InterruptedException {
        boolean flag = true;
        do {
            System.out.println("메인페이지입니다\n숫자를 입력해주세요! \n1.수강생 관리 2.성적관리  3.나가기");
            String choose = sc.next();
            switch (choose) {
                case "1": //학생관리
                    StudentDisplayView.displayView();
                    break;
                case "2": //성적관리
                    ScoreDisplayView.displayView();
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

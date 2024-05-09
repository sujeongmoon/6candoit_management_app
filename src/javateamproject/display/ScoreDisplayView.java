package javateamproject.display;

import javateamproject.management.ScoreManagement;

public class ScoreDisplayView extends DisplayView {
    public static void displayView() throws InterruptedException {
        boolean flag = true;

        do {
            System.out.println("1. 점수 등록\n2. 점수 수정\n3. 점수 조회\n4. 메인 화면 이동");
            String choose = sc.next();
            switch (choose) {
                case "1":
                    ScoreManagement.addScore();
                    break;
                case "2":
                    ScoreManagement.modScore();
                    break;
                case "3":
                    displayInquiryScore();
                    break;
                case "4":
                    flag = false;
                    break;
                default:
                    System.out.println("1~4까지의 정수를 입력해주세요");
            }
        } while (flag);
    }
    public static void displayInquiryScore() throws InterruptedException {
        boolean flag = true;
        do {
            System.out.println("1. 수강생 과목별 시험 회차 등급 조회\n2. 수강생의 과목별 평균 등급을 조회\n3. 수강생 상태별 평균 등급을 조회\n4. 점수관리 화면 이동\n");
            String choose = sc.next();//nextLine 하려면 앞에 정수 입력하고 남은 엔터키 받아주는거 필요
            switch (choose) {
                case "1" -> ScoreManagement.inqScore();
                case "2" -> ScoreManagement.inquiryStudentAverageBySubject();
                case "3" -> ScoreManagement.inquiryStudentAverageByStatus();
                case "4" -> {
                    flag = false;
                }
                default -> {
                    System.out.println("1~4까지의 정수를 입력해주세요\n");
                }
            }
        } while (flag);
    }

}


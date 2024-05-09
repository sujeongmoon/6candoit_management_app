package javateamproject.display;

import javateamproject.management.StudentManagement;


public class StudentDisplayView extends DisplayView{
    public static void displayView() throws InterruptedException {
        boolean flag = true;
        do {
            System.out.println("학생관리페이지");
            System.out.println("1. 수강생 등록\n2. 수강생 조회\n3. 수강생 수정\n4. 수강생 삭제\n5. 메인 화면 이동\n");
            String choose = sc.next();//nextLine 하려면 앞에 정수 입력하고 남은 엔터키 받아주는거 필요
            switch (choose) {
                case "1" -> StudentManagement.createStudent();
                case "2" -> displayInquiryStudent();
                case "3" -> displayUpdateStudent();
                case "4" -> StudentManagement.removeStudent();
                case "5" -> {
                    flag = false;
                }
                default -> {
                    System.out.println("1~5까지의 정수를 입력해주세요.\n");
                }
            }
        } while (flag);
    }
    public static void displayInquiryStudent() throws InterruptedException {
        boolean flag = true;
        do {
            System.out.println("1. 전체 목록 조회\n2. 수강생 개인 조회\n3. 수강생 상태별 조회\n4. 수강생관리 관리 화면 이동\n");

            String choose = sc.next();//nextLine 하려면 앞에 정수 입력하고 남은 엔터키 받아주는거 필요
            switch (choose) {
                case "1" -> StudentManagement.inquiryStudent();
                case "2" -> StudentManagement.searchStudent();
                case "3" -> StudentManagement.inquiryConditionStudent();
                case "4" -> flag = false;
                default -> {
                    System.out.println("1~4까지의 정수를 입력해주세요\n");
                }
            }
        } while (flag);
    }
    private static void displayUpdateStudent() throws InterruptedException {
        boolean flag = true;
        do {
            System.out.println("1. 이름 수정\n2. 상태 수정(관리)\n3. 메인 화면 이동\n");

            String choose = sc.next();//nextLine 하려면 앞에 정수 입력하고 남은 엔터키 받아주는거 필요
            switch (choose) {
                case "1" -> StudentManagement.updateStudentName();
                case "2" -> StudentManagement.setConditionStudent();
                case "3" -> {
                    flag = false;
                }
                default -> {
                    System.out.println("1~3까지의 정수를 입력해주세요\n");
                }
            }
        } while (flag);
    }
}

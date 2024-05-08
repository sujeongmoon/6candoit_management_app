package javateamproject.management;

import javateamproject.store.Store;
import javateamproject.type.SubjectType;

import java.util.*;

public class StudentManagement {
    private static Scanner sc = new Scanner(System.in);
    //수강생 등록
    public static void createStudent() throws InterruptedException {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        //Student student = searchGetStudent();
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)
        int count = 0;
        List<String> subjectIds = new ArrayList<>();
        System.out.println("\n필수과목을 등록합니다...");
        System.out.println("3개 이상 등록해주세요!");
        List<String> mustSubjects = Store.getSubjectNamesBySubjectType(SubjectType.MUST);
        String subjectId;
        while (true) {
            SubjectManagement.viewSubjects(SubjectType.MUST);
            System.out.print("\n해당하는 필수과목코드를 입력해주세요");
            try {
                subjectId = sc.next();
                if (subjectIds.contains(subjectId)) {
                    System.out.println("이미 선택한 과목입니다.");
                    System.out.println("다시 입력하세요");
                    continue;
                }
                if (!mustSubjects.contains(subjectId)) {
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("목록에 맞는 과목코드를 입력하세요!");
                    continue;
                }
                count++;
                subjectIds.add(subjectId);
            } catch (Exception ignored) {
                System.out.println("잘못 입력하셨습니다.");
                continue;
            }
            System.out.println("필수과목 " + count + "개 입력하셨습니다.");
            System.out.print("\n선택한 과목 목록 입니다.");
            SubjectManagement.viewSubjectSelected(subjectIds);
            if (count >= 5) {
                System.out.println("\n 모든 과목을 입력하셨습니다.");
                break;
            }
            if (count >= 3) {
                System.out.println("\n 필수 과목 3개 이상을 입력하셨습니다.");
                System.out.println("더 많은 필수 과목을 듣고 싶으시면 'y'를 입력하세요");
                String sw = sc.next();
                if (!sw.equalsIgnoreCase("Y")) {
                    break;
                }
            }
        }
        count = 0;
        System.out.println("필수과목이 모두 입력 되었습니다.\n이제 선택과목 입력으로 넘어갑니다.");
        System.out.println("\n선택과목을 등록합니다...");
        System.out.println("2개 이상 등록해주세요!");
        List<String> choiceSubjects = Store.getSubjectNamesBySubjectType(SubjectType.CHOICE);
        while (true) {
            SubjectManagement.viewSubjects(SubjectType.CHOICE);
            System.out.print("\n해당하는 선택과목코드를 입력해주세요");
            try {
                subjectId = sc.next();
                if (subjectIds.contains(subjectId)) {
                    System.out.println("이미 선택한 과목입니다.");
                    System.out.println("다시 입력하세요");
                    continue;
                }
                if (!choiceSubjects.contains(subjectId)) {
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("목록에 맞는 과목코드를 입력하세요!");
                    continue;
                }
                count++;
                subjectIds.add(subjectId);
            } catch (Exception ignored) {
                System.out.println("잘못 입력하셨습니다.");
                continue;
            }
            System.out.println("선택과목 " + count + "개 입력하셨습니다.");
            System.out.print("\n선택한 과목 목록 입니다.");
            SubjectManagement.viewSubjectSelected(subjectIds);
            if (count >= 5) {
                System.out.println("\n 모든 과목을 입력하셨습니다.");
                break;
            }
            if (count >= 3) {
                System.out.println("\n 선택 과목을 2개 이상을 입력하셨습니다.");
                System.out.println("더 많은 필수 과목을 듣고 싶으시면 'y'를 입력하세요");
                String sw = sc.next();
                if (!sw.equalsIgnoreCase("Y")) {
                    break;
                }
            }
        }
        System.out.println("모든 과목이 입력 되었습니다.\n");
        Store.addStudent(studentName, subjectIds); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
        System.out.println("-----------------------");
        System.out.println("이름 : " +studentName);
        System.out.println("학번 : " +"ST"+Store.getStudentIndex());
        SubjectManagement.viewSubjectSelected(subjectIds);
        System.out.println("-----------------------\n");
        Thread.sleep(1000);
    }
    //수강생 전체 목록
    public static void inquiryStudent() throws InterruptedException {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        System.out.println("수강생 목록:");

        for (int i = 0; i < Store.getStudentStore().size(); i++) {
            System.out.print(Store.getStudentStore().get(i).getStudentName() + "(" + Store.getStudentStore().get(i).getStudentId() + ")   ");
            if ((i + 1) % 10 == 0) System.out.println();
        }
        System.out.println();
        System.out.println("\n수강생 목록 조회 성공!");
        Thread.sleep(1000);
    }


    //담당 승완님
    //수강생 제거
    public static void removeStudent() {
    }
    //수강생 개인 정보 조회
    public static void searchStudent() {
    }

    //수강생 이름 수정
    public static void updateStudentName() {
    }

    ////담당 수정님
    //상태수정
    public static void setStatusStudent() {
    }
    //수강생 상태별 조회
    public static void inquiryStatusStudent() {
    }

}

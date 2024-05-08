package javateamproject.management;

import javateamproject.model.Student;
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
            if (count >= 4) {
                System.out.println("\n 모든 과목을 입력하셨습니다.");
                break;
            }
            if (count >= 2) {
                System.out.println("\n 선택 과목을 2개 이상을 입력하셨습니다.");
                System.out.println("더 많은 선택 과목을 듣고 싶으시면 'y'를 입력하세요");
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
        System.out.println("이름 : " + studentName);
        System.out.println("학번 : " + "ST" + Store.getStudentIndex());
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
    // 4. 학번으로 해당하는 객체 인스턴스 가져오는 조회 매소드


    //(1). 학번 입력 받기 : 사용자로부터 학번을 입력받음.

    // user로 부터 학번을 입력받고, 입력된 학번이 유효한지 확인!
    // 유효한 학번이 입력될 때까지 반복해서 입력을 받기.

    private static String getStudentNumFromUser() {

        String studentNum;

        while (true) {
            System.out.println("학번을 입력하세요 : ");
            studentNum = sc.next();


            // 입력된 학번이 유효한지 ? 확인하기!
            if (isValidStudentNum(studentNum)) {
                return studentNum;
            } else {
                System.out.println("유효하지 않은 학번입니다. 다시 입력하세요. ");
            }
        }
    }
    //(2). 학번이 있는지 없는지 체크(없을 경우 (예외처리 및 다시받기)
    // == 학번 유효성 검사 : 입력된 학번이 유효한지 확인! , 유효하지 않은 학번일 경우 예외 처리!
    // 입력된 학번이 유효한지를 검사하는 메소드,
    // 입력된 학번이 "ST"로 시작하는지를 확인하여 유효성을 판단.

    private static boolean isValidStudentNum(String studentNum) {
        //private => 해당 멤버가 같은 클래스 내에서만 접근
        // => isValidStudentId 메소드는 CampManagementApplication 클래스 내부에서만 접근 / 외부에서 호출 x
        // isValidStudentNum => 주어진 학번이 유효한지 검사 => String 형식의 studentNum을 매개변수로 받아서 검사.
        return studentNum.startsWith("ST");  //startsWith() 메소드는 문자열이 지정된 문자로 시작하는지 확인
    }
     //3). 학번에 맞는 학생 인스턴스 리턴


    //(3-1). 학번에 해당하는 학생 객체 찾기
    // 입력된 학번에 해당하는 학생 객체를 찾는 메소드
    // 'studentStore'에 저장된 학생 객체들을 순회하면서 입력된 학번과 일치하는 학생 객체를 찾아 반환!
    // 주어진 학번과 일치하는 학생 객체를 studentStore에서 찾아 반환하는 메소드인 findStudentByStudentNum

    public static Student findStudentByStudentNum(String studentNum) {
        for (Student student : Store.getStudentStore()) {
            if (student.getStudentId().equals(studentNum)) {
                return student;
            }
        }
        return null; // 해당 학번에 해당하는 학생이 없을 경우 null 반환
    }

    //(3-2). 찾은 학생 객체 반환
    // 위 과정을 통합하여 학번으로 해당하는 학생 객체를 찾아주는 메소드
    // 학번을 입력받고, 입력된 학번의 유효성을 검사하고, 유효한 학번인 경우 findStudentByStudentNum를 통해 해당하는 학생 객체를 찾아 반환
    // if 입력된 학번에 해당하는 학생이 없을 경우에는 예외 throw.

    public static Student searchGetStudent() {
        String studentNum;
        Student student;

        // 학번 입력 받기
        do {
            studentNum = getStudentNumFromUser();
            // 학번 유효성 검사
            if (!isValidStudentNum(studentNum)) {
                System.out.println("유효하지 않은 학번입니다. 다시 입력하세요.");
            }
        } while (!isValidStudentNum(studentNum));

        // 학번에 해당하는 학생 객체 찾기
        student = findStudentByStudentNum(studentNum);
        if (student == null) {
            throw new IllegalArgumentException("해당 학번에 해당하는 학생이 없습니다.");
        }

        return student;
    }


}
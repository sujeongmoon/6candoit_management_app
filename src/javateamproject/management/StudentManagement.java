package javateamproject.management;

import javateamproject.model.Student;
import javateamproject.model.Subject;
import javateamproject.store.Store;
import javateamproject.type.ConditionType;
import javateamproject.type.SubjectType;

import javax.swing.plaf.BorderUIResource;
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
            System.out.print("\n해당하는 필수과목 코드를 입력해주세요.");
            try {
                subjectId = sc.next();
                if (subjectIds.contains(subjectId)) {
                    System.out.println("이미 선택한 과목입니다.");
                    System.out.println("다시 입력하세요.");
                    continue;
                }
                if (!mustSubjects.contains(subjectId)) {
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("목록에 맞는 과목 코드를 입력하세요!");
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
                System.out.println("\n모든 과목을 입력하셨습니다.");
                break;
            }
            if (count >= 3) {
                System.out.println("\n필수과목 3개 이상을 입력하셨습니다.");
                System.out.println("더 많은 필수과목을 듣고 싶으시면 'y'를 입력하세요.");
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
            System.out.print("\n해당하는 선택과목 코드를 입력해주세요.");
            try {
                subjectId = sc.next();
                if (subjectIds.contains(subjectId)) {
                    System.out.println("이미 선택한 과목입니다.");
                    System.out.println("다시 입력하세요.");
                    continue;
                }
                if (!choiceSubjects.contains(subjectId)) {
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("목록에 맞는 과목 코드를 입력하세요!");
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
                System.out.println("\n선택과목을 2개 이상을 입력하셨습니다.");
                System.out.println("더 많은 선택과목을 듣고 싶으시면 'y'를 입력하세요.");
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
        System.out.println("학번 : " + "ST" + (Store.getStudentIndex()));
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
    public static void removeStudent() throws InterruptedException {
        StudentManagement.inquiryStudent();
        System.out.println("삭제할 학번을 입력하세요:");
        String studentNum = sc.next();
        System.out.print("확인용으로 한 번 더 ");
        Student student = searchGetStudent();
        String confirmNum = student.getStudentId();
        if(confirmNum.equals(studentNum)) {
            Store.deleteStudent(studentNum);
            System.out.println("삭제가 완료되었습니다.");
            StudentManagement.inquiryStudent();  // 학생 목록 보여주기

        } else{
            System.out.println("학번이 일치하지 않습니다.");
        }
        Thread.sleep(1000);
    }

    //수강생 개인 정보 조회
    public static void searchStudent() throws InterruptedException {
        StudentManagement.inquiryStudent();
        System.out.println("수강생의 개인정보를 조회합니다.");
        try{
            Student student =searchGetStudentAtStudent();
            System.out.println("========================================================");
            System.out.println("수강생의 개인정보 입니다.");
            System.out.println("이름: " + student.getStudentName());
            System.out.println("학번: " + student.getStudentId());
            System.out.println("과목: " +student.getSelectSubjectIds());
            System.out.println("컨디션: "+student.getCondition());
            System.out.println("=======================================================");
            Thread.sleep(1000);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //수강생 이름 수정
    public static void updateStudentName() {
        System.out.println("수강생의 이름을 수정합니다.");
        try{
            StudentManagement.inquiryStudent();  // 학생 목록 보여주기

            Student student =searchGetStudentAtStudent();
            System.out.println("선택하신 수강생의 이름은 " + student.getStudentName() +  "입니다.");
            System.out.println("변경할 이름을 적어주세요.");

            String studentName = sc.next();
            System.out.println(studentName+" (으)로 하시겠습니까? 맞으면 y를 입력해주세요.");
            String yesOrNo = sc.next();
            if(yesOrNo.equals("y") || yesOrNo.equals("Y")){
                student.setStudentName(studentName);
                System.out.println("수정되었습니다, " + studentName + " 님.");
            } else{
                System.out.println("y가 입력되지 않았습니다. 수정페이지로 이동합니다...");
            }
            Thread.sleep(1000);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    ////담당 수정님
    //상태수정
    public static void setConditionStudent() throws InterruptedException {


        System.out.println("상태를 관리할 수강생의 학번을 입력해주세요.");
        StudentManagement.inquiryStudent();  // 학생 목록 보여주기
        Student student = searchGetStudentAtStudent();
        System.out.println("현재 " + student.getStudentName() + "님의 상태는 " + student.getCondition() + "입니다.\n");

        System.out.println(student.getStudentName() + "수강생의 상태를 숫자로 입력해주세요.\n1.GREEN 2.YELLOW 3.RED");
        boolean flag = true;
        do {
            String conditionChoose = sc.next();
            switch (conditionChoose) {
                case "1" -> {
                    student.setCondition(ConditionType.GREEN);
                    flag = false;
                }
                case "2" -> {
                    student.setCondition(ConditionType.YELLOW);
                    flag = false;
                }
                case "3" -> {
                    student.setCondition(ConditionType.RED);
                    flag = false;
                }
                default -> System.out.println("상태값을 잘못 입력하셨습니다. 숫자로 다시 입력해주세요. ");
            }
        } while (flag);

        System.out.println("변경된 " + student.getStudentName() + "님의 상태는 " + student.getCondition() + "입니다.\n");
        Thread.sleep(1000);
    }

    //수강생 상태별 조회
    public static void inquiryConditionStudent() throws InterruptedException {

        boolean flag = true;
        ConditionType inputCondition = null;

        System.out.println("상태별 수강생을 조회합니다. 상태를 숫자로 입력해주세요.\n1.GREEN 2.YELLOW 3.RED");

        do {
            String conditionChoose = sc.next();
            switch (conditionChoose) {
                case "1" -> {
                    inputCondition = ConditionType.GREEN;
                    flag = false;
                }
                case "2" -> {
                    inputCondition = ConditionType.YELLOW;
                    flag = false;
                }
                case "3" -> {
                    inputCondition = ConditionType.RED;
                    flag = false;
                }
                default -> System.out.println("상태값을 잘못 입력하셨습니다. 숫자로 다시 입력해주세요. ");
            }

        } while (flag);

        for (int i = 0; i < Store.getStudentStore().size(); i++) {
            if (inputCondition == Store.getStudentStore().get(i).getCondition()) {
                System.out.printf(Store.getStudentStore().get(i).getStudentName() + "(" + Store.getStudentStore().get(i).getStudentId() + ")   ");
                if ((i + 1) % 10 == 0) System.out.println();
            }

        }
        System.out.println("\n\n" + inputCondition + " 상태의 수강생이 전부 출력되었습니다.\n");
        Thread.sleep(1000);
    }
    // 4. 학번으로 해당하는 객체 인스턴스 가져오는 조회 매소드


    //(1). 학번 입력 받기 : 사용자로부터 학번을 입력받음.

    // user로 부터 학번을 입력받고, 입력된 학번이 유효한지 확인!
    // 유효한 학번이 입력될 때까지 반복해서 입력을 받기.

    private static String getStudentNumFromUser() {

        String studentNum;

        while (true) {
            System.out.print("학번을 입력하세요 : ");
            studentNum = sc.next();


            // 입력된 학번이 유효한지 ? 확인하기!
            if (isValidStudentNum(studentNum)) {
                return studentNum;
            } else {
                System.out.println("존재하지 않는 학번입니다.");
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
        Optional<Student> result = Store.getStudentStore().stream()
                .filter(student -> student.getStudentId().equals(studentNum))
                .findFirst();
        return result.isPresent();  //startsWith() 메소드는 문자열이 지정된 문자로 시작하는지 확인
    }


    //(3-2). 찾은 학생 객체 반환
    // 위 과정을 통합하여 학번으로 해당하는 학생 객체를 찾아주는 메소드
    // 학번을 입력받고, 입력된 학번의 유효성을 검사하고, 유효한 학번인 경우 findStudentByStudentNum를 통해 해당하는 학생 객체를 찾아 반환
    // if 입력된 학번에 해당하는 학생이 없을 경우에는 예외 throw.

    public static Student searchGetStudent() {
        String studentId;
        while (true){

            studentId = getStudentNumFromUser();
            if (!ScoreManagement.isScoreExistByStudentId(studentId)) {
                System.out.println("점수 정보가 존재 하지 않는 수강생입니다.");
            }else{
                return isExistStudent(studentId).get();
            }
        }
    }
    public static Optional<Student> isExistStudent(String studentId){
        Optional<Student> result = Store.getStudentStore().stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst();
        return result;
    }
    public static boolean isExistCondition(ConditionType conditionType){
        Optional<Student> result = Store.getStudentStore().stream()
                .filter(student -> student.getCondition()==conditionType)
                .findFirst();
        return result.isPresent();
    }
    private static Student searchGetStudentAtStudent(){
        while(true){
            String studentId = getStudentNumFromUser();
            Optional<Student> result = Store.getStudentStore().stream()
                    .filter(student -> student.getStudentId().equals(studentId))
                    .findFirst();
            if(result.isPresent()) return result.get();
        }

    }


}
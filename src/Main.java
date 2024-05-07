import java.util.*;

public class Main {
    private static List<Student> studentS ;
    private static List<Subject> subjectS ;
    private static List<Score> scoreS ;
    //private static List<Score> scores = new ArrayList<>();
    private static int studentIndex;
    private static int scoreIndex;
    private static int subjectIndex ;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInit();
        try {
            displayMain();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setInit(){
        studentS = new ArrayList<>();
        subjectS = List.of(
                new Subject(
                        sequence(classType.SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }
    public static void displayMain() throws InterruptedException {
        boolean flag = true;
        do {
            System.out.println("메인페이지입니다\n숫자를 입력해주세요! \n1.학생관리 2.성적관리  3.나가기");
            String choose = sc.next();
            switch (choose) {
                case "1": //학생관리
                    displayStudent();
                    break;
                case "2": //성적관리
                    displayScore();break;
                case "3":
                    flag = false;
                    break;
                default:
                    System.out.println("1~3까지의 정숫를 입력해주세요\n");
            }
        } while (flag);
        System.out.println("프로그램을 종료합니다!");
    }

    public static void displayStudent() throws InterruptedException {
        boolean flag = true;
        do {
            System.out.println("1. 수강생 등록\n2. 수강생 조회\n3. 수강생 수정\n4. 수강생 삭제\n5. 메인 화면 이동\n");

            String choose = sc.next();//nextLine 하려면 앞에 정수 입력하고 남은 엔터키 받아주는거 필요
            switch (choose) {
                case "1"-> createStudent();
                case "2"-> displayInquiryStudent();
                case "3"-> displayUpdateStudent();
                case "4"-> removeStudent();
                case "5"-> {
                    flag = false;
                    displayMain();
                }
                default->{System.out.println("1~5까지의 정숫를 입력해주세요\n");}
            }
        } while (flag);
    }
    public static void displayInquiryStudent() throws InterruptedException {
        boolean flag = true;
        do {
            System.out.println("1. 전체 목록 조회\n2. 수강생 개인 조회\n3. 수강생 상태별 조회\n4. 메인 화면 이동\n");

            String choose = sc.next();//nextLine 하려면 앞에 정수 입력하고 남은 엔터키 받아주는거 필요
            switch (choose) {
                case "1"-> inquiryStudent();
                case "2"-> searchStudent ();
                case "3"-> inquiryStatusStudent();
                case "5"-> {
                    flag = false;
                    displayMain();
                }
                default->{System.out.println("1~4까지의 정숫를 입력해주세요\n");}
            }
        } while (flag);
    }



    private static void displayUpdateStudent() throws InterruptedException {
        boolean flag = true;
        do {
            System.out.println("1. 이름 수정\n2. 상태 수정(관리)\n3. 메인 화면 이동\n");

            String choose = sc.next();//nextLine 하려면 앞에 정수 입력하고 남은 엔터키 받아주는거 필요
            switch (choose) {
                case "1"->  updateStudentName();
                case "2"-> setStatusStudent ();
                case "3"-> {
                    flag = false;
                    displayMain();
                }
                default->{System.out.println("1~3까지의 정숫를 입력해주세요\n");}
            }
        } while (flag);
    }

    public static void displayScore() throws InterruptedException {
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
                    displayMain();
                    break;
                default:
                    System.out.println("1~4까지의 정숫를 입력해주세요");
            }
        } while (flag);
    }




    public static void displayInquiryScore() throws InterruptedException {
        boolean flag = true;
        do {
            System.out.println("1. 수강생 과복별 시험 회차 등급 조회\n2. 수강생의 과목별 평균 등급을 조회\n3. 수강생 상태별 평균 등급을 조회\n4. 메인 화면 이동\n");

            String choose = sc.next();//nextLine 하려면 앞에 정수 입력하고 남은 엔터키 받아주는거 필요
            switch (choose) {
                case "1"-> inqScore();
                case "2"-> inquiryStudentAverageBySubject();
                case "3"->  inquiryStudentAverageByStatus();
                case "4"-> {
                    flag = false;
                    displayMain();
                }
                default->{System.out.println("1~4까지의 정숫를 입력해주세요\n");}
            }
        } while (flag);
    }

    private static String sequence(classType classtype) {
        switch (classtype){
            case STUDENT -> {
                return classtype.getIdxName()+studentIndex++;
            }
            case SCORE -> {
                return classtype.getIdxName()+scoreIndex++;
            }
            case SUBJECT -> {
                return classtype.getIdxName()+subjectIndex++;
            }
            default -> {
                return null;
            }
        }
    }

    private static void createStudent() throws InterruptedException {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        //Student student = searchGetStudent();
        String studentName = sc.next();

        // 기능 구현 (필수 과목, 선택 과목)
        int m_idx = 0;
        int c_idx = 0;
        int count = 0;
        List<Integer> subjectIds = new ArrayList<>();
        System.out.println("\n필수과목을 등록합니다...");
        System.out.println("3개 이상 등록해주세요!");
        boolean[] visited = new boolean[9];
        while (true) {
            System.out.println("\n과목 목록 : Java : 0 ,  객체지향 :  1 ,  Spring : 2 , JAP : 3 , Mysql : 4");
            System.out.print("\n해당하는 필수과목 번호를 입력해주세요");
            try {
                c_idx = sc.nextInt();
                if (visited[c_idx]) {
                    System.out.println("이미 선택한 과목입니다.");
                    System.out.println("다시 입력하세요");
                    continue;
                }
                if (c_idx < 0 || c_idx > 4) {
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("1~5까지의 정수를 입력해주세요");
                    continue;
                }
                visited[c_idx] = true;
                count++;
                subjectIds.add(c_idx);
            } catch (Exception ignored) {
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("0~4까지의 정수를 입력해주세요");
                continue;
            }
            System.out.println("필수과목 " + count + "개 입력하셨습니다.");
            viewSubjects(subjectIds);
            if (count >= 5) {
                System.out.println("\n 모든 과목을 입력하셨습니다.");
                break;
            }
            if (count >= 3) {
                System.out.println("\n 필수 과목 3개 이상을 입력하셨습니다.");
                System.out.println("선택과목을 임력하고 싶으시면 'y'를 입력하셍요");
                String sw = sc.next();
                if (sw.equalsIgnoreCase("Y")) {
                    break;
                }
            }
        }
        count = 0;
        System.out.println("필수과목이 모두 입력 되었습니다.\n이제 선택과목 입력으로 넘어갑니다.");
        System.out.println("\n선택과목을 등록합니다...");
        System.out.println("2개 이상 등록해주세요!");
        while (true) {
            System.out.println("\n과목 목록 : 디자인 패턴 : 5, Spring Security : 6,  Redis : 7, MongoDB : 8");
            System.out.print("\n해당하는 선택과목 번호를 입력해주세요");
            try {
                m_idx = sc.nextInt();
                if (visited[m_idx]) {
                    System.out.println("이미 선택한 과목입니다.");
                    System.out.println("다시 입력하세요");
                    continue;
                }
                if (m_idx < 5 || m_idx > 8) {
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("5~8까지의 정수를 입력해주세요");
                    continue;
                }
                count++;
                visited[m_idx] = true;
                subjectIds.add(m_idx);
            } catch (Exception ignored) {
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("5~8까지의 정수를 입력해주세요");
                continue;
            }
            System.out.println("선택과목" +
                    " " + count + "개 입력하셨습니다.");
            viewSubjects(subjectIds);
            if (count >= 4) {
                System.out.println("\n 모든 과목을 입력하셨습니다.");
                break;
            }
            if (count >= 2) {
                System.out.println("\n 선택 과목 2개 이상을 입력하셨습니다.");
                System.out.println("종료하고 싶으시면 'y'를 입력하셍요");
                String sw = sc.next();
                if (sw.equalsIgnoreCase("Y")) {
                    break;
                }
            }
        }
        System.out.println("모든 과목이 입력 되었습니다.\n");
        students.add(new Student(sequence(classType.STUDENT), studentName, subjectIds)); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
        System.out.println("-----------------------");
        System.out.println("이름 : " +studentName);
        System.out.println("학번 : " +"ST"+studentIndex);
        viewSubjects(subjectIds);
        System.out.println("-----------------------\n");
        Thread.sleep(1000);
    }

    // 수강생 목록 조회
    private static void inquiryStudent() throws InterruptedException {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        System.out.println("수강생 목록:");

        for (int i = 0; i < students.size(); i++) {
            System.out.print(students.get(i).getStudentName() + "(" + students.get(i).getStudentNum() + ")   ");
            if ((i + 1) % 10 == 0) System.out.println();
        }
        System.out.println();
        System.out.println("\n수강생 목록 조회 성공!");
        Thread.sleep(1000);
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

    private static String getSubjectName(int idx){return subjectS[idx];}
    private static void viewSubjects(List<Integer> subjectsItem) {
        boolean[] sw = new boolean[2];//필수, 선택 과목 출력 여부 체크
        for (int i : subjectsItem) {
            if (i < 5 && !sw[0]) {
                System.out.print("필수 과목 목록 : ");
                sw[0] = true;
            }
            if (i >= 5 && !sw[1] ) {
                System.out.print("\n선택 과목 목록 : ");
                sw[1] = true;
            }
            System.out.print(getSubjectName(i) + "(" + i + ")  ");
        }
        System.out.println();
    }

    //여기서 부터 승완님
    private static void searchStudent() {
    }

    private static void removeStudent() {
    }
    private static void updateStudentName() {
    }


    //여기서 부터 수정님
    private static void inquiryStatusStudent() {
    }
    private static void setStatusStudent() {

    }



    // 종원님
    private static void addScore() {
    }
    private static void modScore() {
    }
    private static void inqScore() {
    }



    //경민님
    private static void inquiryStudentAverageByStatus() {
    }
    private static void inquiryStudentAverageBySubject() {
    }

}
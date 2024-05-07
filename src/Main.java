import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Student> students;
    private static List<Subject> subjects;
    private static List<Score> scores;
    private static int studentIndex =0;

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

    public static void displayStudent() throws InterruptedException {
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

    private static String sequence() {
        return "ST" + studentIndex++;
    }

    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = searchGetStudent();
        // 기능 구현 (필수 과목, 선택 과목)
        int m_idx = 0;
        int c_idx = 0;
        int count=0;
        List<Integer> subjectIds = new ArrayList<>();
        System.out.println("\n필수과목을 등록합니다...");
        System.out.println("3개 이상 등록해주세요!");
        boolean[] visited = new boolean[9];
        while(true){
            System.out.println("\n과목 목록 : Java : 0 ,  객체지향 :  1 ,  Spring : 2 , JAP : 3 , Mysql : 4");
            System.out.print("\n해당하는 필수과목 번호를 입력해주세요");
            try{
                c_idx = sc.nextInt();
                if(visited[c_idx]){
                    System.out.println("이미 선택한 과목입니다.");
                    System.out.println("다시 입력하세요");
                    continue;
                }
                if(c_idx<0 || c_idx>4){
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("1~5까지의 정수를 입력해주세요");
                    continue;
                }
                visited[c_idx]= true;
                count++;
                subjectIds.add(c_idx) ;
            }catch (Exception ignored){
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("0~4까지의 정수를 입력해주세요");
                continue;
            }
            System.out.println("필수과목 " +count+"개 입력하셨습니다.");
            if(count>=5){
                System.out.println("\n 모든 과목을 입력하셨습니다.");
                break;
            }if(count>=3){
                System.out.println("\n 필수 과목 3개 이상을 입력하셨습니다.");
                System.out.println("선택과목을 임력하고 싶으시면 'y'를 입력하셍요");
                String sw = sc.next();
                if(sw.equalsIgnoreCase("Y")){
                    break;
                }
            }
        }
        count=0;
        System.out.println("필수과목이 모두 입력 되었습니다.\n이제 선택과목 입력으로 넘어갑니다.");
        System.out.println("\n선택과목을 등록합니다...");
        System.out.println("2개 이상 등록해주세요!");
        while(true){
            System.out.println("\n과목 목록 : 디자인 패턴 : 5, Spring Security : 6,  Redis : 7, MongoDB : 8");
            System.out.print("\n해당하는 선택과목 번호를 입력해주세요");
            try{
                m_idx = sc.nextInt();
                if(visited[m_idx]){
                    System.out.println("이미 선택한 과목입니다.");
                    System.out.println("다시 입력하세요");
                    continue;
                }
                if(m_idx<5 || m_idx>8){
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("5~9까지의 정수를 입력해주세요");
                    continue;
                }
                count++;
                visited[m_idx-1]=true;
                subjectIds.add(c_idx-1) ;
            }catch (Exception ignored){
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("5~9까지의 정수를 입력해주세요");
                continue;
            }
            System.out.println("선택과목 " +count+"개 입력하셨습니다.");
            if(count>=4){
                System.out.println("\n 모든 과목을 입력하셨습니다.");
                break;
            }
            if(count>=2){
                System.out.println("\n 선택 과목 2개 이상을 입력하셨습니다.");
                System.out.println("종료하고 싶으시면 'y'를 입력하셍요");
                String sw = sc.next();
                if(sw.equalsIgnoreCase("Y")){
                    break;
                }
            }
        }
        System.out.println("모든 과목이 입력 되었습니다.\n");
        students.add(new Student(sequence(), studentName,subjectIds)); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquiryStudent() throws InterruptedException {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        System.out.println("수강생 목록:");

        for(int i=0;i<students.size();i++){
            System.out.print(students.get(i).getStudentName()+"("+studentStore.get(i).getStudentId()+")   ");
            if((i+1)%10 == 0 ) System.out.println();
        }
        System.out.println();
        System.out.println("\n수강생 목록 조회 성공!");
        Thread.sleep(1000);
    }




}
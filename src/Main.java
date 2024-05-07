import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
































































































































































































    private static int studentIndex =0;
    private static String sequence(String type) {
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
        studentStore.add(new Student(sequence(INDEX_TYPE_STUDENT), studentName,subjectIds)); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() throws InterruptedException {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        System.out.println("수강생 목록:");

        for(int i=0;i<studentStore.size();i++){
            System.out.print(studentStore.get(i).getStudentName()+"("+studentStore.get(i).getStudentId()+")   ");
            if((i+1)%10 == 0 ) System.out.println();
        }
        System.out.println();
        System.out.println("\n수강생 목록 조회 성공!");
        Thread.sleep(1000);
    }







}
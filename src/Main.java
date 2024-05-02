import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        while(true){ Scanner sc = new Scanner(System.in);
            Student student = new Student();
            System.out.println("학번");
            student.setStudentNum(sc.nextInt());
            System.out.println("이름");
            student.setStudentName(sc.next());

            for (int i = 0; i < 1; i++) {
                System.out.println("필수과목");
                String subject = sc.next();
                Subject Subject1 = new Subject(subject);
                student.requiredSubject.add(Subject1);
                System.out.println(student.getRequiredSubject());
            }
            for (int i = 0; i < 1; i++) {
                System.out.println("선택과목");
                String Subject = sc.next();
                Subject Subject2 = new Subject(Subject);
                student.requiredSubject.add(Subject2);
                System.out.println(student.getRequiredSubject());
            }
                Student.studentList.add(student);
                System.out.println(Student.getStudentList());
            // 수강생 인스턴스 생성, 매개변수 = 학번, 이름, 과목(포함)
//        Student student = new Student(studentNum, studentName, new Subject(Subject), electiveSubject);

        }
    }

    //subject name은 Enum으로?
    //필수과목을 타이핑하면서 과목을 선언하고 점수를 끌어온다.
    // Subject subject = new Subject(Subject, new Score())

}
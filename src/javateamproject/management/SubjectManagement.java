package javateamproject.management;

import javateamproject.model.Subject;
import javateamproject.store.Store;
import javateamproject.type.SubjectType;

import java.util.List;
import java.util.Scanner;

public class SubjectManagement {
    private static Scanner sc = new Scanner(System.in);
    //필수, 선택 과목별 목록 보여주기
    public static void viewSubjects(SubjectType subjectType) {
        List<Subject> subjects = Store.getSubjectStore();
        if(subjectType.equals(SubjectType.MUST)){
            System.out.println("필수 과목 목록 입니다.");
            for(Subject s : subjects){
                if(s.getSubjectType().equals(SubjectType.MUST))
                    System.out.print(s.getSubjectName()+"("+s.getSubjectId()+")  ");
            }
        }else{
            System.out.println("선택 과목 목록 입니다.");
            for(Subject s : subjects){
                if(s.getSubjectType().equals(SubjectType.CHOICE))
                    System.out.print(s.getSubjectName()+"("+s.getSubjectId()+")  ");
            }
        }
        System.out.println();
    }
    //subjectIds에 해당하는 과목이름(과목코드) 출력
    public static void viewSubjectSelected(List<String> subjectIds) {
        for(String subjedtId : subjectIds){
            for(Subject s :Store.getSubjectStore()){
                if(subjedtId.equals(s.getSubjectId())) System.out.print(s.getSubjectName()+"("+s.getSubjectId()+")  ");
            }
        }
        System.out.println();
    }


}

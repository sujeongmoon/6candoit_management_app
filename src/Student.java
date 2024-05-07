
import java.util.ArrayList;
import java.util.List;

public class Student {


    // 고유번호
    private int studentNum;
    // 이름
    private String studentName;
    // 과목
    private List<Integer> Subjects;

    private List<Score> scoreStores;

    //생성자
    public Student(int studentNum, String studentName, List<Integer> Subjects) {
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.Subjects = Subjects;
    }

    //메서드d


    public List<Score> getScoreStores() {
        return this.scoreStores;
    }

    public void addScoreStores(Score score) {
       scoreStores.add(score);
    }
    /*
    특정 점수 찾기 메서드, 미확정
    */
    public Score searchGetScore( int subjectIdx, int roundNum) {
        Score score = scoreStores.stream()
                .filter(a -> a.getSubjectNum() == subjectIdx && a.getRoundNum() == roundNum)
                .findFirst()
                .orElseThrow(null);
        System.out.println(score.getExamScore());
        return score;
    }

    public List<Score> searchGetSore( int subjectIdx, int roundNum) {
        List<Score> score = scoreStores.stream()
                .filter(a ->  a.getRoundNum() == roundNum)
                .toList();

        return score;
    }
    /*
    학번 getter
     */
    public int getStudentNum() {
        return studentNum;
    }
    /*
    이름 getter
     */
    public String getStudentName() {
        return studentName;
    }
    /*
   과목 getter
   */
    public List<Integer> getSubjects() {
        return Subjects;
    }



}




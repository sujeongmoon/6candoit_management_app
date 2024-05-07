
import java.util.List;

public class Student {


    // 고유번호
    private String studentNum;
    // 이름
    private String studentName;
    // 과목
    private List<Integer> Subjects;
    //삭제
    private Score score;

    //생성자
    public Student(String studentNum, String studentName, List<Integer> Subjects) {
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.Subjects = Subjects;
        score = new Score();
    }

    //메서드


    /*
    특정 점수 찾기 메서드
    */
    /*public Score searchGetScore( int subjectIdx, int roundNum) {
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
    }*/
    /*
    학번 getter
     */
    public String getStudentNum() {
        return studentNum;
    }

    /*
    이름 getter
     */
    public String getStudentName() {
        return studentName;
    }

    /*

     */
    public List<Integer> getSubjects() {
        return Subjects;
    }

    /*
    수강생이 선택한 과목과 입력받은 과목이 일치하지 않는 경우를 검증
     */
    public boolean checkSubjectScore(int subjectNum) {
        for (Integer subject : Subjects) {
            if (subject == subjectNum) {
                return true;
            }
        }
        System.out.println("수강하지 않는 과목입니다.");
        System.out.println("다시 입력해주세요.");
        return false;
    }

    public void addScore(int scoreSubject, int score, int round) {

            if (checkSubjectScore(scoreSubject)) {
                this.score.createScore(scoreSubject, score, round);

            }
            checkSubjectScore(scoreSubject);


    }


    public void modScore(int scoreSubject, int score, int round) {

            if (checkSubjectScore(scoreSubject)) {
                this.score.modifyScore(scoreSubject, score, round);

            }
            checkSubjectScore(scoreSubject);

    }


    public void inqScore(int scoreSubject) { // 매개변수를 없앨 수 있는지

            if (checkSubjectScore(scoreSubject)) {
                this.score.inquiryScore(scoreSubject);
            }
            checkSubjectScore(scoreSubject);

    }
}





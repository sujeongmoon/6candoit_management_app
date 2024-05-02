public class Score {

    // 과목고유번호
    private int subjectNum;

   private int roundNum;
    // 점수
  private int examScore;
    // 등급
  private char grade;

    public Score ( ){

    }
    public void setSubjectNum(int subjectNum){
        this.subjectNum = subjectNum;
    }
    public void setStudentNum(int studentNum){
        this.subjectNum = studentNum;
    }
    public void setRoundNum(int roundNum){
        this.roundNum = roundNum;
    }
    public void setExamScore(int examScore){
        this.examScore = examScore;
    }


}

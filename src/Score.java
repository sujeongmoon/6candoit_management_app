public class Score implements Comparable<Score>{
    private final String subjectId;
    private final String studentId;
    private final String scoreId;
    private String SubjectType;
    private String grade;
    private int score;
    private int round=0;

    public Score( String scoreId,String studentId, String subjectId, int round, int score, String subjectType) {
        this.scoreId = scoreId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.score = score;
        this.round = round;
        if(subjectType.equals("essential")){
            if(score>=95) grade = "A";
            else if(score>=90) grade="B";
            else if(score>=80) grade="C";
            else if(score>=70) grade="D";
            else if(score>=60) grade="F";
            else grade="N";
        }
        else{
            if(score>=90) grade = "C";
            else if(score>=80) grade="B";
            else if(score>=70) grade="C";
            else if(score>=60) grade="D";
            else if(score>=50) grade="F";
            else grade="N";
        }
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }
    public String getSubjectId(){
        return subjectId;
    }
    public String getStudentId(){
        return studentId;
    }
    public int getRound(){
        return round;
    }
    public int getScore(){
        return score;
    }
    public String getGrade(){
        return  grade;
    }

    public void setScore(int score){
        this.score=score;
        if(this.SubjectType.equals("essential")){
            if(score>=95) grade = "A";
            else if(score>=90) grade="B";
            else if(score>=80) grade="C";
            else if(score>=70) grade="D";
            else if(score>=60) grade="F";
            else grade="N";
        }
        else{
            if(score>=90) grade = "C";
            else if(score>=80) grade="B";
            else if(score>=70) grade="C";
            else if(score>=60) grade="D";
            else if(score>=50) grade="F";
            else grade="N";
        }
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.round,o.round);
    }

}

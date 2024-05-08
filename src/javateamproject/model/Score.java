package javateamproject.model;

import javateamproject.type.SubjectType;

public class Score implements Comparable<Score>{
    private final String subjectId;
    private final String studentId;
    private final String scoreId;
    private SubjectType subjectType;
    private String grade;
    private int score;
    private int round=0;

    public Score(String scoreId, String studentId, String subjectId, int round, int score, SubjectType subjectType) {
        this.scoreId = scoreId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.score = score;
        this.round = round;
        this.grade = calculateGrade(score,subjectType);
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

    public void setScore(int score,SubjectType subjectType){
        this.score=score;
        this.grade = calculateGrade(score,subjectType);
    }
    //정렬용 라운드 크기 기준으로
    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.round,o.round);
    }

    public static String calculateGrade(int score,SubjectType subjectType){
        String grade;
        if(subjectType.equals(SubjectType.MUST)){
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
        return grade;
    }

}

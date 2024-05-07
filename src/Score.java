import java.util.ArrayList;

public class Score {

    /* 필드 */
    private ArrayList<SubjectScore> subjectScores; //

    /* 생성자 */
    Score() {
        this.subjectScores = new ArrayList<>();
    }

    /* 메서드 */
    // 원하는 과목의 시험 회차 및 점수 등록하기
    public void setScore(int scoreSubject, int score, int round) {
        SubjectScore subjectScore = findSubjectScore(scoreSubject);
        subjectScore.setSubjectScore(score, round);
    }

    // 원하는 과목의 시험 회차 및 점수 수정하기
    public void modifyScore(int scoreSubject, int score, int round) {
        SubjectScore subjectScore = findSubjectScore(scoreSubject);
        subjectScore.modifySubjectScore(score, round);
    }

    // 원하는 과목의 시험 회차 및 등급 조회하기
    public void inquiryScore(int scoreSubject) {
        SubjectScore subjectScore = findSubjectScore(scoreSubject);
        System.out.println("[회차, 등급]");
        subjectScore.getWholeGrade();
    }

    // 원하는 과목 인스턴스를 리턴하기(없으면 생성)
    public SubjectScore findSubjectScore(int scoreSubject) { // student 클래스에서, 여기서 입력받은 과목이 수강생이 가진 과목과 일치하지 않는 경우를 확인해주는 메소드 필요 !!

        // 원하는 과목이 subjectScoreSet 안에 들어가있나 확인
        for (SubjectScore subjectScore : subjectScores) {
            if (subjectScore.checkSubjectScoreSubject(scoreSubject)) {
                return subjectScore;
            }
        }
        // 없는 경우에는 새로 생성해주기
        SubjectScore subjectScore = new SubjectScore(scoreSubject);
        subjectScores.add(subjectScore);
        return subjectScore;
    }


}






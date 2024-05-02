public class Score {

    /*필드*/

    //과목고유번호
    private final int subjectNum;
    //수강생고유번호 (중복불가조건 -> final? // )
    private final int studentNum;
    //회차번호
    private int roundNum; //roundNum 말고 Score 자체를 Studen클래스의 배열로 만들어서 저장하면 편하지 않을까?
    //점수
    private int examScore;
    //등급
    private char grade;

    /*생성자*/
    public Score(int subjectNum, int studentNum, int roundNum, int examScore){
        this.subjectNum = subjectNum;
        this.studentNum = studentNum;
        this.roundNum = roundNum;
        this.examScore = examScore;

        //switch로, static으로 올라가있는 subjectNumList 속 subjectNum이랑 입력값의  if문으로 점수 나누기
        //필수과목인 경우
        if ( 95 <= this.examScore && this.examScore <= 100) {
            this.grade = 'A';
        } else if (90 <= this.examScore && this.examScore <= 94) {

            this.grade = 'B';
            // ...
        } else { // 100 초과 및 음수
            System.out.println("범위(0~100)에 벗어난 점수가 입력되어씁니다."); //예외처리 던지기
        }

    }

    /*메서드*/

    //get,set 점수 외에 다른 것-> 굳이?

    // get 점수
    public int getExamScore(Score score){
        return this.examScore;
    }

    // set 점수
    public void getExamScore(){
        System.out.println(this.examScore);
    }



}

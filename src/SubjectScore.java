import java.util.Map;

public class SubjectScore {

    /* 필드 */
    private int subjectScoreSubject; // 해당 subject를 담는 필드
    private int[] subjectScoreStore; // 해당 subject의 score을 담는 배열
    private char[] subjectGradeStore; // 해당 subject의 Grade를 담는 배열

    /* 생성자 */
    SubjectScore(int subjectScoreSubject) {
        //초기화
        this.subjectScoreSubject = subjectScoreSubject;
        this.subjectScoreStore = new int[10];
        this.subjectGradeStore = new char[10];
    }
<<<<<<< HEAD
=======

>>>>>>> 4f7af909d7461ef1c4e74043dce6e5063da93472
    /* 메서드 */
    // 점수 등록하는 메서드
    public void setSubjectScore(int examScore, int round) {
        if (this.isGradeEmpty(round)){
            System.out.println("현재 해당하는 과목의 회차 점수가 등록되어있습니다. 회차 점수 수정을 이용해주시기 바랍니다.");
            //throw해주고 score에서 trycatch?
            return;
        }
        subjectScoreStore[round-1] = examScore;
        this.setGradeStore(examScore, round); //등급도 지정
    }

    // 등급 등록하는 메서드
    public void setGradeStore(int examScore, int round) {
        Map<String, Integer> gradeCutMap; // 등급컷 맵 선언
        round -= 1;

        if ( this.subjectScoreSubject <= 4 ){ // (필수과목 기준) static변수 ?
            gradeCutMap = Map.of("aHigh", 100, "aLowBHigh", 95, "bLowCHigh", 90,
                    "cLowDHigh", 80,  "dLowFHigh", 70,  "fLowNHigh", 60, "nLow", 0);
        } else { // 아닌 경우
            gradeCutMap = Map.of("aHigh", 100, "aLowBHigh", 90, "bLowCHigh", 80,
                    "cLowDHigh", 70,  "dLowFHigh", 60,  "fLowNHigh", 50, "nLow", 0);
        }

        if (gradeCutMap.get("aLowBHigh") <= examScore && examScore <= gradeCutMap.get("aHigh")) {
            this.subjectGradeStore[round] = 'A';
        } else if (gradeCutMap.get("bLowCHigh") <= examScore && examScore < gradeCutMap.get("aLowBHigh")) {
            this.subjectGradeStore[round] = 'B';
        } else if (gradeCutMap.get("cLowDHigh") <= examScore && examScore < gradeCutMap.get("bLowCHigh")) {
            this.subjectGradeStore[round] = 'C';
        } else if (gradeCutMap.get("dLowFHigh") <= examScore && examScore < gradeCutMap.get("cLowDHigh")) {
            this.subjectGradeStore[round] = 'D';
        } else if (gradeCutMap.get("fLowNHigh") <= examScore && examScore < gradeCutMap.get("dLowFHigh")) {
            this.subjectGradeStore[round] = 'F';
        } else if (gradeCutMap.get("nLow") <= examScore && examScore < gradeCutMap.get("fLowNHigh")) {
            this.subjectGradeStore[round] = 'N';
        } else { // 100 초과 및 음수
            System.out.println("범위(0~100)를 벗어난 점수가 입력되었습니다."); //예외처리 던지기
        }
    }

    // 점수 수정하는 메서드
    public void modifySubjectScore(int examScore, int round){
        if (!this.isGradeEmpty(round)){
            System.out.println("현재 해당하는 과목의 회차 점수가 등록되어 있지 않습니다. 회자 점수 등록을 이용해주시기 바랍니다.");
            return;
        }
        subjectScoreStore[round-1] = examScore;
        this.setGradeStore(examScore, round); // 등급 지정
    }

    // 등록된 회차와 등급을 출력하는 메서드
    public void getWholeGrade(){
        for (int i = 0; i < subjectGradeStore.length; i++){
            if (subjectGradeStore[i] != 0){
                System.out.println(i+1 + "회차, " + subjectGradeStore[i] + "등급");
            }
        }
    }

    // 입력받은 과목과 해당 인스턴스의 과목이 일치하는지 체크하는 메서드
    boolean checkSubjectScoreSubject(int subjectScoreSubject) {
        if (this.subjectScoreSubject == subjectScoreSubject) { // 일치하는 경우
            return true;
        }
        return false;
    }

    // 등급이 이미 매겨졌는지 isEmpty check하는 메서드
    boolean isGradeEmpty(int round) {
        // 등급이 없으면 점수도 없다는 뜻
        if (subjectGradeStore[round-1] == 0) { // 없는 경우
            return false;
        }
        return true; // 있는 경우
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> 4f7af909d7461ef1c4e74043dce6e5063da93472

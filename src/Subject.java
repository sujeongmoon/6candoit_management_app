public class Subject {
    private String subjectId;
    private String subjectName;
    private String subjectType;

    public Subject(String subjectName, String subjectType) {

        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    // Getter
    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }


}

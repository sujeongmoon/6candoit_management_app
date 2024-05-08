package javateamproject.model;

import javateamproject.type.SubjectType;

public class Subject {
    private String subjectId;
    private String subjectName;
    private SubjectType subjectType;

    public Subject(String subjectId, String subjectName, SubjectType subjectType) {
        this.subjectId = subjectId;
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

    public SubjectType getSubjectType() {
        return subjectType;
    }

}

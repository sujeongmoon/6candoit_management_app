package javateamproject.type;

public enum ClassType {
    STUDENT("ST"),
    SCORE("SC"),
    SUBJECT("SJ");
    private final String idxName;
    ClassType(String idxName) {
        this.idxName = idxName;
    }
    public String getIdxName(){
        return idxName;
    }

}

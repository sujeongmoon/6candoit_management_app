public enum classType {
    STUDENT("ST"),
    SCORE("SC"),
    SUBJECT("SJ");
    private final String idxName;
    classType(String idxName) {
        this.idxName = idxName;
    }
    public String getIdxName(){
        return idxName;
    }

}

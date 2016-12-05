package bean.domain;

/**
 * Created by GR on 2016/12/4.
 */
public class StudentBean {
    private Integer id = null;
    private String studentId = null;
    private String name = null;
    private Integer grade = null;  //?
    private Integer isOnProject = null;    //什么值对应什么意思??
    private Integer isNeedProject = null;  //同上
    private Integer finalScore = null;
    private String properties1 = null; //??这是什么?
    private String properties2 = null; //这又是什么??
    private Integer worked = null;  //什么默认值?
    private Integer isPunished = null; //什么?
    private String graduatedSchool = null;
    private String tecKeyWord = null;  //这又是什么
    private String homePageUrl = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getIsOnProject() {
        return isOnProject;
    }

    public void setIsOnProject(Integer isOnProject) {
        this.isOnProject = isOnProject;
    }

    public Integer getIsNeedProject() {
        return isNeedProject;
    }

    public void setIsNeedProject(Integer isNeedProject) {
        this.isNeedProject = isNeedProject;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }

    public String getProperties1() {
        return properties1;
    }

    public void setProperties1(String properties1) {
        this.properties1 = properties1;
    }

    public String getProperties2() {
        return properties2;
    }

    public void setProperties2(String properties2) {
        this.properties2 = properties2;
    }

    public Integer getWorked() {
        return worked;
    }

    public void setWorked(Integer worked) {
        this.worked = worked;
    }

    public Integer getIsPunished() {
        return isPunished;
    }

    public void setIsPunished(Integer isPunished) {
        this.isPunished = isPunished;
    }

    public String getGraduatedSchool() {
        return graduatedSchool;
    }

    public void setGraduatedSchool(String graduatedSchool) {
        this.graduatedSchool = graduatedSchool;
    }

    public String getTecKeyWord() {
        return tecKeyWord;
    }

    public void setTecKeyWord(String tecKeyWord) {
        this.tecKeyWord = tecKeyWord;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public void setHomePageUrl(String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }


}

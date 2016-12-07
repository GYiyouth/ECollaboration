package bean.domain;

/**
 * Created by GR on 2016/12/4.
 */
public class StudentBean {
    private Integer id  ;
    private String grade  ;
    private Integer isOnProject ;  
    private Integer isNeedProject ;  
    private String graduatedSchool;
    private String tecKeyWord  ;
    private String homePageUrl  ;
    private Integer codeScore1;
    private Integer codeScore2;
    private Integer presentationScore;
    private Integer finalScore  ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
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

    public Integer getCodeScore1() {
        return codeScore1;
    }

    public void setCodeScore1(Integer codeScore1) {
        this.codeScore1 = codeScore1;
    }

    public Integer getCodeScore2() {
        return codeScore2;
    }

    public void setCodeScore2(Integer codeScore2) {
        this.codeScore2 = codeScore2;
    }

    public Integer getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(Integer presentationScore) {
        this.presentationScore = presentationScore;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }
}

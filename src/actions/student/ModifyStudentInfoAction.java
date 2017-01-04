package actions.student;

import DAO.studentDAO.StudentDAO;
import DAO.studentDAO.StudentDaoImpl;
import bean.domain.StudentBean;
import bean.domain.UserBean;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Map;

/**
 * 修改学生的个人资料
 * Created by GR on 2017/1/4.
 */
public class ModifyStudentInfoAction implements ServletRequestAware, ServletResponseAware, SessionAware{
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> session;

    private String name;
    private Integer sex;        //1:male
    private String email;
    private String phoneNumber;
    private String homePageUrl;
    private  String graduatedSchool;
    private String tecKeyWord;


    private String photo;


    private Integer id;
    private String schoolId;
    private Integer role;       //1:manager 2:teacher 3:student
    private String logName;     //默认：email
    private String passWord;
    private String createDate;  //生成
    private String lastLogTime; //生成
    private String activeBefore;//生成
    private Integer newsFlag;   //生成
    private Integer codeScore1;
    private Integer codeScore2;
    private Integer presentationScore;
    private Integer finalScore;
    private Integer isOnProject;
    private Integer isNeedProject;

    private StudentBean studentBean;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
        this.response.setCharacterEncoding("UTF-8");
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String modifyStudentInfo(){
        StudentBean studentBean = new StudentBean();
        StudentDAO studentDAO = new StudentDaoImpl();
        UserBean userBean = (UserBean)session.get("userBean");
        studentBean.setId(userBean.getId());
        studentBean.setName(name);
        studentBean.setSex(sex);
        studentBean.setEmail(email);
        studentBean.setPhoneNumber(phoneNumber);
        studentBean.setHomePageUrl(homePageUrl);
        studentBean.setGraduatedSchool(graduatedSchool);
        studentBean.setTecKeyWord(tecKeyWord);
        try {
            if(studentDAO.updateInfoByStudent(studentBean)) {
                System.out.println("?");
                studentBean = studentDAO.getInfoById(studentBean.getId());
                setStudentBean(studentBean);
                return "success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
        return "fail";
    }

    public void appModifyStudentInfo() throws Exception{

        JSONObject jsonObject = new JSONObject();
        if(modifyStudentInfo().equals("success")){
            jsonObject.put("studentBean", getStudentBean());
            jsonObject.put("result", "success");

            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().write(jsonObject.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }else {
            jsonObject.put("result", "fail");

            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().write(jsonObject.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public void setHomePageUrl(String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastLogTime() {
        return lastLogTime;
    }

    public void setLastLogTime(String lastLogTime) {
        this.lastLogTime = lastLogTime;
    }

    public String getActiveBefore() {
        return activeBefore;
    }

    public void setActiveBefore(String activeBefore) {
        this.activeBefore = activeBefore;
    }

    public Integer getNewsFlag() {
        return newsFlag;
    }

    public void setNewsFlag(Integer newsFlag) {
        this.newsFlag = newsFlag;
    }

    public StudentBean getStudentBean() {
        return studentBean;
    }

    public void setStudentBean(StudentBean studentBean) {
        this.studentBean = studentBean;
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

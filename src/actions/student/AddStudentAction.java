package actions.student;

import DAO.studentDAO.StudentDAO;
import DAO.studentDAO.StudentDaoImpl;
import bean.domain.StudentBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GR on 2016/12/22.
 */
public class AddStudentAction implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private Integer id;
    private String schoolId;
    private String name;
    private Integer sex;        //1:male
    private Integer role;       //1:manager 2:teacher 3:student
    private String email;
    private String phoneNumber;
    private String logName;     //默认：schoolId
    private String passWord;
    private String createDate;  //生成
    private String photo;
    private String lastLogTime; //生成
    private String activeBefore;//生成
    private Integer newsFlag;   //生成

    private Integer grade;
    private Integer isOnProject;
    private Integer isNeedProject;
    private String graduatedSchool;
    private String tecKeyWord;
    private String homePageUrl;
    private Integer codeScore1;
    private Integer codeScore2;
    private Integer presentationScore;
    private Integer finalScore;

    private StudentBean studentBean;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public StudentBean getStudentBean() {
        return studentBean;
    }

    public void setStudentBean(StudentBean studentBean) {
        this.studentBean = studentBean;
    }

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

    public String addStudent(){
        StudentBean studentBean = new StudentBean();
        StudentDAO studentDAO = new StudentDaoImpl();

        studentBean.setSchoolId(schoolId);
        studentBean.setName(name);
        studentBean.setSex(sex);
        studentBean.setRole(role);
        studentBean.setEmail(email);
        studentBean.setPhoneNumber(phoneNumber);
        studentBean.setLogName(email); //如果登录不等于邮箱，修改这个地方
        studentBean.setPassWord(passWord);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        createDate = sdf.format(new Date());
        studentBean.setCreateDate(createDate);
        studentBean.setPhoto("test");
        studentBean.setLastLogTime(createDate);
        studentBean.setActiveBefore(createDate);
        studentBean.setNewFlag(0);
        studentBean.setGrade(grade);
        studentBean.setIsOnProject(isOnProject);
        studentBean.setIsNeedProject(isNeedProject);
        studentBean.setGraduatedSchool(graduatedSchool);
        studentBean.setTecKeyWord(tecKeyWord);
        studentBean.setHomePageUrl(homePageUrl);
//        studentBean.setCodeScore1(null);
//        studentBean.setCodeScore2(null);
//        studentBean.setPresentationScore(null);
//        studentBean.setFinalScore(null);
        try {
            if(studentDAO.addStudent(studentBean)) {
                this.setStudentBean(studentBean);
                return "success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
        return "fail";
    }


    public void appAddStudent() throws Exception{
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        if (addStudent().equals("success")){

            jsonObject.put("studentBean", getStudentBean());
            jsonObject.put("result", "success");
            jsonArray.add(jsonObject);
            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().write(jsonArray.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }else {
            jsonObject.put("result", "fail");
            jsonArray.add(jsonObject);
            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().write(jsonArray.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }
    }
}

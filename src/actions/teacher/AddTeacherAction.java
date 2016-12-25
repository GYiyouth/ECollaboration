package actions.teacher;

import DAO.teacherDAO.TeacherDAO;
import DAO.teacherDAO.TeacherDAOImpl;
import bean.domain.TeacherBean;
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
public class AddTeacherAction implements ServletRequestAware, ServletResponseAware {
    private HttpServletRequest request;
    private HttpServletResponse response;

    private Integer id;
    private String schoolId;
    private String name;
    private Integer sex;        //1:male
    private Integer role;       //1:manager 2:teacher 3:student
    private String email;
    private String phoneNumber;
    private String logName;     //默认：email
    private String passWord;
    private String createDate;  //生成
    private String photo;
    private String lastLogTime; //生成
    private String activeBefore;//生成
    private Integer newsFlag;   //生成

    private String homePageUrl;
    private Integer needStudentsFlag;

    private TeacherBean teacherBean;

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

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public void setHomePageUrl(String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    public Integer getNeedStudentsFlag() {
        return needStudentsFlag;
    }

    public void setNeedStudentsFlag(Integer needStudentsFlag) {
        this.needStudentsFlag = needStudentsFlag;
    }

    public TeacherBean getTeacherBean() {
        return teacherBean;
    }

    public void setTeacherBean(TeacherBean teacherBean) {
        this.teacherBean = teacherBean;
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


    public String addTeacher(){
        TeacherBean teacherBean = new TeacherBean();
        TeacherDAO teacherDAO = new TeacherDAOImpl();

        teacherBean.setSchoolId(schoolId);
        System.out.println(teacherBean.getSchoolId()+"???????????");
        teacherBean.setName(name);
        teacherBean.setSex(sex);
        teacherBean.setRole(role);
        teacherBean.setEmail(email);
        teacherBean.setPhoneNumber(phoneNumber);
        teacherBean.setLogName(email); //如果登录不等于邮箱，修改这个地方
        teacherBean.setPassWord(passWord);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        createDate = sdf.format(new Date());
        teacherBean.setCreateDate(createDate);
        teacherBean.setPhoto("test");
        teacherBean.setLastLogTime(createDate);
        teacherBean.setActiveBefore(createDate);
        teacherBean.setNewFlag(0);
        teacherBean.setHomePageUrl(homePageUrl);
        teacherBean.setNeedStudentsFlag(needStudentsFlag);
        try {
            if(teacherDAO.addTeacher(teacherBean)) {
                this.setTeacherBean(teacherBean);
                return "success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
        return "fail";
    }


    public void appAddTeacher() throws Exception{
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        if (addTeacher().equals("success")){

            jsonObject.put("teacherBean", getTeacherBean());
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

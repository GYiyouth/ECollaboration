package actions.manager;

import DAO.managerDAO.ManagerDAO;
import DAO.managerDAO.ManagerDAOImpl;
import bean.domain.ManagerBean;
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
public class AddManagerAction implements ServletRequestAware, ServletResponseAware {

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

    private Integer mRole;

    private ManagerBean managerBean;

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

    public Integer getmRole() {
        return mRole;
    }

    public void setmRole(Integer mRole) {
        this.mRole = mRole;
    }

    public ManagerBean getManagerBean() {
        return managerBean;
    }

    public void setManagerBean(ManagerBean managerBean) {
        this.managerBean = managerBean;
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

    public String addManager(){
        ManagerBean managerBean = new ManagerBean();
        ManagerDAO managerDAO = new ManagerDAOImpl();

        managerBean.setSchoolId(schoolId);
        managerBean.setName(name);
        managerBean.setSex(sex);
        managerBean.setRole(role);
        managerBean.setEmail(email);
        managerBean.setPhoneNumber(phoneNumber);
        managerBean.setLogName(logName); //如果登录不等于邮箱，修改这个地方
        managerBean.setPassWord(passWord);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        createDate = sdf.format(new Date());
        managerBean.setCreateDate(createDate);
        managerBean.setPhoto("test");
        managerBean.setLastLogTime(createDate);
        managerBean.setActiveBefore(createDate);
        managerBean.setNewFlag(0);
        managerBean.setmRole(mRole);
//        managerBean.setCodeScore1(null);
//        managerBean.setCodeScore2(null);
//        managerBean.setPresentationScore(null);
//        managerBean.setFinalScore(null);
        try {
            if(managerDAO.addManager(managerBean)) {
                this.setManagerBean(managerBean);
                return "success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
        return "fail";
    }


    public void appAddManager() throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (addManager().equals("success")){

            jsonObject.put("managerBean", getManagerBean());
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
}

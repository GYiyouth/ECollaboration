package actions.project;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import bean.domain.ProjectBean;
import bean.domain.UserBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 创建项目
 * Created by GR on 2016/12/24.
 */
public class CreateProjectAction implements SessionAware, ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> session;

    //jsp提交
    private String name;
    private String applyBeforeDate;     //申请项目截止时间
    private String finishDate;          //项目截止时间
    private String survivalDate;        //项目有效期？
    private Integer teamMax;
    private Integer memberMax;          //每组最多多少人
    private String keyWord;
    private String info;
    private String requirement;
    private String gain;

    //非jsp提交
    private String createDate;
    private Integer teamNumber;
    private Integer grade;              //年份
    private Integer priority;           //区分是那种项目 0：工程实践 1：个人兴趣 2比赛
    private Integer status;             //状态   0：失效 1：申请 2：执行 3：完成
    private Integer creatorId;
    private Integer teacherId;

    private ProjectBean projectBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplyBeforeDate() {
        return applyBeforeDate;
    }

    public void setApplyBeforeDate(String applyBeforeDate) {
        this.applyBeforeDate = applyBeforeDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getSurvivalDate() {
        return survivalDate;
    }

    public void setSurvivalDate(String survivalDate) {
        this.survivalDate = survivalDate;
    }

    public Integer getTeamMax() {
        return teamMax;
    }

    public void setTeamMax(Integer teamMax) {
        this.teamMax = teamMax;
    }

    public Integer getMemberMax() {
        return memberMax;
    }

    public void setMemberMax(Integer memberMax) {
        this.memberMax = memberMax;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getGain() {
        return gain;
    }

    public void setGain(String gain) {
        this.gain = gain;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public ProjectBean getProjectBean() {
        return projectBean;
    }

    public void setProjectBean(ProjectBean projectBean) {
        this.projectBean = projectBean;
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

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String createProject(){
        ProjectDAO projectDAO = new ProjectDAOImpl();
        ProjectBean projectBean = new ProjectBean();
        projectBean.setName(name);
        projectBean.setApplyBeforeDate(applyBeforeDate);
        projectBean.setFinishDate(finishDate);
        projectBean.setSurvivalDate(survivalDate);
        projectBean.setTeamNumber(0);
        projectBean.setTeamMax(teamMax);
        projectBean.setMemberMax(memberMax);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createDate = sdf.format(new Date());
        projectBean.setCreateDate(createDate);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        grade = Integer.parseInt(sdf2.format(new Date()));
        projectBean.setGrade(grade);
        projectBean.setKeyWord(keyWord);
        projectBean.setInfo(info);
        projectBean.setRequirement(requirement);
        projectBean.setGain(gain);
        projectBean.setPriority(priority);
        projectBean.setStatus(status);
//        UserBean u = (UserBean)session.get("userBean");
//        projectBean.setCreatorId(u.getId());
        projectBean.setTeacherId(teacherId);
        try{
            if(projectDAO.addProject(projectBean))
                return "success";
            else
                return "fail";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 成功：返回result=success
     *        返回（ProjectBean）projectBean
     * @throws Exception
     */
    public void appCreateProject() throws Exception{
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        if (createProject().equals("success")){

            jsonObject.put("projectBean", getProjectBean());
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

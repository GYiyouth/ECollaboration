package actions.project;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import bean.domain.ProjectBean;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 根据projectId 获取项目信息
 * 参数：projectId
 * return: projectBean
 * Created by GR on 2017/1/5.
 */
public class GetProjectInfoByProjectIdAction implements SessionAware, ServletRequestAware, ServletResponseAware {
    private Map session;
    private Integer projectId;
    private ProjectBean projectBean;

    private HttpServletRequest request;
    private HttpServletResponse response;

    private Integer id ;
    private String name ;

    private String applyBeforeDate ;
    private String finishDate ;
    private String survivalDate ;
    private Integer teamNumber ;
    private Integer teamMax ;
    private Integer memberMax ;
    private String createDate ;
    private Integer grade ;
    private String keyWord ;
    private String info ;
    private String requirement ;
    private String gain ;
    private Integer priority ;
    private Integer status ;
    private Integer creatorId ;
    private Integer teacherId ;

    public String getProjectInfoByProjectId(){
        ProjectBean projectBean = new ProjectBean();
        ProjectDAO projectDAO = new ProjectDAOImpl();
        System.out.println("得到的projectId是" + projectId);
        try {
            projectBean = projectDAO.getProjectInfo(projectId);
            if(projectBean == null)
                return "fail";
            else {
                setProjectBean(projectBean);
                int role = (int)session.get("role");
                switch (role){
                    case 1:
                    case 2:return "teacher";
                    case 3:return "student";
                }
                return "fail";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    public void appGetProjectInfoByProjectId() throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (getProjectInfoByProjectId().equals("success")){

            jsonObject.put("projectBean", getProjectBean());
            jsonObject.put("result", "success");
            this.response.getWriter().write(jsonObject.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }else {
            jsonObject.put("result", "fail");
            this.response.getWriter().write(jsonObject.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public ProjectBean getProjectBean() {
        return projectBean;
    }

    public void setProjectBean(ProjectBean projectBean) {
        this.projectBean = projectBean;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            this.request = request;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
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
}

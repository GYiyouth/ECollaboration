package actions.project;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.studentDAO.StudentDAO;
import DAO.studentDAO.StudentDaoImpl;
import DAO.teamDAO.TeamDAO;
import DAO.teamDAO.TeamDAOImpl;
import DAO.team_project.TeamProjectDAOImpl;
import DAO.team_project.Team_ProjectDAO;
import bean.domain.ProjectBean;
import bean.domain.TeamBean;
import bean.domain.UserBean;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by GR on 2017/1/5.
 */
public class GetMyJoinProjectAction implements SessionAware, ServletRequestAware, ServletResponseAware {
    private Map<String,Object> session;
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

    private ArrayList<ProjectBean> projectBeans;

    public String getMyJoinProjectByStudent(){
        try{
            ArrayList<ProjectBean> projectBeans = new ArrayList<>();
            UserBean userBean = (UserBean)session.get("userBean");
            StudentDAO studentDAO = new StudentDaoImpl();
            ProjectDAO projectDAO = new ProjectDAOImpl();
            TeamDAO teamDAO = new TeamDAOImpl();
            ArrayList<Integer> teamIds = teamDAO.getTeamIdListByStudentId(userBean.getId());
            if(teamIds == null){
                System.out.println("fail1");
                return "fail";
            }else{
                for(int i=0;i<teamIds.size();i++){
                    ArrayList<Integer> projectIds = projectDAO.getProjectIdListByTeamId(teamIds.get(i));
                    if(projectIds == null){
                        System.out.println("fail2");
                        return "fail";
                    }else{
                        for(int j=0;j<projectIds.size();j++){
                            ProjectBean projectBean = projectDAO.getProjectInfo(projectIds.get(j));
                            projectBeans.add(projectBean);
                        }
                    }
                }
                setProjectBeans(projectBeans);
            }
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    public void appGetMyJoinProjectByStudent() throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (getMyJoinProjectByStudent().equals("success")){

            jsonObject.put("projectBeans", getProjectBeans());
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

    public String getMyJoinProjectByTeacher(){
        try{
            ArrayList<ProjectBean> projectBeans = new ArrayList<>();
            UserBean userBean = (UserBean)session.get("userBean");
            ProjectDAO projectDAO = new ProjectDAOImpl();
            ArrayList<Integer> projectIds = projectDAO.getProjectIdListByTeacherId(userBean.getId());
            if(projectIds == null){
                System.out.println("fail1");
                return "fail";
            }else{
                for(int i=0;i<projectIds.size();i++){
                    ProjectBean projectBean = projectDAO.getProjectInfo(projectIds.get(i));
                    projectBeans.add(projectBean);
                }
                setProjectBeans(projectBeans);
            }
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    public void appGetMyJoinProjectByTeacher() throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (getMyJoinProjectByTeacher().equals("success")){

            jsonObject.put("projectBeans", getProjectBeans());
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

    public ArrayList<ProjectBean> getProjectBeans() {
        return projectBeans;
    }

    public void setProjectBeans(ArrayList<ProjectBean> projectBeans) {
        this.projectBeans = projectBeans;
    }
}

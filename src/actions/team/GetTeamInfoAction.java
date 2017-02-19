package actions.team;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.studentDAO.StudentDAO;
import DAO.studentDAO.StudentDaoImpl;
import DAO.teamDAO.TeamDAO;
import DAO.teamDAO.TeamDAOImpl;
import bean.domain.ProjectBean;
import bean.domain.StudentBean;
import bean.domain.TeamBean;
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
 * 通过teamId获取team信息
 * 返回team信息：teamBean
 * app端未传所有参数
 * Created by GR on 2017/1/3.
 */
public class GetTeamInfoAction implements ServletRequestAware, ServletResponseAware,SessionAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> session;

    //jsp获取
    private int teamId;

    //action中生成
    private TeamBean teamBean;
    ArrayList<StudentBean> studentBeans;
    ArrayList<ProjectBean> projectBeans;

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

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getTeamInfoByTeamId(){
        ArrayList<StudentBean> studentBeans = new ArrayList<>();
        TeamDAO teamDao = new TeamDAOImpl();
        StudentDAO studentDao = new StudentDaoImpl();
        ProjectDAO projectDAO = new ProjectDAOImpl();
        TeamBean teamBean = new TeamBean();
        try{
            teamBean = teamDao.getTeamInfo(teamId);
            System.out.println(teamBean.getTeamName()+"团队名字<GetTeamInfoAction:getTeamInfoByTeamId>");
            setTeamBean(teamBean);
            ArrayList<Integer> studentIds = studentDao.getStudentIdByTeamId(teamId);
            for(int i=0;i<studentIds.size();i++){
                StudentBean studentBean = studentDao.getInfoById(studentIds.get(i));
                studentBeans.add(studentBean);
            }
            setStudentBeans(studentBeans);
            ArrayList<ProjectBean> projectBeans = new ArrayList<>();
            ArrayList<Integer> projectIds = projectDAO.getProjectIdListByTeamId(teamId);
            for(int i=0;i<projectIds.size();i++){
                ProjectBean projectBean = projectDAO.getProjectInfo(projectIds.get(i));
                projectBeans.add(projectBean);
            }
            setProjectBeans(projectBeans);
        }catch(Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    public void appGetTeamInfoByTeamId() throws Exception{
        JSONObject jsonObject = new JSONObject();
        if(getTeamInfoByTeamId().equals("success")){
            jsonObject.put("teamBean", getTeamBean());
            jsonObject.put("studentBeans", getStudentBeans());
            jsonObject.put("projectBeans", getProjectBeans());
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


    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public TeamBean getTeamBean() {
        return teamBean;
    }

    public void setTeamBean(TeamBean teamBean) {
        this.teamBean = teamBean;
    }

    public ArrayList<StudentBean> getStudentBeans() {
        return studentBeans;
    }

    public void setStudentBeans(ArrayList<StudentBean> studentBeans) {
        this.studentBeans = studentBeans;
    }

    public ArrayList<ProjectBean> getProjectBeans() {
        return projectBeans;
    }

    public void setProjectBeans(ArrayList<ProjectBean> projectBeans) {
        this.projectBeans = projectBeans;
    }
}

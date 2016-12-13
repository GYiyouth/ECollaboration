package actions.team;

import DAO.studentDAO.StudentDaoImpl;
import DAO.teamDAO.TeamDAOImpl;
import bean.domain.TeamBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GR on 2016/12/13.
 */
public class JoinTeamAction implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Integer teamId;
    private Integer studentId;


    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String joinTeam() throws Exception{
        int teamId =  Integer.parseInt(request.getParameter("teamId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));    //不知道这个id到底叫什么名字
        StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
        boolean flag = studentDaoImpl.addStudentToTeam(studentId, teamId, 0);    //0代表组员
        if(flag)
            return "success";
        else
            return "fail";

    }

    public String appJoinTeam() throws Exception{
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        System.out.println(joinTeam()+"?");
        return null;
    }

}

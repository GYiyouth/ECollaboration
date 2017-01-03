package actions.team;

import DAO.studentDAO.StudentDAO;
import DAO.studentDAO.StudentDaoImpl;
import DAO.teamDAO.TeamDAO;
import DAO.teamDAO.TeamDAOImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * 组长处理别人加入团队的请求
 * 参数：要加入团队学生id，团队id
 * Created by GR on 2016/12/25.
 * ok
 */
public class DealJoinApplyAction implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private int teamId;
    private int studentId;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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


    //接受申请
    public String acceptJoinApply() throws Exception{
        TeamDAO teamDAO = new TeamDAOImpl();
        int leaderFlag = 2;     //设为组员
        if(teamDAO.acceptApplyJoinByTeamIdStudentId(teamId,studentId,leaderFlag))
            return "success";
        else
            return "false";
    }

    public void appAcceptJoinApply() throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (acceptJoinApply().equals("success")){

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

    //拒绝申请
    public String refuseJoinApply() throws Exception{
        StudentDAO studentDAO = new StudentDaoImpl();
        if(studentDAO.deleteStudentFormTeam(teamId,studentId))
            return "success";
        else
            return "false";
    }

    public void appRefuseJoinApply() throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (refuseJoinApply().equals("success")){

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
}

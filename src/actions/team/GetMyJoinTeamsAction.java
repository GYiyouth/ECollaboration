package actions.team;

import DAO.teamDAO.TeamDAOImpl;
import bean.domain.TeamBean;
import bean.domain.UserBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * 获取我加入的所有团队
 * 参数：无
 * 返回：ArrayList<TeamBean> teamBeans;
 * Created by GR on 2016/12/14.
 */
public class GetMyJoinTeamsAction implements ServletRequestAware, ServletResponseAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> session;

//    private int studentId;

    private ArrayList<TeamBean> teamBeans;

//    public int getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(int studentId) {
//        this.studentId = studentId;
//    }

    public ArrayList<TeamBean> getTeamBeans() {
        return teamBeans;
    }

    public void setTeamBeans(ArrayList<TeamBean> teamBeans) {
        this.teamBeans = teamBeans;
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

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getMyJoinTeam() throws Exception{
        try {
            TeamDAOImpl teamDaoImpl = new TeamDAOImpl();
            UserBean userBean = (UserBean)session.get("userBean");
            int studentId = userBean.getId();
            ArrayList<Integer> teamIds = teamDaoImpl.getTeamIdListByStudentId(studentId);
            ArrayList<TeamBean> teams = new ArrayList<>();
            for(int i = 0 ;i<teamIds.size() ;i++){
                TeamBean teamBean = new TeamBean();
                teamBean = teamDaoImpl.getTeamInfo(teamIds.get(i));
                teams.add(teamBean);
            }
            setTeamBeans(teams);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 返回result： 成功：success  失败：fail
     * 成功返回团队信息：teamBean：
     * @throws Exception
     */
    public void appGetMyJoinTeam() throws Exception{
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        if(getMyJoinTeam().equals("success")){
            jsonObject.put("teamBeans", getTeamBeans());
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

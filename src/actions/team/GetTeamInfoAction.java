package actions.team;

import DAO.teamDAO.TeamDAOImpl;
import bean.domain.TeamBean;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 通过teamId获取team信息
 * 返回team信息：teamBean
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
        TeamDAOImpl teamDaoImpl = new TeamDAOImpl();
        TeamBean teamBean = new TeamBean();
        try{
            teamBean = teamDaoImpl.getTeamInfo(teamId);
            System.out.println(teamBean.getTeamName()+"团队名字<GetTeamInfoAction:getTeamInfoByTeamId>");
            setTeamBean(teamBean);
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
}

package actions.project;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.teamDAO.TeamDAO;
import DAO.teamDAO.TeamDAOImpl;
import DAO.team_project.TeamProjectDAOImpl;
import DAO.team_project.Team_ProjectDAO;
import bean.domain.ProjectBean;
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
 * 获取一个项目相关的信息的action
 * 需要projectId不可为空
 * 返回projectBean
 *    teamNum
 *    teamBeans
 *
 * Created by geyao on 2017/1/4.
 */
public class getProjectInfoAction implements SessionAware, ServletRequestAware, ServletResponseAware {
	private Map session;
	private Integer projectId;
	private ProjectBean projectBean;
	private Integer teamNum;
	private ArrayList<TeamBean> teamBeans;

	private HttpServletRequest request;
	private HttpServletResponse response;



	public String execute() throws Exception {
		ProjectDAO projectDAO = new ProjectDAOImpl();
		Team_ProjectDAO team_projectDAO = new TeamProjectDAOImpl();
		this.projectBean = projectDAO.getProjectInfo(projectId);
		teamBeans = team_projectDAO.getTeamBeanByProjectId(projectId);
		return "success";
	}

	public void appExecute() throws Exception {
		JSONObject jsonObject = new JSONObject();
		String result = execute();
		if (result.equals("success")){

			jsonObject.put("projectBean", projectBean);
			jsonObject.put("teamNum", teamNum);
			jsonObject.put("teamBeans", teamBeans);
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

	public Integer getTeamNum() {
		return teamNum;
	}

	public void setTeamNum(Integer teamNum) {
		this.teamNum = teamNum;
	}

	public ArrayList<TeamBean> getTeamBeans() {
		return teamBeans;
	}

	public void setTeamBeans(ArrayList<TeamBean> teamBeans) {
		this.teamBeans = teamBeans;
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
}

package actions.team;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.studentDAO.StudentDAO;
import DAO.studentDAO.StudentDaoImpl;
import DAO.teamDAO.TeamDAO;
import DAO.teamDAO.TeamDAOImpl;
import bean.domain.StudentBean;
import bean.domain.TeamBean;
import bean.domain.UserBean;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import smallTools.JSONHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/**
 * form里要有teamId, projectId
 * 会验证team，project是否合法。申请人必须是团队创始人
 * Created by geyao on 2016/12/24.
 */
public class ApplyProjectAction implements SessionAware, ServletResponseAware, ServletRequestAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;
	private Integer teamId;
	private Integer projectId;

	private StudentBean studentBean;

	public void execute() throws Exception {
		JSONObject jsonObject = new JSONObject();
		UserBean userBean = (UserBean) this.session.get("userBean");
		TeamDAO teamDAO = new TeamDAOImpl();
		ProjectDAO projectDAO = new ProjectDAOImpl();
		TeamBean teamBean = teamDAO.getTeamInfo(teamId);
		if (teamBean == null ||
				teamBean.getCreatorId() != userBean.getId() ||
				projectDAO.getProjectInfo(projectId) == null ){
			JSONHandler.sendJSON(jsonObject, response);
			return;
		}


		ArrayList<Integer> prosList = projectDAO.getProjectIdListByTeamId(teamId);
		ArrayList<Integer> prosList2 = projectDAO.checkSchoolProjectByIdList(prosList, 0);
		if (prosList2 == null || !prosList2.contains(projectId)){
			teamDAO.setTeamProject(teamId, projectId);
			jsonObject.put("success", "fail");
			JSONHandler.sendJSON(jsonObject, response);
			return;
		}else
			JSONHandler.sendJSON(jsonObject, response);
	}

	/**
	 * Sets the HTTP request object in implementing classes.
	 *
	 * @param request the HTTP request.
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		try {
			this.request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the HTTP response object in implementing classes.
	 *
	 * @param response the HTTP response.
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		this.response.setCharacterEncoding("UTF-8");
	}

	/**
	 * Sets the Map of session attributes in the implementing class.
	 *
	 * @param session a Map of HTTP session attribute name/value pairs.
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public StudentBean getStudentBean() {
		return studentBean;
	}

	public void setStudentBean(StudentBean studentBean) {
		this.studentBean = studentBean;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Map getSession() {
		return session;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
}

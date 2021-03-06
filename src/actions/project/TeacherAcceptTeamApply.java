package actions.project;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.teamDAO.TeamDAO;
import DAO.teamDAO.TeamDAOImpl;
import bean.domain.UserBean;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.SQLException;
import java.util.Map;

/**
 * 老师接受团队对于项目的申请
 * 在DAO里修改了team_project的applyFlag，以及project本身的teamNumber
 * form里要有teamId，projectId
 * Created by geyao on 2016/12/25.
 */
public class TeacherAcceptTeamApply implements SessionAware{
	private int teamId;
	private int projectId;
	private Map session;
	public String accept() throws Exception {
		UserBean userBean = (UserBean) session.get("userBean");
		TeamDAO teamDAO = new TeamDAOImpl();
		ProjectDAO projectDAO = new ProjectDAOImpl();
		if (teamDAO.getTeamInfo(teamId) == null)
			return "fail";
		if (projectDAO.getProjectInfo(projectId) == null)
			return "fail";
		if (teamDAO.getTeamIdListByProjectId(projectId).contains(teamId)){
			try {
				if (teamDAO.acceptTeamApplyToProject(teamId, projectId))
					return "success";
			}catch (SQLException e){
				e.printStackTrace();
				return "fail";
			}
		}
		return "fail";
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

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
}

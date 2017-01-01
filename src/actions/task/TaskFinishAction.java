package actions.task;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.team_project.TeamProjectDAOImpl;
import DAO.team_project.Team_ProjectDAO;
import bean.domain.UserBean;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * 任务完成，给予评价,包括修改评价也可以用这个
 * form里要有：access（百分制）必须有
 * projectId,必须有，teamId，必须有，taskId必须有
 * 未测试
 * Created by geyao on 2016/12/26.
 */
public class TaskFinishAction implements SessionAware{
	private Map session;
	private int projectId;
	private int teamId;
	private int taskId;
	private int access;

	public String setAccess(){

		Integer role = (Integer) session.get("role");
		if (role != null && role ==2 ){
			Team_ProjectDAO team_projectDAO = new TeamProjectDAOImpl();
			UserBean userBean = (UserBean) session.get("userBean");
			ProjectDAO projectDAO = new ProjectDAOImpl();
			try {
				ArrayList<Integer> projectIdListByTeacherId =  projectDAO.getProjectIdListByTeacherId(userBean.getId());
				if (projectIdListByTeacherId == null || !projectIdListByTeacherId.contains(projectId))
					return "fail";
				ArrayList<Integer> projectIdListByTeam = projectDAO.getProjectIdListByTeamId(teamId);
				if (projectIdListByTeam == null || !projectIdListByTeam.contains(projectId))
					return "fail";
				ArrayList<Integer> projectIdListByTask = projectDAO.getProjectIdListByTaskId(taskId);
				if (projectIdListByTask == null || !projectIdListByTask.contains(projectId))
					return "fail";
				int team_projectId = team_projectDAO.getIdByTeamIdProjectId(teamId, projectId);
				if (access <= 100 && access >=0) {
					team_projectDAO.addAccess(team_projectId, taskId, access);
					return "success";
				}
				else
					return "fail";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}
		return "fail";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}
}

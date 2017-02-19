package actions.task;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.team_project.TeamProjectDAOImpl;
import DAO.team_project.Team_ProjectDAO;
import bean.domain.UserBean;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import smallTools.JSONHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
public class TaskFinishAction implements SessionAware, ServletRequestAware, ServletResponseAware{
	private Map session;
	private int projectId;
	private int teamId;
	private int taskId;
	private int access;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public void setAccess() throws Exception {
		JSONObject jsonObject = new JSONObject();
		Integer role = (Integer) session.get("role");
		if (role != null && role ==2 ){
			Team_ProjectDAO team_projectDAO = new TeamProjectDAOImpl();
			UserBean userBean = (UserBean) session.get("userBean");
			ProjectDAO projectDAO = new ProjectDAOImpl();
			try {
				ArrayList<Integer> projectIdListByTeacherId =  projectDAO.getProjectIdListByTeacherId(userBean.getId());
				if (projectIdListByTeacherId == null || !projectIdListByTeacherId.contains(projectId)){
					JSONHandler.sendJSON(jsonObject, response);
					return;
				}

				ArrayList<Integer> projectIdListByTeam = projectDAO.getProjectIdListByTeamId(teamId);
				if (projectIdListByTeam == null || !projectIdListByTeam.contains(projectId)){
					JSONHandler.sendJSON(jsonObject, response);
					return;
				}
				ArrayList<Integer> projectIdListByTask = projectDAO.getProjectIdListByTaskId(taskId);
				if (projectIdListByTask == null || !projectIdListByTask.contains(projectId)){
					JSONHandler.sendJSON(jsonObject, response);
					return;
				}
				int team_projectId = team_projectDAO.getIdByTeamIdProjectId(teamId, projectId);
				if (access <= 100 && access >=0) {
					team_projectDAO.addAccess(team_projectId, taskId, access);
					jsonObject.put("result", "success");
					JSONHandler.sendJSON(jsonObject, response);
				}
				else{
					JSONHandler.sendJSON(jsonObject, response);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				JSONHandler.sendJSON(jsonObject, response);
				return;
			}
		}
		JSONHandler.sendJSON(jsonObject, response);
		return;
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

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		try {
			this.request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}

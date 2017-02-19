package actions.task;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.taskDAO.TaskDAO;
import DAO.taskDAO.TaskDAOImpl;
import DAO.teacherDAO.TeacherDAO;
import DAO.teacherDAO.TeacherDAOImpl;
import bean.domain.TaskBean;
import bean.domain.TeacherBean;
import bean.domain.UserBean;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import smallTools.JSONHandler;
import smallTools.Time;
import smallTools.TimeImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建任务，需要form里有——
 * projects数组，不可为空
 * title，不可为空，content，可为空，beginDate可为空，targetDate不可为空
 * Created by geyao on 2016/12/25.
 */
public class TaskCreationAction implements SessionAware, ServletRequestAware, ServletResponseAware {
	private Map session;
	private List<String> projects;
	private String title;
	private String content;
	private String beginDate;
	private String targetDate;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public void execute() throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (projects == null || projects.size() <1 ){
			JSONHandler.sendJSON(jsonObject, response);
			return;
		}

		TeacherDAO teacherDAO = new TeacherDAOImpl();
		ProjectDAO projectDAO = new ProjectDAOImpl();
		TaskDAO taskDAO = new TaskDAOImpl();
		UserBean userBean = (UserBean) session.get("userBean");
		TaskBean taskBean = taskBeanInit(userBean.getId());
		ArrayList<Integer> projects = new ArrayList<>();
		for (String str : this.projects){
			int i = Integer.parseInt(str);
			if (projectDAO.getProjectInfo(i) != null)
				projects.add(i);
			else{
				JSONHandler.sendJSON(jsonObject, response);
				return;
			}
		}
		if (projects.size() > 0) {
			taskDAO.addTask(taskBean, projects);
			jsonObject.put("result", "success");
			JSONHandler.sendJSON(jsonObject, response);
		}else{
			JSONHandler.sendJSON(jsonObject, response);
			return;
		}
	}

	private TaskBean taskBeanInit(int teacherId){
		TaskBean taskBean = new TaskBean();
		taskBean.setTitle(title);
		taskBean.setContent(content);
		taskBean.setCreatorId(teacherId);
		Time time = new TimeImpl();
		taskBean.setCreateDate(time.getTime());
		if (beginDate !=null && !beginDate.equals("")){
			taskBean.setBeginDate(beginDate);
		}else
			taskBean.setBeginDate(taskBean.getCreateDate());
		taskBean.setTargetDate(targetDate);
		return taskBean;
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

	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
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

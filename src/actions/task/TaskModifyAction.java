package actions.task;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.taskDAO.TaskDAO;
import DAO.taskDAO.TaskDAOImpl;
import DAO.teacherDAO.TeacherDAO;
import DAO.teacherDAO.TeacherDAOImpl;
import bean.domain.TaskBean;
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
 * 任务修改的action
 * 提交的信息必须包含新的实施projects数组
 * Created by geyao on 2016/12/25.
 */
public class TaskModifyAction implements SessionAware, ServletRequestAware, ServletResponseAware {
	private Map session;
	private List<String> projects;
	private String title;
	private String content;
	private String beginDate;
	private String targetDate;
	private int taskId;
	private HttpServletResponse response;
	private HttpServletRequest request;

	/**
	 * 修改任务，会判断提交来的projects数组是否正确，仅对数据库中存在的项目添加任务
	 * 返回 successProjectsId - 存在的项目ID 的ArrayList
	 *     failProjectsId - 不存在的项目ID的 ArrayList
	 *     taskBean - 服务器拿到并更新了的taskBean
	 * @throws Exception
	 */
	public void execute() throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (projects == null || projects.size() < 1){
			JSONHandler.sendJSON(jsonObject, response);
			return;
		}

		TeacherDAO teacherDAO = new TeacherDAOImpl();
		ProjectDAO projectDAO = new ProjectDAOImpl();
		TaskDAO taskDAO = new TaskDAOImpl();
		UserBean userBean = (UserBean) session.get("userBean");
		TaskBean taskBean = taskDAO.getTaskInfo(taskId);
		if (taskBean == null){
			JSONHandler.sendJSON(jsonObject, response);
			return;
		}
		if (title != null && !title.equals(""))
			taskBean.setTitle(title);
		if (content != null && !content.equals(""))
			taskBean.setContent(content);
		if (beginDate != null && !beginDate.equals(""))
			taskBean.setBeginDate(beginDate);
		if (targetDate != null && !targetDate.equals(""))
			taskBean.setTargetDate(targetDate);
		ArrayList<Integer> successProjects = new ArrayList<>();
		ArrayList<Integer> failProjects = new ArrayList<>();
		for (String str : this.projects) {
			int i = Integer.parseInt(str);
			if (projectDAO.getProjectInfo(i) != null)
				successProjects.add(i);
			else{
				failProjects.add(i);
			}
		}
		if (successProjects.size() > 0) {
			taskDAO.updateTask(taskBean);
			projectDAO.deleteTask_Project(taskId);
			for (int i : successProjects) {
				projectDAO.setProjectTask(i, taskId);
			}
			jsonObject.put("result", "success");
		} else{
			jsonObject.put("result", "fail");
		}
		jsonObject.put("successProjectsId", successProjects);
		jsonObject.put("failProjectsId", failProjects);
		jsonObject.put("taskBean", taskBean);
		JSONHandler.sendJSON(jsonObject, response);
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

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
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

package actions.task;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.taskDAO.TaskDAO;
import DAO.taskDAO.TaskDAOImpl;
import DAO.teacherDAO.TeacherDAO;
import DAO.teacherDAO.TeacherDAOImpl;
import bean.domain.TaskBean;
import bean.domain.UserBean;
import org.apache.struts2.interceptor.SessionAware;
import smallTools.Time;
import smallTools.TimeImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 任务修改的action
 * 提交的信息必须包含新的实施project数组
 * Created by geyao on 2016/12/25.
 */
public class TaskModifyAction implements SessionAware {
	private Map session;
	private List<String> projects;
	private String title;
	private String content;
	private String beginDate;
	private String targetDate;
	private int taskId;

	public String execute() throws Exception {
		if (projects == null || projects.size() < 1)
			return "fail";
		TeacherDAO teacherDAO = new TeacherDAOImpl();
		ProjectDAO projectDAO = new ProjectDAOImpl();
		TaskDAO taskDAO = new TaskDAOImpl();
		UserBean userBean = (UserBean) session.get("userBean");
		TaskBean taskBean = taskDAO.getTaskInfo(taskId);
		if (taskBean == null)
			return "fail";
		if (title != null && !title.equals(""))
			taskBean.setTitle(title);
		if (content != null && !content.equals(""))
			taskBean.setContent(content);
		if (beginDate != null && !beginDate.equals(""))
			taskBean.setBeginDate(beginDate);
		if (targetDate != null && !targetDate.equals(""))
			taskBean.setTargetDate(targetDate);
		ArrayList<Integer> projects = new ArrayList<>();
		for (String str : this.projects) {
			int i = Integer.parseInt(str);
			if (projectDAO.getProjectInfo(i) != null)
				projects.add(i);
			else
				return "fail";
		}
		if (projects.size() > 0) {
			taskDAO.updateTask(taskBean);
			projectDAO.deleteTask_Project(taskId);
			for (int i : projects) {
				projectDAO.setProjectTask(i, taskId);
			}
			return "success";
		} else
			return "fail";
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
}

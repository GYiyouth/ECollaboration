package actions.logIn;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.teamDAO.TeamDAO;
import DAO.teamDAO.TeamDAOImpl;
import DAO.userDAO.UserDAO;
import DAO.userDAO.UserDAOImpl;
import bean.domain.ProjectBean;
import bean.domain.UserBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import smallTools.SessionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 登录action
 *
 * Created by geyao on 2016/12/13.
 */
public class LogInAction implements ServletRequestAware, ServletResponseAware, SessionAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String userName;
	private String passWord;
	private UserBean userBean;
	private Map session;



	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
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

	public String log() throws Exception {
		UserDAO userDAO = new UserDAOImpl();
		UserBean userBean;
		userBean = userDAO.getLogInfo(userName, passWord);
		if (userBean!=null) {
//			File file = userDAO.getUserPhoto(userBean);
//

			this.setUserBean(userBean);
			if (this.session.containsKey("userName"))
				this.session.remove("userName");
			this.session.put("userName", userName);
			if (this.session.containsKey("userId"))
				this.session.remove("userId");
			this.session.put("userId", userBean.getId());
			if (this.session.containsKey("role"))
				this.session.remove("role");
			//1管理员，2老师，3学生
			this.session.put("role", userBean.getRole());
			if (this.session.containsKey("userBean"))
				this.session.remove("userBean");
			this.session.put("userBean", userBean);
			System.out.println(session);
			switch (userBean.getRole()){
				case 1:{
					//管理员

				}break;
				case 2:{
					//老师
					return teacherLogIn(userBean.getId());
				}
				case 3:{
					//学生
					return studentLogIn(userBean.getId());
				}
				default:return "fail";
			}
			return "success";
		}else
			return "fail";
	}

	public String appLog() throws Exception{

		JSONObject jsonObject = new JSONObject();
		String result = log();
		try {
			if (result.equals("teacher") || result.equals("student")) {

				jsonObject.put("userBean", getUserBean());
				jsonObject.put("photoPath", getUserBean().getPhoto());
				jsonObject.put("result", "success");

				TeamDAO teamDAO = new TeamDAOImpl();
				ArrayList<Integer>teamIdList = teamDAO.getTeamIdListByStudentId(getUserBean().getId());
				if (teamIdList == null)
					teamIdList = new ArrayList<>();
				jsonObject.put("teamIdList", teamIdList);

				this.response.setCharacterEncoding("UTF-8");
				this.response.getWriter().write(jsonObject.toString());
				this.response.getWriter().flush();
				this.response.getWriter().close();
				System.out.println(session);
				return "success";
			} else {
				jsonObject.put("result", "fail");

				this.response.setCharacterEncoding("UTF-8");
				this.response.getWriter().write(jsonObject.toString());
				this.response.getWriter().flush();
				this.response.getWriter().close();
				return "fail";
			}
		}catch (Exception e){
			e.printStackTrace();
			return "fail";
		}
	}

	private String teacherLogIn(int teacherId){
		ProjectDAO projectDAO = new ProjectDAOImpl();
		System.out.println(1);
		try {
			ArrayList<Integer> projectIdList = projectDAO.getProjectIdListByTeacherId(teacherId);
			if (projectIdList == null){
				projectIdList = new ArrayList<>();
			}
			int projectNum = projectIdList.size();
			System.out.println(2);

			HashMap<Integer, ProjectBean> projectId_BeanMap = new HashMap<>();
			for (int projectId : projectIdList){
				ProjectBean projectBean = projectDAO.getProjectInfo(projectId);
				projectId_BeanMap.put(projectBean.getId(), projectBean);
			}
			ArrayList<String> temp = new ArrayList<>();
			System.out.println(3);
			temp.add("projectNum");
			temp.add("projectIdList");
			temp.add("projectId_BeanMap");
			session = SessionTools.removeAttributes(session, temp);
			session.put("projectNum", projectNum);
			session.put("projectIdList", projectIdList);
			session.put("projectId_BeanMap", projectId_BeanMap);
			return "teacher";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	private String studentLogIn(int id){
		TeamDAO teamDAO = new TeamDAOImpl();
		ProjectDAO projectDAO = new ProjectDAOImpl();
		try {
			ArrayList<Integer> teamIdList = teamDAO.getTeamIdListByStudentId(id);
			if (teamIdList == null)
				teamIdList = new ArrayList<>();

			HashMap<Integer, HashMap<Integer, ProjectBean> >teamId_projectBean = new HashMap<>();
			for (int teamId : teamIdList){
				ArrayList<Integer> projects = projectDAO.getProjectIdListByTeamId(teamId);
				if (projects == null){
					projects = new ArrayList<>();
				}
				HashMap<Integer, ProjectBean> projectBeanHashMap = new HashMap<>();
				for (int projectId : projects){
					projectBeanHashMap.put(projectId, projectDAO.getProjectInfo(projectId) );
				}
				teamId_projectBean.put(teamId, projectBeanHashMap);
			}
			session = SessionTools.removeAttribute(session, "teamIdList");
			session = SessionTools.removeAttribute(session, "teamId_projectBean");
			session.put("teamIdList", teamIdList);
			session.put("teamId_projectBean", teamId_projectBean);
			return "student";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
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
}


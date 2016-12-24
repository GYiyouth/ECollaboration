package actions.logIn;

import DAO.userDAO.UserDAO;
import DAO.userDAO.UserDAOImpl;
import bean.domain.UserBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
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
			File file = userDAO.getUserPhoto(userBean);
			userBean.setPhoto(file.getCanonicalPath());
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
			return "success";
		}else
			return "fail";
	}

	public void appLog() throws Exception{

		JSONObject jsonObject = new JSONObject();

		try {
			if (log().equals("success")) {

				jsonObject.put("userBean", getUserBean());
				jsonObject.put("photoPath", getUserBean().getPhoto());
				jsonObject.put("result", "success");


				this.response.setCharacterEncoding("UTF-8");
				this.response.getWriter().write(jsonObject.toString());
				this.response.getWriter().flush();
				this.response.getWriter().close();

			} else {
				jsonObject.put("result", "fail");

				this.response.setCharacterEncoding("UTF-8");
				this.response.getWriter().write(jsonObject.toString());
				this.response.getWriter().flush();
				this.response.getWriter().close();
				;
			}
		}finally {

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


package actions.log;

import DAO.userDAO.UserDAO;
import DAO.userDAO.UserDAOImpl;
import bean.domain.UserBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


/**
 * 登录action
 *
 * Created by geyao on 2016/12/13.
 */
public class LogAction implements ServletRequestAware, ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String userName;
	private String passWord;
	private UserBean userBean;

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
	}

	/**
	 * Sets the HTTP response object in implementing classes.
	 *
	 * @param response the HTTP response.
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String log() throws Exception {
		UserDAO userDAO = new UserDAOImpl();
		UserBean userBean = new UserBean();
		userBean = userDAO.getLogInfo(userName, passWord);
		if (userBean!=null) {
			File file = userDAO.getUserPhoto(userBean);
			userBean.setPhoto(file.getCanonicalPath());
			this.setUserBean(userBean);

			return "success";
		}else
			return "fail";
	}

	public String appLog() throws Exception{
		if (log().equals("success")){
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userBean", getUserBean());
			jsonObject.put("photoPath", getUserBean().getPhoto());
			jsonArray.add(jsonObject);

			this.response.setCharacterEncoding("UTF-8");
			this.response.getWriter().write(jsonArray.toString());
			return "success";
		}else
			return "fail";
	}
}


package actions.team;

import bean.domain.StudentBean;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by geyao on 2016/12/24.
 */
public class ApplyProjectAction implements SessionAware, ServletResponseAware, ServletRequestAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;

	private StudentBean studentBean;

	public String execute() throws Exception {
		return "success";
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
			this.request.setCharacterEncoding("UTF_8");
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
}

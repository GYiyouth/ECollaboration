package actions.logIn;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * 登出，清空session里的值
 * Created by geyao on 2017/1/1.
 */
public class LogOutAction implements SessionAware{
	private Map session;
	public String execute() throws Exception {
		session.clear();
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}

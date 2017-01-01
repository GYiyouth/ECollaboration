package interceptors.com;

import actions.logIn.LogInAction;
import bean.domain.UserBean;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by GR on 2016/12/24.
 */
public class LogInValidationInterceptor extends AbstractInterceptor{
    private HttpServletRequest request;
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        Map<String, Object> session= actionInvocation.getInvocationContext().getSession();
//        System.out.println( actionInvocation.getInvocationContext().getParameters().get("passWord").toString() );
        UserBean userBean = (UserBean) session.get("userBean");

        request.setCharacterEncoding("UTF-8");
        System.out.println(request.getParameterMap());
        if(LogInAction.class==actionInvocation.getAction().getClass())
        {
            //登录这个动作不应该被拦截
            return actionInvocation.invoke();
        }
        if (userBean == null) {
            //终止执行，返回登录页面
            System.out.println("没登录");
            return "login";
        } else {
            //继续执行剩余的拦截器和Action
            System.out.println(userBean);
            return actionInvocation.invoke();
        }
    }
}

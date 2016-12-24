package interceptors.com;

import actions.logIn.LogInAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * Created by GR on 2016/12/24.
 */
public class LogInValidationInterceptor extends AbstractInterceptor{

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("登录拦截器");
        Map<String, Object> session= actionInvocation.getInvocationContext().getSession();
        String userName = (String) session.get("userName");
        System.out.println("已经登录的用户："+userName+"!");
        if(LogInAction.class==actionInvocation.getAction().getClass())
        {
            System.out.println("当前进行登录呢。。");
            return actionInvocation.invoke();
        }
        if (userName == null) {
            //终止执行，返回登录页面
            return "login";
        } else {
            //继续执行剩余的拦截器和Action
            return actionInvocation.invoke();
        }
    }
}

package actions.plan;

import DAO.planDAO.PlanDAO;
import DAO.planDAO.PlanDAOImpl;
import bean.domain.PlanBean;
import bean.domain.UserBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 完成计划
 * Created by GR on 2016/12/25.
 */
public class PlanFinishAction implements SessionAware, ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> session;

    //jsp获取
    private Integer planId;
    private String finishDate;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
        this.response.setCharacterEncoding("UTF-8");
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String finishPlan() throws Exception{
        UserBean userBean = new UserBean();
        PlanBean planBean = new PlanBean();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        finishDate = sdf.format(new Date());

        PlanDAO planDAO = new PlanDAOImpl();
        if(userBean.getId().equals(planDAO.getCreatorIdByPlanId(planId))) {   //判断是否是完成者
            if(planDAO.finishPlan(planId,finishDate))
                return "success";
            else
                return "fail";
        }else {
            return "fail";
        }
    }

    public void appFinishPlan() throws Exception{
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        if (finishPlan().equals("success")){

            jsonObject.put("result", "success");
            jsonArray.add(jsonObject);

            this.response.getWriter().write(jsonArray.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }else {
            jsonObject.put("result", "fail");
            jsonArray.add(jsonObject);

            this.response.getWriter().write(jsonArray.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }
    }

}

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
import java.util.Map;

/**
 * Created by GR on 2016/12/26.
 */
public class PlanModifyAction implements SessionAware, ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> session;

    //jsp获取
    private String title;
    private String content;
    private String beginDate;
    private String targetDate;
    private Integer projectId;
    private Integer planId;
//    private Integer teamId;




//    private Integer creatorId;
//    private String createDate;
//    private String finishDate;
//    private String beginDate;
//    private Integer studentId;
//    private Integer planId;

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

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    //
//    public Integer getCreatorId() {
//        return creatorId;
//    }
//
//    public void setCreatorId(Integer creatorId) {
//        this.creatorId = creatorId;
//    }
//
//    public String getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(String createDate) {
//        this.createDate = createDate;
//    }
//
//    public String getFinishDate() {
//        return finishDate;
//    }
//
//    public void setFinishDate(String finishDate) {
//        this.finishDate = finishDate;
//    }
//
//    public String getBeginDate() {
//        return beginDate;
//    }
//
//    public void setBeginDate(String beginDate) {
//        this.beginDate = beginDate;
//    }
//
//    public Integer getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(Integer studentId) {
//        this.studentId = studentId;
//    }
//
//    public Integer getPlanId() {
//        return planId;
//    }
//
//    public void setPlanId(Integer planId) {
//        this.planId = planId;
//    }

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

    public String modifyPlan() throws Exception{
        UserBean userBean = (UserBean)session.get("userBean");
        PlanDAO planDAO = new PlanDAOImpl();
        PlanBean planBean = new PlanBean();
        planBean.setId(planId);
        planBean.setTitle(title);
        planBean.setContent(content);
        planBean.setCreatorId(userBean.getId());
        planBean.setBeginDate(beginDate);
        planBean.setTargetDate(targetDate);
        if(userBean.getId().equals(planDAO.getCreatorIdByPlanId(planId))){
            if(planDAO.updatePlanByPlanBean(planBean))
                return "success";
            else
                return "fail";
        }else{
            return "fail";
        }

    }

    public void appModifyPlan() throws Exception{
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        if (modifyPlan().equals("success")){

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

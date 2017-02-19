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
 * 创建计划给自己
 *
 * Created by GR on 2016/12/25.
 */
public class CreatePlanToSelfAction implements SessionAware, ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> session;

    //jsp获取
    private String title;
    private String content;
    private String targetDate;
    private Integer projectId;
    private String beginDate;
//    private Integer teamId;



    private Integer id;
    private Integer creatorId;
    private String createDate;
    private String finishDate;

    private Integer studentId;
    private Integer planId;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
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

    public String createPlanToSelf() throws Exception{
        UserBean userBean = new UserBean();
        PlanBean planBean = new PlanBean();
        creatorId = userBean.getId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createDate = sdf.format(new Date());

        planBean.setTitle(title);
        planBean.setContent(content);
        planBean.setCreatorId(creatorId);
        planBean.setCreateDate(createDate);
        planBean.setFinishDate(finishDate);
        planBean.setBeginDate(beginDate);
        planBean.setTargetDate(targetDate);

        studentId = userBean.getId();

        PlanDAO planDAO = new PlanDAOImpl();
        if(planDAO.addPlanToStudent(planBean,studentId,projectId))
            return "success";
        else
            return "fail";
    }

    public void appCreatePlanToSelf() throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (createPlanToSelf().equals("success")){
            jsonObject.put("result", "success");
            this.response.getWriter().write(jsonObject.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }else {
            jsonObject.put("result", "fail");
            this.response.getWriter().write(jsonObject.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }
    }

}

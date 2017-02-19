package actions.task;

import DAO.taskDAO.TaskDAO;
import DAO.taskDAO.TaskDAOImpl;
import bean.domain.ProjectBean;
import bean.domain.TaskBean;
import bean.domain.UserBean;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 查询项目的所有任务
 * 需要参数：projectId
 * 返回 ArrayList<TaskBean>
 * Created by GR on 2017/1/5.
 */
public class GetTaskListAction  implements SessionAware, ServletRequestAware, ServletResponseAware {

    private Map<String, Object> session;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private Integer projectId;

    private Integer id ;
    private String title ;
    private String content ;
    private Integer creatorId ;
    private String createDate ;
    private String modifyDate ;
    private String beginDate ;
    private String targetDate ;
    private ArrayList<TaskBean> taskBeans;

    public String getTaskList(){
        UserBean userBean = (UserBean)session.get("userBean");
        TaskDAO taskDAO = new TaskDAOImpl();
        try {
            ArrayList<TaskBean> taskBeans = new ArrayList<>();
            ArrayList<Integer> taskIds = taskDAO.getTaskIdListByProjectId(projectId);
            if(taskIds == null || taskIds.size() == 0){
                this.taskBeans = new ArrayList<>();
                return "success";
            }else{
                for(int i = 0; i < taskIds.size(); i++){
                    TaskBean taskBean = taskDAO.getTaskInfo(taskIds.get(i));
                    if(taskBean != null){
                        taskBeans.add(taskBean);

                    }else{
                        continue;
                    }
                }
                setTaskBeans(taskBeans);
                switch(userBean.getRole()){
                    case 1:
                    case 2:return "teacher";
                    case 3:return "student";
                    default:return "fail";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    public void appGetTaskList() throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (getTaskList().equals("success")){

            jsonObject.put("taskBeans", getTaskBeans());
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

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public ArrayList<TaskBean> getTaskBeans() {
        return taskBeans;
    }

    public void setTaskBeans(ArrayList<TaskBean> taskBeans) {
        this.taskBeans = taskBeans;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
            this.request = request;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        this.response = response;
    }

}

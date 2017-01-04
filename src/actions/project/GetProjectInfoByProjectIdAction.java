package actions.project;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import bean.domain.ProjectBean;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 根据projectId 获取项目信息
 * 参数：projectId
 * return: projectBean
 * Created by GR on 2017/1/5.
 */
public class GetProjectInfoByProjectIdAction implements SessionAware, ServletRequestAware, ServletResponseAware {
    private Map session;
    private Integer projectId;
    private ProjectBean projectBean;

    private HttpServletRequest request;
    private HttpServletResponse response;

    public String getProjectInfoByProjectId(){
        ProjectBean projectBean = new ProjectBean();
        ProjectDAO projectDAO = new ProjectDAOImpl();
        try {
            projectBean = projectDAO.getProjectInfo(projectId);
            if(projectBean == null)
                return "fail";
            else {
                setProjectBean(projectBean);
                return "success";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    public void appGetProjectInfoByProjectId() throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (getProjectInfoByProjectId().equals("success")){

            jsonObject.put("projectBean", getProjectBean());
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

    public ProjectBean getProjectBean() {
        return projectBean;
    }

    public void setProjectBean(ProjectBean projectBean) {
        this.projectBean = projectBean;
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

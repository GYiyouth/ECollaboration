package actions.team;

import DAO.ECFileDAO.ECFileDAOImpl;
import DAO.codeDAO.CodeDAOImpl;
import DAO.planDAO.PlanDAOImpl;
import DAO.studentDAO.StudentDaoImpl;
import DAO.taskDAO.TaskDAOImpl;
import DAO.teamDAO.TeamDAOImpl;
import bean.domain.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * 获取团队的详细信息
 * Created by GR on 2016/12/14.
 */
public class GetMyJoinTeamInfoAction implements ServletRequestAware, ServletResponseAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private int projectId;
//    private int studentId;
    private int teamId;

    private ArrayList<ArrayList> teamInfoList;
    private ArrayList<PlanBean> teamPlanBeans;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public ArrayList<ArrayList> getTeamInfoList() {
        return teamInfoList;
    }

    public void setTeamInfoList(ArrayList<ArrayList> teamInfoList) {
        this.teamInfoList = teamInfoList;
    }

    public ArrayList<PlanBean> getTeamPlanBeans() {
        return teamPlanBeans;
    }

    public void setTeamPlanBeans(ArrayList<PlanBean> teamPlanBeans) {
        this.teamPlanBeans = teamPlanBeans;
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


    public String getMyJoinTeamInfo() throws Exception{
        TeamDAOImpl teamDaoImpl = new TeamDAOImpl();
        StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
        PlanDAOImpl planDaoImpl = new PlanDAOImpl();
        CodeDAOImpl codeDaoImpl = new CodeDAOImpl();
        ECFileDAOImpl fileDaoImpl = new ECFileDAOImpl();

        try{
//            int teamId = teamDaoImpl.getTeamIdByStudentIdProjectId(studentId, projectId);
//            System.out.println("teamId"+teamId);
            ArrayList<Integer> studentIds = studentDaoImpl.getStudentIdByTeamIdProjectId(teamId,projectId);
            ArrayList<PlanBean> teamPlanBeans = new ArrayList<>();

            ArrayList<Integer> teamPlanIds = planDaoImpl.getPlanIdListByTeamIdProjectId(teamId,projectId);
            for(int temp:teamPlanIds){
                System.out.println("团队任务id"+temp);
                PlanBean planBean = planDaoImpl.getPlanInfoByPlanId(temp);
                teamPlanBeans.add(planBean);
            }

            ArrayList<ArrayList> teamInfoList = new ArrayList();
            for(int i=0;i<studentIds.size();i++){
                ArrayList studentInfo = new ArrayList();
                StudentBean studentBean = studentDaoImpl.getInfoById(studentIds.get(i));
                System.out.println("学生个人主页"+studentBean.getHomePageUrl());
                int codeSum = codeDaoImpl.getCodeRowsSum(studentIds.get(i),1);
                int fileSum = fileDaoImpl.getFileSum(studentIds.get(i),1);
                System.out.println("代码："+codeSum+"文件"+fileSum);

                ArrayList<ArrayList<PlanBean>> stagePlanBeanSum = new ArrayList();
                for(int j=0;j<teamPlanBeans.size();j++){
                    ArrayList<PlanBean> stagePlanBean = new ArrayList<>();
                    PlanBean teamPlanBean = teamPlanBeans.get(j);
                    System.out.println("团队任务的每个任务的标题:"+teamPlanBean.getTitle());
                    ArrayList<Integer> planIds = planDaoImpl.getPlanIdBetweenATimeBTime(teamPlanBean.getBeginDate(),teamPlanBean.getTargetDate(),studentIds.get(i),1);
                    if(planIds.size()==0)
                        stagePlanBean.add(null);
                    else {
                        for (int k = 0; k < planIds.size(); k++) {
                            PlanBean planBean = planDaoImpl.getPlanInfoByPlanId(planIds.get(k));
                            stagePlanBean.add(planBean);
                        }
                    }
                    stagePlanBeanSum.add(stagePlanBean);
                }

                studentInfo.add(studentBean);
                studentInfo.add(codeSum);
                studentInfo.add(fileSum);
                studentInfo.add(stagePlanBeanSum);
                teamInfoList.add(studentInfo);

            }
            return "success";
        }catch(Exception e){
            return "fail";
        }

    }

    public void appGetMyJoinTeamInfo() throws Exception{

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();



        if (getMyJoinTeamInfo().equals("success")){
            jsonObject.put("teamPlanBeans",getTeamPlanBeans());
            jsonObject.put("teamInfoList", getTeamInfoList());
            jsonObject.put("result", "success");
            jsonArray.add(jsonObject);
            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().write(jsonArray.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }else {
            jsonObject.put("result", "fail");
            jsonArray.add(jsonObject);
            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().write(jsonArray.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }

    }


}

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
 * ok
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

    /**
     * 返回：
     * 成功：1.result=success，
     *      2.ArrayList<PlanBean> teamPlanBeans 团队计划，按照顺序存储团队计划<PlanBean>的arraylist数组
     *      3.ArrayList<ArrayList> teamInfoList包含小组所有人的信息：（ArrayList）teamInfoList.get(i)为第i位同学信息，默认组长在第一个
     *          学生信息里包括 3.1 学生个人信息（StudentBean）
     *                          3.2 代码行数（int型）
     *                          3.3 上传文件总数（int型）
     *                          3.4 个人每阶段完成所有任务的集合，阶段以团队任务划分ArrayList<ArrayList<PlanBean>> stagePlanBeanSum
     *                                 3.4.1 ArrayList<PlanBean>的每个阶段的完成任务集合：其中，如果某个阶段没有完成任务，则stagePlanBeanSum.get（i）==null
     *
     * @throws Exception
     */
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

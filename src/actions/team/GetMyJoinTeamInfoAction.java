package actions.team;

import DAO.ECFileDAO.ECFileDAOImpl;
import DAO.codeDAO.CodeDAOImpl;
import DAO.planDAO.PlanDAOImpl;
import DAO.studentDAO.StudentDaoImpl;
import DAO.taskDAO.TaskDAOImpl;
import DAO.teamDAO.TeamDAOImpl;
import actions.plan.PlanFinishAction;
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
    private int teamId;

//    private ArrayList<ArrayList> teamInfoList;
    private ArrayList<PlanBean> teamPlanBeans;
    private ArrayList<StudentBean> studentBeans;    //学生信息集合
    private ArrayList<String> codeSums;    //代码行数集合
    private ArrayList<String> fileSums;    //文件数集合
    private ArrayList<ArrayList<PlanBean>> planBeansAllStudents;  //所有学生计划集合



    public String getMyJoinTeamInfo() throws Exception{
        System.out.println("getMyJoinTeam");
        TeamDAOImpl teamDaoImpl = new TeamDAOImpl();
        StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
        PlanDAOImpl planDaoImpl = new PlanDAOImpl();
        CodeDAOImpl codeDaoImpl = new CodeDAOImpl();
        ECFileDAOImpl fileDaoImpl = new ECFileDAOImpl();
        if(projectId==-1){  //没有项目
            return "noProject";
        }else {
            try {
                System.out.println("teamId" + teamId);
                ArrayList<PlanBean> teamPlanBeans = new ArrayList<PlanBean>();
                ArrayList<StudentBean> studentBeans = new ArrayList<>();
                ArrayList<String> codeSums = new ArrayList<>();
                ArrayList<String> fileSums = new ArrayList<>();
                ArrayList<ArrayList<PlanBean>> planBeansAllStudents = new ArrayList<>();
                ArrayList<Integer> studentIds = studentDaoImpl.getStudentIdByTeamIdProjectId(teamId, projectId);
                System.out.println(studentIds.get(0) + ":0");


                //团队的计划集合
                ArrayList<Integer> teamPlanIds = planDaoImpl.getPlanIdListByTeamIdProjectId(teamId, projectId);
                for (int temp : teamPlanIds) {
                    System.out.println("团队任务id" + temp);
                    PlanBean planBean = planDaoImpl.getPlanInfoByPlanId(temp);
                    teamPlanBeans.add(planBean);
                    setTeamPlanBeans(teamPlanBeans);
                }

                //团队成员各种信息
                ArrayList<ArrayList> teamInfoList = new ArrayList();
                for (int i = 0; i < studentIds.size(); i++) {
                    ArrayList studentInfo = new ArrayList();
                    StudentBean studentBean = studentDaoImpl.getInfoById(studentIds.get(i));
                    System.out.println("学生个人主页" + studentBean.getHomePageUrl());
                    String codeSum = codeDaoImpl.getCodeRowsSum(studentIds.get(i), projectId).toString();
                    String fileSum = fileDaoImpl.getFileSum(studentIds.get(i), projectId).toString();
                    System.out.println("代码：" + codeSum + "文件" + fileSum);

                    ArrayList<PlanBean> planBeans = new ArrayList();
                    ArrayList<Integer> planIds = planDaoImpl.getPlanIdsFinishedByStudentIdProjectId(studentIds.get(i),projectId);
                    if(planIds.size()==0)   //没有完成任务
                        planBeans.add(null);
                    else {
                        for (int j = 0; j < planIds.size(); j++) {
                            ArrayList<PlanBean> stagePlanBean = new ArrayList<>();
                            PlanBean teamPlanBean = teamPlanBeans.get(j);
                            PlanBean planBean = planDaoImpl.getPlanInfoByPlanId(planIds.get(j));
                            planBeans.add(planBean);
                        }
                    }
                    studentBeans.add(studentBean);
                    codeSums.add(codeSum);
                    fileSums.add(fileSum);
                    planBeansAllStudents.add(planBeans);

                    //set
                    setStudentBeans(studentBeans);
                    setCodeSums(codeSums);
                    setFileSums(fileSums);
                    setPlanBeansAllStudents(planBeansAllStudents);
                }
                return "success";
            } catch (Exception e) {
                return "fail";
            }
        }

    }

    /**
     * 返回：
     * 成功：1.result=success
     *      2.ArrayList<PlanBean> teamPlanBeans 团队计划，按照顺序存储团队计划<PlanBean>的arraylist数组
     *          //下面四个list顺序是对应的
     *      3.rrayList<StudentBean> studentBeans 学生的信息  第一个是组长
     *      4.ArrayList<Integer> codeSums   每个人的代码量
     *      5.ArrayList<Integer> fileSum    每个人的文件数
     *      6.ArrayList<ArrayList<PlanBean>> planBeansAllStudents   每个人完成的计划的集合
     * @throws Exception
     */
    public void appGetMyJoinTeamInfo() throws Exception{

        JSONObject jsonObject = new JSONObject();
        if (getMyJoinTeamInfo().equals("success")){
            jsonObject.put("teamPlanBeans",getTeamPlanBeans());
            jsonObject.put("studentBeans", getStudentBeans());
            jsonObject.put("codeSums", getCodeSums());
            jsonObject.put("fileSum", getFileSums());
            jsonObject.put("planBeansAllStudents", getPlanBeansAllStudents());
            jsonObject.put("result", "success");
            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().write(jsonObject.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }else {
            jsonObject.put("result", "fail");
            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().write(jsonObject.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }

    }

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

    public ArrayList<StudentBean> getStudentBeans() {
        return studentBeans;
    }

    public void setStudentBeans(ArrayList<StudentBean> studentBeans) {
        this.studentBeans = studentBeans;
    }

    public ArrayList<String> getCodeSums() {
        return codeSums;
    }

    public void setCodeSums(ArrayList<String> codeSums) {
        this.codeSums = codeSums;
    }

    public ArrayList<String> getFileSums() {
        return fileSums;
    }

    public void setFileSums(ArrayList<String> fileSums) {
        this.fileSums = fileSums;
    }

    public ArrayList<ArrayList<PlanBean>> getPlanBeansAllStudents() {
        return planBeansAllStudents;
    }

    public void setPlanBeansAllStudents(ArrayList<ArrayList<PlanBean>> planBeansAllStudents) {
        this.planBeansAllStudents = planBeansAllStudents;
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


}

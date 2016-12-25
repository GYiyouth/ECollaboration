package actions.team;

import DAO.messageDAO.MessageDAOImpl;
import DAO.studentDAO.StudentDaoImpl;
import DAO.teamDAO.TeamDAOImpl;
import DAO.userDAO.UserDAOImpl;
import bean.domain.MessageBean;
import bean.domain.StudentBean;
import bean.domain.UserBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 申请加入团队
 * 参数：学生id，团队id
 * Created by GR on 2016/12/13.
 */
public class JoinTeamAction implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Integer teamId;
    private Integer studentId;


    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    /**
     *
     * @return 成功：success，失败 :fail
     * @throws Exception
     */
    public String joinTeam() throws Exception{
        StudentDaoImpl studentDaoImpl = new StudentDaoImpl();

        boolean flag = studentDaoImpl.addStudentToTeam(studentId, teamId, 0);    //0代表申请团队还没经过同意
        if(flag)
            return "success";
        else
            return "fail";

    }


    /**
     * 返回result  成功：success  失败：fail
     * @throws Exception
     */
    public void appJoinTeam() throws Exception{

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        if (joinTeam().equals("success")){
            TeamDAOImpl teamDaoImpl = new TeamDAOImpl();
            MessageDAOImpl messageDaoImpl = new MessageDAOImpl();
            StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
            UserDAOImpl userDaoImpl = new UserDAOImpl();

            UserBean user = new UserBean();
            MessageBean message = new MessageBean();
            StudentBean student = new StudentBean();

            //发消息
            student = studentDaoImpl.getInfoById(studentId);
            user = userDaoImpl.getUserInfoById(student.getId());
            int teamCreatorId = teamDaoImpl.getCreatorIdByTeamId(teamId);

            message.setTitle("anybody join！");
            message.setContent(user.getName()+"join in");
            //消息保存一个月
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar cal = Calendar.getInstance();

            String createTime = format.format(cal.getTime());
            message.setCreateTime(createTime);

            cal.add(Calendar.MONTH, 1);
            String deadTime = format.format(cal.getTime());
            message.setDeadDate(deadTime);

            System.out.println(createTime);
            message.setCreateTime(createTime);
            message.setSenderId(1);
            message.setReadFlag(0);


            int messageId = messageDaoImpl.addMessage(message);
            messageDaoImpl.addMessageOneReceiver(messageId,teamCreatorId);


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

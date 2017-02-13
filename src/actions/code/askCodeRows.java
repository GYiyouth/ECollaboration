package actions.code;

import DAO.codeDAO.CodeDAO;
import DAO.codeDAO.CodeDAOImpl;
import DAO.studentDAO.StudentDAO;
import DAO.studentDAO.StudentDaoImpl;
import bean.domain.CodeBean;
import com.sun.org.apache.bcel.internal.classfile.Code;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import smallTools.JSONHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询某个人、团队，在特定项目下的代码量
 * 需要：projectId不可为空
 *      getStudentCodes查询某个学生在该项目下的代码量
 *          额外需要studentId
 *          返回 rows
 *      getTeamCodes查询某个团队在该项目下的代码量
 *          额外需要teamId
 *          返回 studentNum - 该团队人数
 *              xid - x 为从 1～n的数字，第x位成员的id
 *              xrow - x 为1～n的数字，第x位成员的代码量
 *              teamRows - 总代码量
 * Created by geyao on 2017/1/3.
 */
public class askCodeRows implements ServletRequestAware, ServletResponseAware, SessionAware{

	private Integer projectId;
	private Integer teamId;
	private Integer studentId;
	private Integer teamRows;
	private Integer studentRow;
	private CodeBean codeBean = new CodeBean();
	private HashMap<Integer, CodeBean> CodeBeans = new HashMap<>();
	private HashMap<Integer, Integer> students = new HashMap<>();
	private HashMap<Integer, Integer> studentRows = new HashMap<>();
	private HttpServletRequest request;
	private HttpServletResponse response;



	private Map session;

	public void getStudentCodes() throws Exception {
		CodeDAO codeDAO = new CodeDAOImpl();
		int rows = codeDAO.getCodeRowsSum(studentId, projectId);
		setStudentRow(rows);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("rows", studentRow);
		JSONHandler.sendJSON(jsonObject, response);
	}

	public void getTeamCodes() throws Exception{
		JSONObject jsonObject = new JSONObject();
		CodeDAO codeDAO = new CodeDAOImpl();
		setCodeBeans(codeDAO.getCodeBeans(projectId, teamId));
		StudentDAO studentDAO = new StudentDaoImpl();
		this.students = studentDAO.getStudentIdMapByTeamId(teamId);
		int studentNum = students.size();
		jsonObject.put("studentNum", studentNum);
		//遍历每一个学生
		for (int i =1; i <= studentNum; i++){
			int studentId = students.get(i);
			int tempRows =0;
			if (!studentRows.containsKey(studentId))
				studentRows.put(studentId, tempRows);
			//遍历codeBean的哈希表，找匹配的
			for (CodeBean tempBean : getCodeBeans().values()){
				if (tempBean.getStudentId() == studentId) {
					tempRows = studentRows.get(i) + tempBean.getRow();
					studentRows.replace(i, tempRows);
					jsonObject.put(i + "id", studentId);
					jsonObject.put(i + "row", tempRows);
				}
			}
		}
		//处理团队总代码
		teamRows = 0;
		for (int rows : studentRows.values()){
			teamRows += rows;
		}

		jsonObject.put("result", "success");
		jsonObject.put("teamRows", teamRows);
		JSONHandler.sendJSON(jsonObject, response);
	}




	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

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

	public Integer getTeamRows() {
		return teamRows;
	}

	public void setTeamRows(Integer teamRows) {
		this.teamRows = teamRows;
	}


	public HashMap<Integer, Integer> getStudents() {
		return students;
	}

	public void setStudents(HashMap<Integer, Integer> students) {
		this.students = students;
	}

	public Integer getStudentRow() {
		return studentRow;
	}

	public void setStudentRow(Integer studentRow) {
		this.studentRow = studentRow;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public CodeBean getCodeBean() {
		return codeBean;
	}

	public void setCodeBean(CodeBean codeBean) {
		this.codeBean = codeBean;
	}

	public HashMap<Integer, CodeBean> getCodeBeans() {
		return CodeBeans;
	}

	public void setCodeBeans(HashMap<Integer, CodeBean> codeBeans) {
		CodeBeans = codeBeans;
	}

	public HashMap<Integer, Integer> getStudentRows() {
		return studentRows;
	}

	public void setStudentRows(HashMap<Integer, Integer> studentRows) {
		this.studentRows = studentRows;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		try {
			this.request.setCharacterEncoding("UTF-8");
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

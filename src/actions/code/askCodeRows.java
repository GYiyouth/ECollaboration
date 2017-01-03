package actions.code;

import DAO.codeDAO.CodeDAO;
import DAO.codeDAO.CodeDAOImpl;
import DAO.studentDAO.StudentDAO;
import DAO.studentDAO.StudentDaoImpl;
import bean.domain.CodeBean;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.apache.struts2.interceptor.SessionAware;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询某个人、团队，在特定项目下的代码量
 * 需要：projectId不可为空
 *      studentId在查询具体某个人的代码量时不可为空
 *      teamId在查询整个团队代码量时不可为空，此时studentId可以为空
 * 提供：团队查询时：
 *      teamRows：该团队的代码量
 *      studentRows：学生代码量，为哈希表，id-rows
 *      students：学生编号-id键值对。将组长放在1，组员放在1 2 3
 *      个人查询时：
 *      提供
 *      studentRow
 * Created by geyao on 2017/1/3.
 */
public class askCodeRows implements SessionAware{

	private Integer projectId;
	private Integer teamId;
	private Integer studentId;
	private Integer teamRows;
	private Integer studentRow;
	private CodeBean codeBean = new CodeBean();
	private HashMap<Integer, CodeBean> CodeBeans = new HashMap<>();
	private HashMap<Integer, Integer> students = new HashMap<>();

	private Map session;

	public String getStudentCodes() throws Exception {
		CodeDAO codeDAO = new CodeDAOImpl();
		int rows = codeDAO.getCodeRowsSum(studentId, projectId);
		setStudentRow(rows);
		return "success";
	}

	public String getTeamCodes() throws Exception{
		CodeDAO codeDAO = new CodeDAOImpl();
		setCodeBeans(codeDAO.getCodeBeans(projectId, teamId));
		StudentDAO studentDAO = new StudentDaoImpl();
		studentDAO.getStudentIdByTeamId(teamId);
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
}

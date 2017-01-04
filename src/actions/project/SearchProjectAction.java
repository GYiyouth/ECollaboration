package actions.project;

import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.teacherDAO.TeacherDAO;
import DAO.teacherDAO.TeacherDAOImpl;
import bean.domain.ProjectBean;
import bean.domain.TeacherBean;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import smallTools.SessionTools;
import smallTools.StringCheck;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * form里要有keyWord，这个可以是字符串，我会分析
 * 搜索的团队年限是2000～2050年
 * form里只需要有keyWord
 * 返回
 * SearchResultHashMap
 * Created by geyao on 2016/12/31.
 */
public class SearchProjectAction implements SessionAware, ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;
	private String keyWord;
	private HashMap<Integer, ProjectBean> SearchResultHashMap = new HashMap<>();

	//以下是搜索关键字的列表
	private ArrayList<Integer> gradeList = new ArrayList<>();
	private ArrayList<String> ProNameList = new ArrayList<>();
	private ArrayList<String> keyWordList = new ArrayList<>();
	private ArrayList<TeacherBean> teacherList = new ArrayList<>();
	private ArrayList<String> studentList = new ArrayList<>();
	private ArrayList<String> remainList = new ArrayList<>();
	private boolean gradeFlag = false;
	private boolean proNameFlag = false;
	private boolean keyWordFlag = false;
	private boolean teacherFlag = false;
	private boolean studentFlag = false;

	private static HashMap<Integer, TeacherBean> teacherBeanDB = new HashMap<>();
	private static ArrayList<String> keyWordListDB = new ArrayList<>();

	static {
		TeacherDAO teacherDAO = new TeacherDAOImpl();
		try {
			teacherBeanDB = teacherDAO.getAllTeacher();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//关键词的集合
		String[] temp = {"java", "c", "c++", "c#", "机器学习", "深度学习", "python", "php", "web", "javaweb", "web"
		,".net", "前端", "后端", "大数据", "数据挖掘", "多媒体", "云计算", "云", "tomcat", "搜索", "信息安全", "it", "浏览器",
		"ios", "苹果", "移动", "android", "安卓", "游戏", "服务器", "linux", "windows", "unix", "network", "网络编程", "unity"
		,"struts", "struts2", "hibernate", "spring", "hadoop", "xml", "html", "html5","网页"};
		for (String a : temp){
			keyWordListDB.add(a);
		}

	}
	//网页端查询
	public String execute() throws Exception {
		try {
			this.setSearchResultHashMap(analyse(getKeyWord()));
			this.session = SessionTools.removeAttribute(session, "SearchResultHashMap");
			this.session.put("SearchResultHashMap", getSearchResultHashMap());
			System.out.println("搜索结果是"+getSearchResultHashMap());
		return "success";
		}catch (Exception e){
			e.printStackTrace();
			return "fail";
		}
	}

	public void appExecute() throws Exception{
		JSONObject jsonObject = new JSONObject();
		String result = execute();
		if (result.equals("success")){
			jsonObject.put("result", "success");
			jsonObject.put("SearchResultHashMap", getSearchResultHashMap());
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

	private HashMap<Integer, ProjectBean> analyse(String keyWord) throws SQLException {
		this.keyWord = keyWord.trim();
		String[] elements = this.keyWord.split(" ");
		System.out.println("输入的关键词是" + elements);
		for (String element : elements){
			if (element.equals(" ") || element.equals(" "))
				continue;
			boolean tempGrade = analyseGrade(element);
			if (tempGrade)
				continue;
			boolean tempkey = analyseKeyWord(element);
			boolean tempTecher = analyseTeacher(element);
			boolean tempProName = analyseProjectName(element);
			if (tempGrade || tempkey || tempProName || tempTecher){
				continue;
			}else{
				remainList.add(element);
			}
		}
		ProjectDAO projectDAO = new ProjectDAOImpl();
		//仅获取校园工程实践
		return createResult(projectDAO.getAllProject(0));
	}

	private HashMap<Integer, ProjectBean> createResult(HashMap<Integer, ProjectBean> db){
		HashMap<Integer, ProjectBean> result1 = new HashMap<>();
		if (gradeFlag == true){
			for (int grade : gradeList){
				for (ProjectBean temp :
						db.values()) {
					if ( temp.getGrade() == grade)
						result1.put(temp.getId(), temp);
				}
			}
			gradeFlag = false;
		}
		HashMap<Integer, ProjectBean> result2 = new HashMap<>();
		if (teacherFlag == true){
			for (TeacherBean bean : teacherList){
				for (ProjectBean temp :	db.values()) {
					if (bean.getId() == temp.getTeacherId())
						result2.put(temp.getId(), temp);
				}
			}
		}
		HashMap<Integer, ProjectBean> result3 = new HashMap<>();

		for (String key : keyWordList){
			for (ProjectBean temp :	db.values()) {
				if (       temp.getRequirement().contains(key)
						|| temp.getName().contains(key)
						|| temp.getGain().contains(key)
						|| temp.getInfo().contains(key)
						|| temp.getKeyWord().contains(key))
					result3.put(temp.getId(), temp);
			}
		}

		HashMap<Integer, ProjectBean> result4 = new HashMap<>();
		if (result3.size() == 0){
			for (String key : remainList){
				for (ProjectBean temp :	db.values()) {
					if (       temp.getRequirement().contains(key)
							|| temp.getName().contains(key)
							|| temp.getGain().contains(key)
							|| temp.getInfo().contains(key)
							|| temp.getKeyWord().contains(key))
						result4.put(temp.getId(), temp);
				}
			}
		}

		HashMap<Integer, ProjectBean> result = new HashMap<>();
		result.putAll(result1);
		result.putAll(result2);
		result.putAll(result3);
		result.putAll(result4);
		return result;
	}

	private boolean analyseGrade(String element){
		if (StringCheck.isNumeric(element )){
			//如果关键字包含数字
			int grade = Integer.parseInt(element);
			if (grade >= 2050 || grade <= 2000)
				return false;
			gradeFlag = true;
			gradeList.add(grade);
			return true;
		}else
			return false;
	}

	private boolean analyseProjectName(String element){
		ProjectDAO projectDAO = new ProjectDAOImpl();
		proNameFlag = true;
		ProNameList.add(element);
		return true;
	}

	private boolean analyseKeyWord(String element){
		if (keyWordListDB.contains(element)) {
			keyWordFlag = true;
			keyWordList.add(element);
			return true;
		}
		else
			return false;
	}

	private boolean analyseTeacher(String element){
		for (TeacherBean bean : teacherBeanDB.values()){
			if (bean.getName().contains(element)){
				teacherFlag = true;
				teacherList.add(bean);
			}
		}
		return teacherFlag;
	}

	private boolean analyseStudent(String element){
		return false;
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public HashMap<Integer, ProjectBean> getSearchResultHashMap() {
		return SearchResultHashMap;
	}

	public void setSearchResultHashMap(HashMap<Integer, ProjectBean> searchResultHashMap) {
		SearchResultHashMap = searchResultHashMap;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Map getSession() {
		return session;
	}



	public ArrayList<Integer> getGradeList() {
		return gradeList;
	}

	public void setGradeList(ArrayList<Integer> gradeList) {
		this.gradeList = gradeList;
	}

	public ArrayList<String> getProNameList() {
		return ProNameList;
	}

	public void setProNameList(ArrayList<String> proNameList) {
		ProNameList = proNameList;
	}

	public ArrayList<String> getKeyWordList() {
		return keyWordList;
	}

	public void setKeyWordList(ArrayList<String> keyWordList) {
		this.keyWordList = keyWordList;
	}

	public ArrayList<TeacherBean> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(ArrayList<TeacherBean> teacherList) {
		this.teacherList = teacherList;
	}

	public ArrayList<String> getStudentList() {
		return studentList;
	}

	public void setStudentList(ArrayList<String> studentList) {
		this.studentList = studentList;
	}

	public ArrayList<String> getRemainList() {
		return remainList;
	}

	public void setRemainList(ArrayList<String> remainList) {
		this.remainList = remainList;
	}

	public boolean isGradeFlag() {
		return gradeFlag;
	}

	public void setGradeFlag(boolean gradeFlag) {
		this.gradeFlag = gradeFlag;
	}

	public boolean isProNameFlag() {
		return proNameFlag;
	}

	public void setProNameFlag(boolean proNameFlag) {
		this.proNameFlag = proNameFlag;
	}

	public boolean isKeyWordFlag() {
		return keyWordFlag;
	}

	public void setKeyWordFlag(boolean keyWordFlag) {
		this.keyWordFlag = keyWordFlag;
	}

	public boolean isTeacherFlag() {
		return teacherFlag;
	}

	public void setTeacherFlag(boolean teacherFlag) {
		this.teacherFlag = teacherFlag;
	}

	public boolean isStudentFlag() {
		return studentFlag;
	}

	public void setStudentFlag(boolean studentFlag) {
		this.studentFlag = studentFlag;
	}

	public static HashMap<Integer, TeacherBean> getTeacherBeanDB() {
		return teacherBeanDB;
	}

	public static void setTeacherBeanDB(HashMap<Integer, TeacherBean> teacherBeanDB) {
		SearchProjectAction.teacherBeanDB = teacherBeanDB;
	}

	public static ArrayList<String> getKeyWordListDB() {
		return keyWordListDB;
	}

	public static void setKeyWordListDB(ArrayList<String> keyWordListDB) {
		SearchProjectAction.keyWordListDB = keyWordListDB;
	}
}

package bean.BusinessBean.Code;

import bean.com.XmlReaderBean;
import bean.domain.CodeBean;
import org.dom4j.DocumentException;

import java.io.File;
import java.io.IOException;

/**
 * 用来处理代码的业务逻辑方法集合
 * Created by geyao on 2016/12/20.
 */
public class CodeBusinessBean {


	private String outFormat;
	private String outPath;

	public CodeBusinessBean(){
		super();
		this.outFormat = " -xml";
	}

	/**
	 * 中间会在code所在文件夹下产生一个临时的xml文件
	 * 通过这个文件读取到code的信息，通过bean返回，然后删除这个临时文件
	 * @param codeBean
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public CommitBean reviewCode(CodeBean codeBean) throws IOException, DocumentException {
		setOutPath(codeBean.getPath());
		String xmlPath = codeBean.getPath().replace(codeBean.getCodeName(), "tmpXml.xml");
		String cmd = "cloc " + codeBean.getPath() + this.outFormat +
				" -out=" + xmlPath;
//		System.out.println(cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		while (p.isAlive())
			;
		File file = new File(xmlPath);
		XmlReaderBean xmlReaderBean = new XmlReaderBean();
		CommitBean commitBean = xmlReaderBean.getCommitBeanByXml(file);
		file.delete();
		return commitBean;
	}

	public String getOutFormat() {
		return outFormat;
	}

	public void setOutFormat(String outFormat) {
		this.outFormat = outFormat;
	}

	public String getOutPath() {
		return outPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}

	public static void main(String[] args) {
		CodeBusinessBean codeBusinessBean = new CodeBusinessBean();
		CodeBean codeBean = new CodeBean();
		codeBean.setPath("/Users/geyao/IdeaProjects/ECollaborationGit/src/actions/log/LogInAction.java");
		codeBean.setCodeName("LogInAction.java");
		try {
			System.out.println(codeBusinessBean.reviewCode(codeBean));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}

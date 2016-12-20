package bean.BusinessBean;

import bean.BusinessBean.Code.CommitBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * 读取xml文档
 * Created by geyao on 2016/12/20.
 */
public class XmlReaderBean {
	public XmlReaderBean(){
		super();
	}
	public CommitBean getCommitBeanByXml(File xmlFile) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(xmlFile);

		Element root = document.getRootElement();
		Element languages = root.element("languages");
		Element language = languages.element("language");
		int codeLine = Integer.parseInt( language.attributeValue("code").toString() );
		int commentLine = Integer.parseInt( language.attributeValue("comment").toString() );
		int blankLine = Integer.parseInt( language.attributeValue("blank").toString() );
		CommitBean commitBean = new CommitBean();
		commitBean.setBlank(blankLine);
		commitBean.setCode(codeLine);
		commitBean.setComment(commentLine);
		return commitBean;
	}
}

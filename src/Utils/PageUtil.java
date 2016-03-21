package Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PageUtil {
	public enum Type {
		TEXT, XML
	};

	private Type type;
	private FileWriter fw;

	public PageUtil(String filePath, Type type) throws IOException {
		this.type = type;
		fw = new FileWriter(new File(filePath));
	}

	public void printText(HtmlPage page) throws IOException {
		DomNodeList<DomNode> dnl = page.getChildNodes();
		for (int i = 0; i < dnl.size(); i++) {
			visitNode(dnl.get(i));
		}
		fw.flush();
		fw.close();
	}

	private void visitNode(DomNode dn) throws IOException {
		printContent(dn);
		DomNodeList<DomNode> childs = dn.getChildNodes();
		if (childs != null && childs.size() > 0) {
			for (int i = 0; i < childs.size(); i++) {
				visitNode(childs.get(i));
			}
		}
	}

	private void printContent(DomNode dn) throws IOException {
		String content = null;
		if (type == Type.TEXT) {
			content = dn.asText();
		} else {
			content = dn.asXml();
		}
		fw.write(content);
	}
}

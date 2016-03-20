import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Test {
	private static final String URL = "http://wenku.baidu.com/view/0d165532c1c708a1294a4450.html?from=search";

	public static final void main(String[] args) {
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
		// webClient.setJavaScriptTimeout(10000);//设置JS执行的超时时间
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.waitForBackgroundJavaScript(10000);// 设置JS后台等待执行时间
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 很重要，设置支持AJAX
		webClient.getOptions().setJavaScriptEnabled(true);
		try {
			HtmlPage dynamicPage = (HtmlPage) webClient.getPage(URL);
			webClient.waitForBackgroundJavaScript(1000 * 3);
			webClient.setJavaScriptTimeout(0);

			fw = new FileWriter("E:/spiderxml.txt");
			visitNodes(dynamicPage.getChildNodes());

			// fw.write(dynamicPage.asText());
			fw.flush();
			fw.close();
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static FileWriter fw;

	private static void visitNodes(DomNodeList<DomNode> dnl) throws IOException {
		for (int i = 0; i < dnl.size(); i++) {
			visitNode(dnl.get(i));
		}
	}

	private static void visitNode(DomNode dn) throws IOException {
		// fw.write(dn.asXml());
		printContent(dn);
		DomNodeList<DomNode> childs = dn.getChildNodes();
		if (childs == null || childs.size() == 0) {
			// fw.write(dn.asXml());
		} else {
			visitNodes(childs);
		}
	}

	// 匹配的结果有点奇怪：基本上没起到筛选的作用
	private static final String TITLE_HINT = "doc-tittle";
	private static final String INTRO_HINT = "<span id=\"docDesc";
	private static final String CONTENT_HINT = "reader-word-layer";

	private static void printContent(DomNode dn) throws IOException {
		String xml = dn.asXml();
		String text = dn.asText().trim();
		if (xml.contains(TITLE_HINT)) {
			fw.write("《" + text + "》 \n");
		} else if (xml.contains(INTRO_HINT)) {
			fw.write(text + "\n");
		} else if (xml.contains(CONTENT_HINT)) {
			fw.write(text);
		}
	}
}

package Utils;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class LoadUtil {
	private static LoadUtil loadUtil;
	private final WebClient webClient;

	private LoadUtil() {
		webClient = new WebClient(BrowserVersion.FIREFOX_38);
		webClient.getOptions().setCssEnabled(false);
	}

	public static LoadUtil getInstance() {
		if (null == loadUtil) {
			loadUtil = new LoadUtil();
		}
		return loadUtil;
	}

	public HtmlPage getPage(String url) throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		webClient.getOptions().setJavaScriptEnabled(false);
		return webClient.getPage(url);
	}

	public HtmlPage getAjaxPage(String url) {
		enableAjax();
		HtmlPage dynamicPage = null;
		try {
			dynamicPage = (HtmlPage) webClient.getPage(url);
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		webClient.waitForBackgroundJavaScript(1000 * 3);
		webClient.setJavaScriptTimeout(0);
		return dynamicPage;
	}

	private void enableAjax() {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.waitForBackgroundJavaScript(10000);// 设置JS后台等待执行时间
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 很重要，设置支持AJAX
		webClient.getOptions().setJavaScriptEnabled(true);
	}
}

package xinna;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import Utils.LoadUtil;

public class XinnaUrls {
	private String url;

	public XinnaUrls(String url) {
		this.url = url;
	}

	private static final String PRE = "http://blog.sina.com.cn/s/blog_";

	public List<String> getUrls() {
		HtmlPage page = null;
		try {
			page = LoadUtil.getInstance().getPage(url);
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		List<?> as = page.getByXPath("//a");
		HtmlAnchor anchor;
		String href;
		List<String> urls = new ArrayList<String>();
		for (Object o : as) {
			anchor = (HtmlAnchor) o;
			href = anchor.getAttribute("href");
			if (href.startsWith(PRE)) {
				urls.add(href);
			}
		}
		return urls;
	}
}

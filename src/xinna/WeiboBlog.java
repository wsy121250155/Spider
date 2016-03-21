package xinna;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlStrong;

import Utils.LoadUtil;

public class WeiboBlog {
	private String url;

	public WeiboBlog(String url) {
		this.url = url;
	}

	private static final String CONTENT = "//div[@class='articalContent   newfont_family']";

	private static final String TITLE = "//h2[@class='titName SG_txta']";

	private static final String TIME = "//span[@class='time SG_txtc']";

	private static final String AUTHOR = "//strong[@id='ownernick']";

	private String content;
	private String title;
	private String time;
	private String author;

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public String getTime() {
		return time;
	}

	public String getAuthor() {
		return author;
	}

	public void getInfo() {
		HtmlPage page = null;
		try {
			page = LoadUtil.getInstance().getPage(url);
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// get title
		List<?> h2s = page.getByXPath(TITLE);
		HtmlHeading2 h2 = (HtmlHeading2) h2s.get(0);
		title = h2.asText();
		// get time
		List<?> spans = page.getByXPath(TIME);
		HtmlSpan span = (HtmlSpan) spans.get(0);
		time = span.asText();
		// get content
		List<?> divs = page.getByXPath(CONTENT);
		HtmlDivision div = (HtmlDivision) divs.get(0);
		content = div.asText();
		// get author
		List<?> strongs = page.getByXPath(AUTHOR);
		HtmlStrong strong = (HtmlStrong) strongs.get(0);
		author = strong.asText();
	}
}

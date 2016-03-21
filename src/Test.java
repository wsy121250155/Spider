import xinna.WeiboBlog;
import xinna.XinnaUrls;

public class Test {
	public static final String URL = "http://blog.sina.com.cn/s/blog_15c2f7ed60102w44z.html";
	public static final String XINNA = "http://blog.sina.com.cn/";

	public static final void main(String[] args) {
		// WeiboBlog wb = new WeiboBlog(URL);
		// wb.getInfo();
		// System.out.println(wb.getContent());
		XinnaUrls xu = new XinnaUrls(XINNA);
		for (String url : xu.getUrls()) {
			System.out.println(url);
		}
	}
}

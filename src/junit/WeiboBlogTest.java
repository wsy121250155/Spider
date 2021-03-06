package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import xinna.WeiboBlog;

public class WeiboBlogTest {
	private static final String WBURL = "http://blog.sina.com.cn/s/blog_6fa38d750102wnlp.html";

	@Test
	public void test() {
		WeiboBlog wb = new WeiboBlog(WBURL);
		wb.getInfo();
		assertTrue(TITLE.equals(wb.getTitle()) && AUTHOR.equals(wb.getAuthor())
				&& TIME.equals(wb.getTime()));
	}

	private static final String TITLE = "博客第一记";
	private static final String AUTHOR = "Heudora";
	private static final String TIME = "(2016-03-21 21:59:46)";
	private static final String CONTENT = "2016年3月20日，吴奇隆和刘诗诗在众人的见证和祝福下，幸福地结婚了，而我，也去电子城领着我的宝贝回家了。作为一个拮据的颜控，眼红U303已久，今日终于可以跟赖着我7年卡了六年半的ideapad Y330 say goodbye了。虽然荷包又瘪了，幸福感是满满的~"
			+ "很久没有打开空间，百度空间竟然已经关闭近一年了。学生时代的牢骚什么的，为了躲避别人的眼光，又要满足小姑娘家的虚荣心，保持腾讯空间的访问量，只能往百度空间倒。满满的流水账和苦水，每次往回看，都会被自己蠢到，定期地清理。如今，空间也被自己清理空了。"
			+ "大学毕业至今快四年了，从一个拥有浪漫情怀和梦的象牙塔女孩变成一个自暴自弃的女屌丝。这不是自嘲，是总结。最近网络疯转的“A4”腰人家曾经也是有的，而现在甚至不知腰为何物。毕业时87斤到现在的103斤，四年间经历了从女神到土肥圆的蜕变，不，是演变。T_T 我想我会越来越自卑吧。每天中午在公司食堂吃完饭，总要三个妹子结伴到公司后门的诊所称一称体重，再也不敢提当年瘦了。真的很悔，早知道会这样胖下去，当初为什么不更用力吃。"
			+ "上周本省的公务员招考，离上一次当公考炮灰已两年有余了，我想这两年期间，已经抱着随遇而安的心思了，想着关于归属感、主人翁等非本企业文化，这次报名的原因在于心累无奈某公司的落后机制，顺便看看自己剩下多少可用的活跃脑细胞。我一直不敢公开说自己考公务员这件事，因为一直不是一个善(hen)于(hui)考(du)试(shu)的人，考上大学也要庆幸当年高考题目太难把高材生们考倒了，每一次考试都是视死如归顺便做做白日梦，混沌了这些年。"
			+ "上个月，曾经的男神结婚了（以下简称曾男），和我的小学同学。2015年至今，我经历了在朋友圈看到曾男订婚的消息，在朋友圈看到曾男领证的消息，在朋友圈看到曾男拍婚纱照的消息，在朋友圈看到曾男结婚的消息，每一次都是一次失恋。虽然早就知道会有这一天，但来了依旧如此措手不及，都是打击。我和曾男两度从朋友变路人，每一次都是因为一方捅破了那层窗户纸，不同的一方。如今，我也不断地尝试着调整心态，好好过自己的生活，既然可以忍受他在远方存在，如何不能忍受他幸福地在远方存在。有朝一日我们会偶遇，在家乡的街道，在异地的闹区，是路人还是友人，分不清楚。"
			+ "2016年会有我的大事记吧，常常说的猴年马月，会实现什么呢？我期待的，是自己能够有所改变，改变四年来养成的惰性和懒癌，改变这四年带给我的一成不变。常常羡慕那些漂泊的人们，疲惫，心却飞扬。";

}

package com.youxiachai.cnblogs.app.api;

/**
 * @author youxiachai
 * @date   2013-6-19
 */
public interface Api {
	
	public interface Key {
		public String PAGE = "page";
		public String COUNT = "count";
		public String ID = "id";
		public String BOLGLISTTYPE = "bloglistype";
	}
	
	public interface Path {
		public String NEWSRECOMMEND = "/news/recommend/paged/";
		public String NEWBODY = "/news/item/";
		
		public String TOPRECOMMEND = "/blog/TenDaysTopDiggPosts/";
		public String TOPVIEW = "/blog/48HoursTopViewPosts/";
		public String BLOGINDEX = "/blog/sitehome/paged/";
		public String BLOGBODY = "/blog/post/body/";
	
	}
	
	public interface Query {
		
	}
}

package com.dx.ss.hankoo.common.constants;

public interface ViewConstants {

	String LOGIN_HEADER_COOKIE = "cookie";

	String LOGIN_TICKET_KEY = "ticket";

	String LOGIN_TICKET_USER = "LOGIN_TICKET_USER";

	String LOGIN_WX_KEY="WEIXIN_USERS_LOGIN_KEY";

	String JSONP_CALLBACK_FUN_NAME = "callback";

	String NOT_LOG_SHOW = "请重新登录";

	String NO_AUTHORITY = "无权限";

	String PARAMETER_ERORY = "参数有误";
	
	/** 文本换行符 */
	String NEWLINE_CHARACTER_N = "\n";
	
	/** web换行符 */
	String NEWLINE_CHARACTER_B = "<br/>";
	
	/**
	 * 信任COOKIE失效时间 单位秒
	 */
	int TRUST_COOKIE_TIME = 2 * 60 * 60;

	int NO = 0;

	int YES = 1;
}

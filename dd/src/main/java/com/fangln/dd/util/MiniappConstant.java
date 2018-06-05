package com.fangln.dd.util;

/**
 * 小程序枚举常量池
 * @author fangln
 *
 */
public class MiniappConstant {
	
    public static final String FIRST_PAGE = "1";

	public static final int NOT_FOUND_USER = 5001;
	
	public static final int PARAM_ERROR = 5401;
	public static final int RESULT_EMPTY = 5402;
	public static final String RESULT_EMPTY_MSG = "哎呀,找不到你想要的.";

	public static final int INVALID_TOKEN = 5403;

	/**
	 * 操作成功
	 */
	public static final int OP_SUCCESS=0;
	public static final String OP_SUCCESS_MSG = "操作成功";

	/**
	 * 用户手机号为空
	 */
	public static final int CAPTCHA_PHONE_NULL=5002;
	/**
	 * 手机号非法
	 */
	public static final int PHONE_CHECK_FAIL=5003;
	/**
	 * 未知短信验证类型
	 */
	public static final int CAPTCHA_TYPE_UNKNOW=5004;
	/**
	 * 短信频率发送太快
	 */
	public static final int CAPTCHA_SEND_TOO_FAST=5005;
	/**
	 * 发送验证码失败
	 */
	public static final int CAPTCHA_SEND_ERROR=5006;
	/**
	 * 服务器内部错误
	 */
	public static final int ERR_INTERNAL=5007;

	/**
	 * 性别
	 */
	public static final int SEX_MALE = 1;
	public static final int SEX_FEMALE = 0;


	/**
	 * 用户状态
	 */
	public static final int USER_NORMAL = 1;

	public static final String CHARSET = "UTF-8";
}

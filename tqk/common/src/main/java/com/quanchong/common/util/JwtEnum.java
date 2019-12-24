package com.quanchong.common.util;

public enum JwtEnum {

	NO_TOKEN("0005","请求不合法,提示信息:缺少token!"),
	INVALID_TOKEN("0001","token不合法!"),
	EXPIRED_TOKEN("0002","token失效,请重新获取!"),
	SIGN_FAIL_TOKEN("0003","token非法签名,请联系开发者!"),
	ERROR_TOKEN("0004","请求异常!"),
	TOKEN("0000","请求成功!");
	
	private String code;
	private String msg;
	
	JwtEnum(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
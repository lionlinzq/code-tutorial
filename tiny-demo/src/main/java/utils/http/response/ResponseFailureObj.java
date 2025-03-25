/*
 * Copyright (c) 2015 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package utils.http.response;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Description 
 * @author <a href="mailto:wangpeiwen@139130.net">Peiwen.Wang</a>
 * @Data 2015年1月12日
 * @Version 1.0.0
 */
public class ResponseFailureObj {
	@JsonProperty("error_type")
	private int errorType;
	@JsonProperty("error_code")
	private String errorMsg;
	@JsonProperty("error_params")
	private Object params;

	public ResponseFailureObj(){
	}
	
	public ResponseFailureObj(int errorCode, String errorMsg) {
		this.errorType = errorCode;
		this.errorMsg = errorMsg;		
	}

	public ResponseFailureObj(int errorCode, String errorMsg, Object params) {
		this.errorType = errorCode;
		this.errorMsg = errorMsg;
		this.params = params;
	}


	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}
}

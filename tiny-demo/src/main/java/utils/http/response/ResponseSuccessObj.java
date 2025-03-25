/*
 * Copyright (c) 2015 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package utils.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Description 
 * @author <a href="mailto:wangpeiwen@139130.net">Peiwen.Wang</a>
 * @Data 2015年1月9日
 * @Version 1.0.0
 */
public class ResponseSuccessObj {
	@JsonProperty("resp_data")
	private Object params;

	public ResponseSuccessObj() {

	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}
}

/*
 * Copyright (c) 2015 by XuanWu Wireless Technology Co.Ltd.
 *             All rights reserved
 */
package utils.http.response;

import com.xuanwu.apaas.core.utils.Constants;
import com.xuanwu.apaas.core.utils.RequestContext;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * @author <a href="mailto:wangpeiwen@139130.net">Peiwen.Wang</a>
 * @Description
 * @Data 2015年1月12日
 * @Version 1.0.0
 */
public class RestResult {

    public static Response success(Object data) {
        ResponseSuccessObj result = new ResponseSuccessObj();
        result.setParams(data);
        Response resp = Response.ok().type(MediaType.APPLICATION_JSON).entity(result).build();
        RequestContext.put(Constants.RESPONSE, resp);
        return resp;
    }

    public static Response success(Long requestId, Object data) {
        ResponseSuccessObj result = new ResponseSuccessObj();
        result.setParams(data);
        Response resp = Response.ok().header("req_id", requestId).type(MediaType.APPLICATION_JSON).entity(result).build();
        RequestContext.put(Constants.RESPONSE, resp);
        return resp;
    }

    public static Response success(Long requestId) {
        ResponseSuccessObj result = new ResponseSuccessObj();
        Response resp = Response.ok().header("req_id", requestId).type(MediaType.APPLICATION_JSON).entity(result).build();
        RequestContext.put(Constants.RESPONSE, resp);
        return resp;
    }


    public static Response success() {
        ResponseSuccessObj result = new ResponseSuccessObj();
        Response resp = Response.ok().type(MediaType.APPLICATION_JSON).entity(result).build();
        RequestContext.put(Constants.RESPONSE, resp);
        return resp;
    }


    public static Response failure(Long requestId, int errorcode, String errormsg) {
        Response resp = failure(requestId, errorcode, errormsg, Status.INTERNAL_SERVER_ERROR.getStatusCode());
        RequestContext.put(Constants.RESPONSE, resp);
        return resp;
    }

    public static Response failure(Long requestId, int errorcode, String errormsg, int status) {
        ResponseFailureObj result = new ResponseFailureObj(errorcode, errormsg);
        Response resp = Response.status(status).header("req_id", requestId).type(MediaType.APPLICATION_JSON).entity(result).build();
        RequestContext.put(Constants.RESPONSE, resp);
        return resp;
    }

    public static Response failure(Long requestId, int errorcode, String errormsg, int status, Object params) {
        ResponseFailureObj result = new ResponseFailureObj(errorcode, errormsg, params);
        Response resp = Response.status(status).header("req_id", requestId).type(MediaType.APPLICATION_JSON).entity(result).build();
        RequestContext.put(Constants.RESPONSE, resp);
        return resp;
    }

    public static Response failure(int errorcode, String errormsg) {
        ResponseFailureObj result = new ResponseFailureObj(errorcode, errormsg);
        Response resp = Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).type(MediaType.APPLICATION_JSON).entity(result).build();
        RequestContext.put(Constants.RESPONSE, resp);
        return resp;
    }
}

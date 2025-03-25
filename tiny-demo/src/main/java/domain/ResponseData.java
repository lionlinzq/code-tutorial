package domain;

import com.xuanwu.apaas.core.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="lizongxian@wxchina.com">ZongXian.Li </a>
 * @Description:ReponseData
 * @Date 2018-03-21
 * @Version 1.0
 **/
public class ResponseData {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 操作日志
     */
    private List<OperateLog> operateLogs;

    public ResponseData() {
    }

    public static ResponseData failure() {
        return new ResponseData(false);
    }

    public static ResponseData success() {
        return new ResponseData(true);
    }

    public ResponseData(boolean success) {
        this.success = success;
    }


    public static ResponseData success(String operatemodule, String operatetype, String operatecontent) {
        ResponseData res = new ResponseData(true);
        res.recordLog(operatemodule, operatetype, operatecontent);
        return res;
    }


    public class OperateLog {
        private String operatemodule;

        private String operatetype;

        private String operatecontent;

        public OperateLog() {
        }

        public OperateLog(String operatemodule, String operatetype, String operatecontent) {
            this.operatemodule = operatemodule;
            this.operatetype = operatetype;
            this.operatecontent = operatecontent;
        }

        public String getOperatecontent() {
            return operatecontent;
        }

        public void setOperatecontent(String operatecontent) {
            this.operatecontent = operatecontent;
        }

        public String getOperatemodule() {
            return operatemodule;
        }

        public void setOperatemodule(String operatemodule) {
            this.operatemodule = operatemodule;
        }

        public String getOperatetype() {
            return operatetype;
        }

        public void setOperatetype(String operatetype) {
            this.operatetype = operatetype;
        }
    }

    public boolean isSuccess() {
        return success;
    }

//    public void setSuccess(boolean success) {
//        this.success = success;
//    }

    public List<OperateLog> getOperateLogs() {
        return operateLogs;
    }

    public void setOperateLogs(List<OperateLog> operateLogs) {
        this.operateLogs = operateLogs;
    }

    public void recordLog(String operatemodule, String operatetype, String operatecontent) {
        if (ListUtil.isBlank(this.operateLogs)) {
            this.operateLogs = new ArrayList<>();
        }
        operateLogs.add(new ResponseData().new OperateLog(operatemodule, operatetype, operatecontent));
    }

    public static void main(String[] args) {
        ResponseData res = new ResponseData();
        OperateLog operateLog = new ResponseData().new OperateLog();
    }


}

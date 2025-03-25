package utils;

import com.xuanwu.apaas.core._enum.EditType;
import com.xuanwu.apaas.core.domain.SessionInfo;
import com.xuanwu.apaas.core.server.filter.SessionTokenFilter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="lizongxian@wxchina.com">ZongXian.Li </a>
 * @Description:RequestContext
 * @Date 2017-02-16
 * @Version 1.0
 **/
public class RequestContext {

    private static ThreadLocal<Map<String, Object>> context = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected synchronized Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    public static Map<String, Object> map() {
        Map<String, Object> result = new HashMap<String, Object>();
        for (String key : context.get().keySet()) {
            result.put(key, context.get().get(key));
        }
        return result;
    }

    public static Object get(String key) {
        return context.get().get(key);
    }


    public static void put(String key, Object value) {
        context.get().put(key, value);
    }

    /**
     * 将当前用户操作信息放入map
     *
     * @param type ADD表示新增，MODIFY表示编辑
     * @param map  待放入用户操作信息的map
     */
    public static Date putCurrUserInfoToMap(EditType type, Map<String, Object> map) {
        Date now = new Date((new Date().getTime() / 1000) * 1000);//抹除毫秒数
        SessionInfo info = SessionTokenFilter.getSession();
        Long accountCode = null;
        String accountName = null;
        if(info != null) {
            //优先取成员id，(为了兼容ide的，取usrinfoid）再后者取accountcode
            accountCode = info.getMemberCode() != null ? info.getMemberCode() : info.getUserInfoId() != null ? info.getUserInfoId() : info.getAccountCode();
            accountName = StringUtils.isEmpty(info.getUserInfoName()) ? info.getUserName() : info.getUserInfoName();
        }
        if(accountCode == null) {
            accountCode = get(Constants.KEY_USER_CODE) == null ? 0 : Long.valueOf(get(Constants.KEY_USER_CODE) + "");
        }
        if(accountName == null) {
            accountName = get(Constants.KEY_USER_NAME) == null ? "" : (String) get(Constants.KEY_USER_NAME);
        }

        switch (type) {
            case ADD://新增操作
                map.put("createtime", now);
                map.put("createop", accountCode);
                map.put("createaccountcode", accountCode);
                map.put("createaccountname", accountName);
                map.put("createopname", accountName);
                map.put("updatetime", now);
                map.put("updateop", accountCode);
                map.put("updateaccountcode", accountCode);
                map.put("updateaccountname", accountName);
                map.put("updateopname", accountName);
                map.put("platcreatetime", now);
                map.put("platcreateop", accountCode);
                map.put("platupdatetime", now);
                map.put("platupdateop", accountCode);
                break;
            case MODIFY://编辑操作
                map.put("updatetime", now);
                map.put("updateop", accountCode);
                map.put("updateaccountcode", accountCode);
                map.put("updateaccountname", accountName);
                map.put("updateopname", accountName);
                map.put("platupdatetime", now);
                map.put("platupdateop", accountCode);
                break;
            default:
                break;
        }
        return now;
    }

    public static void clear() {
        //contextMap.clear();
        context.set(new HashMap<String, Object>());
    }

}

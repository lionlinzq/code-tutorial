package utils;

import com.xuanwu.apaas.core.domain.SessionInfo;

/**
 * @author <a href="lizongxian@wxchina.com">ZongXian.Li </a>
 * @Description:ClientTypeUtil
 * @Date 2018-03-16
 * @Version 1.0
 **/
public class ClientTypeUtil {
    /**
     * 1 web 2 app
     *
     * @param session
     * @return
     */
    public static Long getShortType(SessionInfo session) {

        int clientTypeCode = session.getClientTypeCode().intValue();
        switch (clientTypeCode) {
            case 1:
                return 1L;
            case 2:
            case 3:
            case 7:
                return 2L;
            default:
                return session.getClientTypeCode();
        }
    }

    public static Long transferClientCategoryCode(Long clientTypeCode) {
        Long result = 1L;
        if(clientTypeCode != null) {
            switch (clientTypeCode.intValue()) {
                case 2:
                case 3:
                case 7:
                    result = 2L;
                    break;
                case 4:
                case 5:
                    result = 3L;
                    break;
                default:
                    if (clientTypeCode != null && clientTypeCode != 0) {
                        result = clientTypeCode.longValue();
                    }
                    break;
            }
        }
        return result;
    }
}

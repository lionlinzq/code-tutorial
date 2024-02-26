package pers.lionlinzq.service.impl;

import cn.monitor4all.logRecord.bean.LogDTO;
import cn.monitor4all.logRecord.service.IOperationLogGetService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lin
 */
@Slf4j
@Component
public class CustomFuncTestOperationLogGetService implements IOperationLogGetService {
    @Override
    public boolean createLog(LogDTO logDTO) {
        log.info("logDTO: [{}]", JSON.toJSONString(logDTO));
        return true;
    }
}

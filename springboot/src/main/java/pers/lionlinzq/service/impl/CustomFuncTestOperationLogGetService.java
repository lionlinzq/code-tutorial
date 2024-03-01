package pers.lionlinzq.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.lionlinzq.bean.LogDTO;
import pers.lionlinzq.service.IOperationLogGetService;

/**
 * @author lin
 */
@Slf4j
@Component
public class CustomFuncTestOperationLogGetService implements IOperationLogGetService {
    @Override
    public boolean createLog(LogDTO logDTO) {
        log.info("logDTO: [{}]", JSON.toJSONString(logDTO));
        log.info("logDTO: [{}]", logDTO.getBizType());
        return true;
    }
}

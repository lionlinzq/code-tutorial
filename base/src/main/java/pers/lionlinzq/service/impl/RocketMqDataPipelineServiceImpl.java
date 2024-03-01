package pers.lionlinzq.service.impl;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import pers.lionlinzq.bean.LogDTO;
import pers.lionlinzq.configuration.LogRecordProperties;
import pers.lionlinzq.constants.LogConstants;
import pers.lionlinzq.service.DataPipelineService;

@Service
@Slf4j
@EnableConfigurationProperties({LogRecordProperties.class})
@ConditionalOnProperty(name = "log-record.data-pipeline", havingValue = LogConstants.DataPipeline.ROCKET_MQ)
public class RocketMqDataPipelineServiceImpl implements DataPipelineService {

    @Autowired
    private LogRecordProperties properties;

    @Autowired
    private DefaultMQProducer defaultMqProducer;

    @Override
    public boolean createLog(LogDTO logDTO) {
        try {
            Message msg = new Message(properties.getRocketMqProperties().getTopic(), properties.getRocketMqProperties().getTag(), (JSON.toJSONString(logDTO)).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = defaultMqProducer.send(msg);
            log.info("LogRecord RocketMq send LogDTO [{}] sendResult: [{}]", logDTO, sendResult);
            return true;
        } catch (Exception e) {
            log.error("LogRecord RocketMq send LogDTO error", e);
            return false;
        }
    }
}

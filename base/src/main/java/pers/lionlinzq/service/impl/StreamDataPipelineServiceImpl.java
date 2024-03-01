package pers.lionlinzq.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import pers.lionlinzq.bean.LogDTO;
import pers.lionlinzq.configuration.LogRecordProperties;
import pers.lionlinzq.configuration.StreamSenderConfiguration;
import pers.lionlinzq.constants.LogConstants;
import pers.lionlinzq.service.DataPipelineService;

@Service
@Slf4j
@EnableConfigurationProperties({LogRecordProperties.class})
@ConditionalOnProperty(name = "log-record.data-pipeline", havingValue = LogConstants.DataPipeline.STREAM)
public class StreamDataPipelineServiceImpl implements DataPipelineService {

    @Autowired
    private StreamSenderConfiguration.LogRecordChannel channel;

    @Override
    public boolean createLog(LogDTO logDTO) {
        return channel.messageLoggingQueueInput().send(MessageBuilder.withPayload(logDTO).build());
    }
}

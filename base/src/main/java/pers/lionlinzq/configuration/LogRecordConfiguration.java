package pers.lionlinzq.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.lionlinzq.function.CustomFunctionRegistrar;

@Slf4j
@Configuration
public class LogRecordConfiguration {

    @Bean
    public CustomFunctionRegistrar registrar() {
        return new CustomFunctionRegistrar();
    }

}

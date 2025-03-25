package pers.lionlinzq.excel.config;

import com.github.yitter.contract.IdGeneratorException;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {

    @Bean
    public void excelUtil() {
        IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
        try {
            // 初始化ID生成器
            YitIdHelper.setIdGenerator(options);
        } catch (IdGeneratorException e) {
            throw new RuntimeException("初始化ID生成器失败", e);
        }
    }


}

package pers.lionlinzq.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
class AuthControllerTest {

    private final static ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void testGetData() throws JsonProcessingException {
        String string = "[{\"id\":1,\"name\":\"Jone\",\"age\":18,\"email\":\"test1@baomidou.com\",\"create_time\":1706712053000},{\"id\":2,\"name\":\"Jack\",\"age\":18,\"email\":\"test2@baomidou.com\",\"create_time\":1706712061000},{\"id\":3,\"name\":\"Tom\",\"age\":18,\"email\":\"test3@baomidou.com\",\"create_time\":1706712064000},{\"id\":4,\"name\":\"Sandy\",\"age\":18,\"email\":\"test4@baomidou.com\",\"create_time\":1706712066000}]";
        List list = objectMapper.readValue(string, List.class);
        log.info("objects is {}", list);
    }
}

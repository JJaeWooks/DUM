package com.jojoldu.book.springboot;

import com.jojoldu.book.springboot.web.HelloController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
//@SpringBootTest

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers= HelloController.class)
class ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello="hellos";
        mvc.perform(get("/hello")) // /hello 주소로 GET 요청
                .andExpect(status().isOk()) //HTTP 헤더의 Status를 검증
                .andExpect(content().string(hello));
    }
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name="hello";
        int amount =1000;

        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount))).andExpect(status().isOk()).andExpect(jsonPath("$.name",is(name))).andExpect(jsonPath("$.amount",is(amount)));
                
    }

}

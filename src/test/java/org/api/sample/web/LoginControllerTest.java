package org.api.sample.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.api.sample.model.Members;
import org.api.sample.model.response.ItemResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);

    private static String authToken = "";

    @Test
    void noTokenAccessDenyTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/access-test")).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void loginTest() throws Exception {
        Members member = new Members();
        member.setId("test");
        member.setPwd("test123");
        ObjectMapper mapper = new ObjectMapper();
        String memberJson =  mapper.writeValueAsString(member);

        String token = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON).content(memberJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        logger.info("token ===> {}", token);
        authToken = token;
    }

    @Test
    void loginSuccessAccessTest() throws Exception {
        logger.info("token check ===> {}", authToken);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/access-test")
                .header("X-AUTH-TOKEN", authToken)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


}
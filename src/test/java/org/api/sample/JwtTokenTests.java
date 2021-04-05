package org.api.sample;

import org.api.sample.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(properties = {"classpath:test-application.yml"})
public class JwtTokenTests {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final Logger logger = LoggerFactory.getLogger(JwtTokenTests.class);
    private static String token;

    @Test
    public void createTokenTest(){
        String token = jwtTokenProvider.createJwtToken("test", null);
        logger.info("token ===>{}", token);
        this.token = token;
        assert (token != null);
    }

    @Test
    public void validateExpiredTokenTest(){
        String expiredToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTYxNzE3OTM2NCwiaWF0IjoxNjE3MTc3NTY0fQ.BZIvRyFr1pNiEw62nq1y8eHxejpJQNPfAbIQlbG5qFI";
        String primaryKey = jwtTokenProvider.getUserPrimaryKey(expiredToken);
        assert (primaryKey == null);
    }

    @Test
    public void getTokenBodyTest(){
        String primaryKey = jwtTokenProvider.getUserPrimaryKey(this.token);
        logger.info("primaryKey ===>{}", primaryKey);
        assert ("test".equals(primaryKey));
    }


}

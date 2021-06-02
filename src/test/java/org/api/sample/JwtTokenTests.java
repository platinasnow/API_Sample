package org.api.sample;

import org.api.sample.model.Tokens;
import org.api.sample.security.JwtTokenProvider;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.*;


@SpringBootTest
public class JwtTokenTests {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final Logger logger = LoggerFactory.getLogger(JwtTokenTests.class);
    private static String accessToken;
    private static String refreshToken;

    @Test
    public void createTokenTest(){
        Tokens token = jwtTokenProvider.createJwtToken("test", null);
        logger.info("token ===>{}", token);
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
        then(token).isNotNull();
    }

    @Test
    public void validateExpiredTokenTest(){
        String expiredToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTYxNzE3OTM2NCwiaWF0IjoxNjE3MTc3NTY0fQ.BZIvRyFr1pNiEw62nq1y8eHxejpJQNPfAbIQlbG5qFI";
        String primaryKey = jwtTokenProvider.getUserPrimaryKey(expiredToken);
        then(primaryKey).isNull();
    }

    @Test
    public void getTokenBodyTest(){
        String primaryKey = jwtTokenProvider.getUserPrimaryKey(this.accessToken);
        logger.info("primaryKey ===>{}", primaryKey);
        then(primaryKey).isEqualTo("test");
    }


}

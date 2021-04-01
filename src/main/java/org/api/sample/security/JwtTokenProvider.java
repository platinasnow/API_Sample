package org.api.sample.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("spring.jwt.secret")
    private String tokenSecretKey;
    private final long tokenValidMillisecond = 1000L * 60 * 30; // 30분 유효
    private final String _TOKEN_NAME = "X-AUTH-TOKEN";
    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final UserDetailsService userDetailService;

    @PostConstruct
    protected void init() {
        logger.debug("***** JWT Provider init");
        tokenSecretKey = Base64.getEncoder().encodeToString(tokenSecretKey.getBytes());
    }

    public String createJwtToken(String username, List<String> roles) {
        Calendar cal = Calendar.getInstance();
        if(roles == null){
            roles = new ArrayList<>();
            roles.add("ROLE_USER");
        }
        return JWT.create()
                .withSubject(username)
                .withClaim("roles", roles)
                .withIssuedAt(cal.getTime())
                .withExpiresAt(new Date(cal.getTimeInMillis() + tokenValidMillisecond))
                .sign(Algorithm.HMAC256(tokenSecretKey));
    }

    public Authentication getAuthentication(String userPk) {
        UserDetails userDetails = userDetailService.loadUserByUsername(userPk);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPrimaryKey(String token) {
        String primaryKey = null;
        try {
            primaryKey = JWT.require(Algorithm.HMAC256(tokenSecretKey)).build().verify(token).getSubject();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return primaryKey;
    }

    public String getHeaderToken(HttpServletRequest request) {
        return request.getHeader(_TOKEN_NAME);
    }


}

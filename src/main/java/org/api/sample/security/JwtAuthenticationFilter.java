package org.api.sample.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.api.sample.model.Tokens;
import org.api.sample.model.response.ItemResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenJson = jwtTokenProvider.getHeaderToken((HttpServletRequest)request);
        if(tokenJson != null){
            ObjectMapper mapper = new ObjectMapper();
            ItemResponse<Tokens> token = mapper.readValue(tokenJson, new TypeReference<ItemResponse<Tokens>>(){});
            String userPk = jwtTokenProvider.getUserPrimaryKey(token.getData().getAccessToken());
            if(userPk != null){
                Authentication auth = jwtTokenProvider.getAuthentication(userPk);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}

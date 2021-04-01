package org.api.sample.web;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import lombok.RequiredArgsConstructor;
import org.api.sample.model.Members;
import org.api.sample.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginController {

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public String login(@RequestBody Members members){
        //TODO ID PASSWORD 인증 로직 구현
        return jwtTokenProvider.createJwtToken(members.getId(), null);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }


}

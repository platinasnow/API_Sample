package org.api.sample.web;

import lombok.RequiredArgsConstructor;
import org.api.sample.model.Members;
import org.api.sample.security.JwtTokenProvider;
import org.api.sample.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody Members members){
        Members loginItem =  memberService.findById(members.getId());
        if(loginItem != null ){
            if(members.getPwd() != null && passwordEncoder.matches(members.getPwd(), loginItem.getPwd())){
                return jwtTokenProvider.createJwtToken(members.getId(), null);
            }
        }
        return "";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/test3")
    public String test3(){
        return "test33";
    }

    @GetMapping("/test2")
    public String test2() throws Exception {
        //강제 에러
        throw new Exception();
    }


}

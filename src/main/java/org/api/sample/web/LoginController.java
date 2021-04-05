package org.api.sample.web;

import lombok.RequiredArgsConstructor;
import org.api.sample.model.Members;
import org.api.sample.model.response.CommonResponse;
import org.api.sample.security.JwtTokenProvider;
import org.api.sample.service.MemberService;
import org.api.sample.service.ResponseService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final ResponseService responseService;

    @PostMapping("/login")
    public CommonResponse login(@RequestBody Members members){
        Members loginItem =  memberService.findById(members.getId());
        if(loginItem != null ){
            if(members.getPwd() != null && passwordEncoder.matches(members.getPwd(), loginItem.getPwd())){
                return responseService.getItemResponse(jwtTokenProvider.createJwtToken(members.getId(), null));
            }
        }
        return responseService.getFailedResponse("1000", "loginFail");
    }

    @GetMapping("/access-test")
    public CommonResponse test(){
        return responseService.getSuccessResponse();
    }

    @GetMapping("/test3")
    public CommonResponse test3(){
        return responseService.getItemResponse("test1");
    }

    @GetMapping("/test2")
    public String test2() throws Exception {
        //강제 에러
        throw new Exception();
    }


}

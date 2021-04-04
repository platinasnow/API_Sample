package org.api.sample.web;

import lombok.RequiredArgsConstructor;
import org.api.sample.model.response.CommonResponse;
import org.api.sample.service.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class CustomErrorController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);
    private final ResponseService responseService;

    @GetMapping("/exError")
    public CommonResponse error(HttpServletRequest request){
        Object statusCode =request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorException = RequestDispatcher.ERROR_EXCEPTION;

        logger.info("errorCode ===> {}", statusCode);
        logger.info("errorException ===> {}" , errorException);
        //TODO LOG 처리

        return responseService.getFailedResponse(String.valueOf(statusCode), errorException);
    }

    @Override
    public String getErrorPath() {
        return "/exError";
    }
}

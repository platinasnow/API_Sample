package org.api.sample.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomErrorController implements ErrorController {

    final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @GetMapping("/exError")
    public String error(HttpServletRequest request){
        Object statusCode =request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorException = RequestDispatcher.ERROR_EXCEPTION;
        logger.info("errorCode ===> {}", statusCode);
        logger.info("errorException ===> {}" , errorException);
        return "error " + statusCode;
    }

    @Override
    public String getErrorPath() {
        return "/exError";
    }
}

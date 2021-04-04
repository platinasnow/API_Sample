package org.api.sample.common;

import lombok.RequiredArgsConstructor;
import org.api.sample.model.response.CommonResponse;
import org.api.sample.service.ResponseService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "org.api.sample.web")
public class ExceptionAdvise extends ResponseEntityExceptionHandler {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    protected CommonResponse DefaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailedResponse("500", "Exception");
    }

}

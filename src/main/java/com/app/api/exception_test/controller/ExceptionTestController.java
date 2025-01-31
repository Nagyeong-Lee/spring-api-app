package com.app.api.exception_test.controller;

import com.app.api.exception_test.dto.BindExceptionTestDto;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exception")
public class ExceptionTestController {

    @GetMapping("/bind-exception-test")
    public String bindExceptionTest(@Valid BindExceptionTestDto bindExceptionTestDto) {
        return "ok";
    }

    @GetMapping("/business-exception-test")
    public void businessExceptionTest() {
        throw new BusinessException(ErrorCode.TEST);
    }
}

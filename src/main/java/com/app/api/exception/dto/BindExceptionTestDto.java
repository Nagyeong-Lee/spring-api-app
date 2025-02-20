package com.app.api.exception.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BindExceptionTestDto {
    
    @NotBlank(message = "필수값입니다.")
    private String val1;
    
    @Max(value = 10, message = "최대값은 10입니다.")
    private Integer val2;
}

package com.tsis.drs.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginForm {
    private String loginId;
    private String password;
}

package org.api.sample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {

    private String id;
    private String pwd;
    private String refreshToken;
}

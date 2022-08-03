package com.smile.basic.core.enums;

import lombok.Getter;


public enum PasswordEncoderTypeEnum {

    BCRYPT("{bcrypt}","BCRYPT加密"),
    NOOP("{noop}","无加密明文");

    @Getter
    private final String prefix;

    @Getter
    private final String desc;

    PasswordEncoderTypeEnum(String prefix, String desc){
        this.prefix=prefix;
        this.desc = desc;
    }

}

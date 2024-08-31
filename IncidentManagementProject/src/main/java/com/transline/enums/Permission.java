package com.transline.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    
    AUTHORIZED_USER_READ("(authorized user:read"),
    AUTHORIZED_USER_UPDATE("(authorized user:update"),
    AUTHORIZED_USER_CREATE("(authorized user:create"),
    AUTHORIZED_USER_DELETE("(authorized user:delete"),
    
    NORMAL_USER_READ("(noraml user:read"),
    NORMAL_USER_UPDATE("(noraml user:update"),
    NORMAL_USER_CREATE("(noraml user:create"),
    NORMAL_USER_DELETE("(noraml user:delete"),
    
    ;

    @Getter
    private final String permission;
}
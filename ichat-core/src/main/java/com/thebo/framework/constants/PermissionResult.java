package com.thebo.framework.constants;

public enum PermissionResult {
    PERMISSION_SUCCESS("验证通过"),SESSION_TIMEOUT("登陆超时"),PERMISSION_DENIED("没有权限");

    private String type;

    private PermissionResult(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}

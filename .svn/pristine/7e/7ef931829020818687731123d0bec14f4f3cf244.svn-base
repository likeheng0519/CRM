/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p/>
 * Created on 2016/9/25.
 */
package cn.com.conversant.weizi.crm.api.exception;

import cn.com.conversant.commons.exception.ErrorCode;

/**
 * @author Robin
 */
public enum AdminErrorCode implements ErrorCode {
    SESSION_INVALIDATE(10002, "session.invalidate"),
    ADMIN_ACCOUNT_EXISTED(11001, "admin.account.exist"),
    ADMIN_ACCOUNT_NO_EXISTED(11002, "admin.account.not.exist"),
    ADMIN_ACCOUNT_INVALID(11003, "admin.account.invalid"),
    ADMIN_INVALID_PASSWORD(11004, "admin.password.invalidate"),
    ADMIN_INVALID_STATUS(11005, "admin.status.invalidate"),;

    private int errorCode;

    private String message;

    private AdminErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }
}

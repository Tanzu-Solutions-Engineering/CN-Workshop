package com.vmware.resilience.frontend;

import java.util.Date;

public class FrontendError {

    private Date date;
    private String msg;

    public FrontendError(String msg) {
        this.msg = msg;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String getMsg() {
        return msg;
    }
}

package com.vmware.resilience.backend;

import java.util.Date;

public class BackendResponse {

    private int wait;
    private Date date;

    public BackendResponse() { }

    public BackendResponse(int wait, Date date) {
        this.wait = wait;
        this.date = date;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

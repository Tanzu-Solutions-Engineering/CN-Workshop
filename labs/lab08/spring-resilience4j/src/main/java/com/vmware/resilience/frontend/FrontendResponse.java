package com.vmware.resilience.frontend;

import com.vmware.resilience.backend.BackendResponse;

import java.util.Date;

public class FrontendResponse {

    private boolean fallback;
    private long totalTime;
    private int backendWait;
    private Date backendDate;

    public FrontendResponse(boolean fallback, long totalTime, BackendResponse response) {
        this.fallback = fallback;
        this.totalTime = totalTime;
        this.backendWait = response.getWait();
        this.backendDate = response.getDate();
    }

    public long getTotalTime() {
        return totalTime;
    }

    public int getBackendWait() {
        return backendWait;
    }

    public Date getBackendDate() {
        return backendDate;
    }

    public boolean isFallback() {
        return fallback;
    }
}

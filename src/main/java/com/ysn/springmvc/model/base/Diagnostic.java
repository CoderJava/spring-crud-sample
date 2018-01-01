package com.ysn.springmvc.model.base;

import org.springframework.http.HttpStatus;

public class Diagnostic {

    private HttpStatus status;
    private long unix_timestamp;

    public Diagnostic() {
    }

    public Diagnostic(HttpStatus status, long unix_timestamp) {
        this.status = status;
        this.unix_timestamp = unix_timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public long getUnix_timestamp() {
        return unix_timestamp;
    }

    public void setUnix_timestamp(long unix_timestamp) {
        this.unix_timestamp = unix_timestamp;
    }

    @Override
    public String toString() {
        return "Diagnostic{" +
                "status='" + status + '\'' +
                ", unix_timestamp=" + unix_timestamp +
                '}';
    }
}

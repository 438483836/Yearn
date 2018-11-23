package com.yearn.life.pojo;

import java.io.Serializable;

public class ZTOResponse implements Serializable {

    private static final long serialVersionUID = 6595536548911627202L;

    private String message;
    private Boolean status;
    private String statusCode;
    private Object result;

    public ZTOResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}

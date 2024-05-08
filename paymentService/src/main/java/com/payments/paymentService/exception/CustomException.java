package com.payments.paymentService.exception;

import org.springframework.http.HttpStatusCode;

public class CustomException extends RuntimeException {

    private HttpStatusCode errorCode;
    private int status;

    public CustomException(String message, HttpStatusCode errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

	public HttpStatusCode getErrorCode() {
		return this.errorCode;
	}

	public int getStatus() {
		return this.status;
	}
}
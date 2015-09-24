package com.slug.exception;

/**
 * @author zhw
 * @version 0.1  15/9/24
 */
public class InitFailException extends RuntimeException {

    public InitFailException() {
        super();
    }

    public InitFailException(String msg) {
        super(msg);
    }
}

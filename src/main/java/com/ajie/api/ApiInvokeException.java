package com.ajie.api;

/**
 * api调用异常
 *
 * @author niezhenjie
 *
 */
public class ApiInvokeException extends Exception {

	private static final long serialVersionUID = 1L;

	public ApiInvokeException() {
		super();
	}

	public ApiInvokeException(String message) {
		super(message);
	}

	public ApiInvokeException(Throwable e) {
		super(e);
	}

	public ApiInvokeException(String message, Throwable e) {
		super(message, e);
	}

}

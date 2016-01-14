package com.thebo.framework.exception;

/**
 * 表示参数不正确。
 * 
 * <p style="display:none">modifyRecord</p>
 * @since
 * @version
 */
public class IllegalArgumentException extends EasyCarException {

	private static final long serialVersionUID = -1398555596273694035L;

	public IllegalArgumentException() {
		super("illegalArgument");
	}

	public IllegalArgumentException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public IllegalArgumentException(String messageKey) {
		super(messageKey);
	}

	public IllegalArgumentException(Throwable cause) {
		super("illegalArgument", cause);
	}

}

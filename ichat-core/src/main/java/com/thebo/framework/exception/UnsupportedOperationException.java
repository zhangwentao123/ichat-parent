package com.thebo.framework.exception;

/**
 * 不支持的操作，一般源于程序员错误地调用了API，或前台权限控制的疏漏。
 * <p style="display:none">modifyRecord</p>
 * @since
 * @version
 */
public class UnsupportedOperationException extends EasyCarException {

	private static final long serialVersionUID = 2263196920034158855L;

	public UnsupportedOperationException() {
		super("unsupportedOperation");
	}

	public UnsupportedOperationException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public UnsupportedOperationException(String messageKey) {
		super(messageKey);
	}

	public UnsupportedOperationException(Throwable cause) {
		super("unsupportedOperation", cause);
	}
	
}

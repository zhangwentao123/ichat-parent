package com.thebo.framework.exception;

/**
 * 表示数据不一致。
 * 
 * <p style="display:none">modifyRecord</p>
 * @since
 * @version
 */
public class InconsistentException extends EasyCarException {

	private static final long serialVersionUID = 1669843560642800254L;

	public InconsistentException() {
		super("inconsistent");
	}

	public InconsistentException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public InconsistentException(String messageKey) {
		super(messageKey);
	}

	public InconsistentException(Throwable cause) {
		super("inconsistent", cause);
	}

}

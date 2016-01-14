package com.thebo.framework.exception;

/**
 * 系统参数未配置或配置错误
 * <p style="display:none">modifyRecord</p>
 * @since
 * @version
 */
public class ErrorBasConfigException extends EasyCarException {

	private static final long serialVersionUID = 2263196920034158855L;

	public ErrorBasConfigException() {
		super("errorBasConfig");
	}

	public ErrorBasConfigException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public ErrorBasConfigException(String messageKey) {
		super(messageKey);
	}

	public ErrorBasConfigException(Throwable cause) {
		super("errorBasConfig", cause);
	}
	
}

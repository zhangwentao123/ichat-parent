package com.thebo.framework.exception;

/**
 * 表示数据提交失败。
 * <p style="display:none">modifyRecord</p>
 * @since
 * @version
 */
public class DataCommitException extends EasyCarException {

	private static final long serialVersionUID = 1669843560642800254L;

	public DataCommitException() {
		super("dataCommitFailed");
	}

	public DataCommitException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public DataCommitException(String messageKey) {
		super(messageKey);
	}

	public DataCommitException(Throwable cause) {
		super("dataCommitFailed", cause);
	}

}

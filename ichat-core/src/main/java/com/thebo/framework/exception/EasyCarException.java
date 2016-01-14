package com.thebo.framework.exception;


public class EasyCarException extends RuntimeException {

	private static final long serialVersionUID = -3088104317098344394L;
    private Object[] messageArgs;

    public EasyCarException() {
		super("unknown");
	}

	public EasyCarException(String messageKey, Throwable cause, Object... messageArgs) {
		super(messageKey, cause);
        this.messageArgs = messageArgs;
	}

	public EasyCarException(String messageKey, Object... messageArgs) {
		super(messageKey);
        this.messageArgs = messageArgs;
	}

	public EasyCarException(Throwable cause) {
		super("unknown", cause);
	}
	
//	public String getLocalizedMessage() {
//		String errorMessageKey = "error." +  getMessage();
//		MessageSource messageSource = (MessageSource) ContextUtils.getBean("messageSource");
//		return messageSource.getMessage(errorMessageKey, messageArgs, "", LocaleContextHolder.getLocale());
//    }
	
}

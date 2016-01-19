package com.thebo.framework.cache;

public interface Cache {

	public String getValue(String key);

	public void putValue(String key, String value);

	public void removeValue(String key);

	public void clearCache();
	
	public void refreshCache();
}

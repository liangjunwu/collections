package com.ljw.ssme.beans.common;

public class Json {
	
	/**
	 * -1
	 * -2
	 * -3
	 */
	private int status;  //状态 码
	private Object obj; //返回的数据
	private boolean success; //是否成功
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}

package com.tysong.qq.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class WebResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status;
	
	private Object data;
	
	private String message;
	
	private String checkType;
	
	private String msgType;
	
	private List<String> msgList = new ArrayList<String>();
	
	private String msg;
	
	public WebResult() {
		this.status = "200";
	}
	
	public WebResult(String msg) {
		this.msgList = Arrays.asList(msg);
		this.checkType = "";
	}
	
	public WebResult(String msg, String checkType) {
		this.msgList = Arrays.asList(msg);
		this.checkType = checkType;
	}
	
	public WebResult(List<String> msgList, String msgType) {
		this.msgList = msgList;
		this.msgType = msgType;
	}
	
	public WebResult(List<String> msgList, String msgType, String checkType) {
		this.msgList = msgList;
		this.msgType = msgType;
		this.checkType = checkType;
	}

	public String getStatus() {
		return status == null ? "200" : status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<String> msgList) {
		this.msgList = msgList;
	}
	
	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	
	public String getMsgType() {
		return msgType == null ? "1" : msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
    /**
     * 获取 msg
     * @return msg.
     */
    public String getMsg() {
        return msg;
    }

    
    /**
     * 设置 msg
     * @param msg msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
        msgList.add(msg);
    }
	
}

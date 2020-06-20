package com.bridgelabz.bookstore.payload.response;

public class Response {
	private static final long serialVersionUID = 6597721492410014185L;
	private int status;          //create status integer for user response 
	private String message;     // create message in String for give the user message
	private Object data;        // create  data for give any information
	public Response() {
		// TODO Auto-generated constructor stub
	}
	public Response(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + ", data=" + data + "]";
	}


}

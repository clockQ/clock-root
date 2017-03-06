package org.clock.bs.param;

public class ResponsePOJO {
    //返回类型包含的三部分,执行结果result,返回信息message,和元数据data
    private boolean result;
	private String message;
	private Object data;
	
	public ResponsePOJO(){
		this.result = false;
		this.message = "";
		this.data = null;
	}
	
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
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

   
}
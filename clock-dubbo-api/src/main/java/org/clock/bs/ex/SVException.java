package org.clock.bs.ex;

public class SVException extends Exception {
	/**
	 * SV层异常处理类
	 */
	private static final long serialVersionUID = -1439341210317149766L;

	public SVException(String msg){
		super(msg);
	}
	
	public SVException(String msg,Exception ex){
		super(msg,ex);
	}
}

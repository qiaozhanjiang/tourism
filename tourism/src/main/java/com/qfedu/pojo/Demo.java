package com.qfedu.pojo;

/**
 * 接收前端订单购买页 转用类
 * 
 */
public class Demo {
	private String uuid;
	private String g_t_id;
	private String o_number;
	private String message;
	private double messageMoney;
	
	
	

	public double getMessageMoney() {
		return messageMoney;
	}

	public void setMessageMoney(double messageMoney) {
		this.messageMoney = messageMoney;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getG_t_id() {
		return g_t_id;
	}

	public void setG_t_id(String g_t_id) {
		this.g_t_id = g_t_id;
	}

	public String getO_number() {
		return o_number;
	}

	public void setO_number(String o_number) {
		this.o_number = o_number;
	}

}

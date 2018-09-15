package com.qfedu.pojo;

/**
 * 订单实体类
 */
public class Order {
	private int o_id;
	private int g_t_id;
	private String uuid;
	private int o_number;// 记录已购买的数量.
	private int o_state; // 是否付费. 0未付费,1已付费
	private String o_pay_time;

	public int getO_number() {
		return o_number;
	}

	public void setO_number(int o_number) {
		this.o_number = o_number;
	}

	public int getO_id() {
		return o_id;
	}

	public void setO_id(int o_id) {
		this.o_id = o_id;
	}

	public int getG_t_id() {
		return g_t_id;
	}

	public void setG_t_id(int g_t_id) {
		this.g_t_id = g_t_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getO_state() {
		return o_state;
	}

	public void setO_state(int o_state) {
		this.o_state = o_state;
	}

	public String getO_pay_time() {
		return o_pay_time;
	}

	public void setO_pay_time(String o_pay_time) {
		this.o_pay_time = o_pay_time;
	}

	@Override
	public String toString() {
		return "Order [o_id=" + o_id + ", g_t_id=" + g_t_id + ", uuid=" + uuid + ", o_number=" + o_number + ", o_state="
				+ o_state + ", o_pay_time=" + o_pay_time + "]";
	}

}

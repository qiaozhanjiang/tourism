package com.qfedu.pojo;

// 
/**
 * 银行卡表对应类
 */
public class Bank {
	private Integer bank_id;
	private String uuid; // 用户id
	private String bank_number; // 银行卡号

	public Integer getBank_id() {
		return bank_id;
	}

	public void setBank_id(Integer bank_id) {
		this.bank_id = bank_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getBank_number() {
		return bank_number;
	}

	public void setBank_number(String bank_number) {
		this.bank_number = bank_number;
	}

	@Override
	public String toString() {
		return "Bank [bank_id=" + bank_id + ", uuid=" + uuid + ", bank_number=" + bank_number + "]";
	}

}

package com.henangengyun.gxrl.po;

import java.util.Date;

public class ConfirmInfo {
	private String No; // 编号
	private String transaction_id; // 微信支付订单号
	private float cash_fee; // 现金支付金额
	private float total_fee; // 订单金额
	private String result_code; // 业务结果
	private Date time_end; // 支付完成时间

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public float getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(float cash_fee) {
		this.cash_fee = cash_fee;
	}

	public float getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(float total_fee) {
		this.total_fee = total_fee;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public Date getTime_end() {
		return time_end;
	}

	public void setTime_end(Date time_end) {
		this.time_end = time_end;
	}

}

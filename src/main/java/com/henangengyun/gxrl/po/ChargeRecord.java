package com.henangengyun.gxrl.po;

public class ChargeRecord {
	private String No;
	private String Name;
	private String Season;
	private Float PayedFee;// 缴纳金额
	private Float DelayFee;// 滞纳金
	private String PayedTime;// 缴费时间
	private String PayedMethod;// 缴费方式
	private String StreamNo;// 流水号

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSeason() {
		return Season;
	}

	public void setSeason(String season) {
		Season = season;
	}

	public Float getPayedFee() {
		return PayedFee;
	}

	public void setPayedFee(Float payedFee) {
		PayedFee = payedFee;
	}

	public Float getDelayFee() {
		return DelayFee;
	}

	public void setDelayFee(Float delayFee) {
		DelayFee = delayFee;
	}

	public String getPayedTime() {
		return PayedTime;
	}

	public void setPayedTime(String payedTime) {
		PayedTime = payedTime;
	}

	public String getPayedMethod() {
		return PayedMethod;
	}

	public void setPayedMethod(String payedMethod) {
		PayedMethod = payedMethod;
	}

	public String getStreamNo() {
		return StreamNo;
	}

	public void setStreamNo(String streamNo) {
		StreamNo = streamNo;
	}

}

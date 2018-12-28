package com.henangengyun.gxrl.po;

public class ChargeDetail {
	private float Price; // 按面积单价
	private float MeterPrice; // 按流量单价
	private float DiscountRatio; // 折扣系数
	private String StartTime; // 供热开始时间
	private String EndTime; // 供热结束时间
	private float DayValue; // 供热天数
	private float MeterValue; // 用热流量
	private float PreFee; // 预收费用
	private float ConsumeFee; // 实际费用

	
	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public float getMeterPrice() {
		return MeterPrice;
	}

	public void setMeterPrice(float meterPrice) {
		MeterPrice = meterPrice;
	}

	public float getDiscountRatio() {
		return DiscountRatio;
	}

	public void setDiscountRatio(float discountRatio) {
		DiscountRatio = discountRatio;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public float getDayValue() {
		return DayValue;
	}

	public void setDayValue(float dayValue) {
		DayValue = dayValue;
	}

	public float getMeterValue() {
		return MeterValue;
	}

	public void setMeterValue(float meterValue) {
		MeterValue = meterValue;
	}

	public float getPreFee() {
		return PreFee;
	}

	public void setPreFee(float preFee) {
		PreFee = preFee;
	}

	public float getConsumeFee() {
		return ConsumeFee;
	}

	public void setConsumeFee(float consumeFee) {
		ConsumeFee = consumeFee;
	}
}

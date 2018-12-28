package com.henangengyun.gxrl.po;

public class SeasonFeeInfo {
	private String HeatingSeason; // 季度
	private String State; // 费用状态
	private String LateFeeStartTime; // 缴费期限
	private float SeasonOwedFee; // 欠费金额
	private Float DelayFee; // 滞纳金
	private Float OwedFee; // (总欠费金额=欠费金额+滞纳金)

	public String getHeatingSeason() {
		return HeatingSeason;
	}

	public void setHeatingSeason(String heatingSeason) {
		HeatingSeason = heatingSeason;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getLateFeeStartTime() {
		return LateFeeStartTime;
	}

	public void setLateFeeStartTime(String lateFeeStartTime) {
		LateFeeStartTime = lateFeeStartTime;
	}

	public float getSeasonOwedFee() {
		return SeasonOwedFee;
	}

	public void setSeasonOwedFee(float seasonOwedFee) {
		SeasonOwedFee = seasonOwedFee;
	}

	public Float getDelayFee() {
		return DelayFee;
	}

	public void setDelayFee(Float delayFee) {
		DelayFee = delayFee;
	}

	public Float getOwedFee() {
		return OwedFee;
	}

	public void setOwedFee(Float owedFee) {
		OwedFee = owedFee;
	}

}

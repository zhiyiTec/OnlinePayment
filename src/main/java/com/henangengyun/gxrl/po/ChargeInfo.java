package com.henangengyun.gxrl.po;

public class ChargeInfo {
	private String No; // 编号
	private String Name; // 姓名
	private Float TotalOwedFee; // 应缴总金额

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

	public Float getTotalOwedFee() {
		return TotalOwedFee;
	}

	public void setTotalOwedFee(Float totalOwedFee) {
		TotalOwedFee = totalOwedFee;
	}
}
